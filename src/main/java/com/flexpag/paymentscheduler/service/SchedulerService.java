package com.flexpag.paymentscheduler.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.flexpag.paymentscheduler.dto.FindAllDto;
import com.flexpag.paymentscheduler.dto.FindIdDto;
import com.flexpag.paymentscheduler.dto.PaymentScheduleDto;
import com.flexpag.paymentscheduler.entities.Scheduler;
import com.flexpag.paymentscheduler.entities.enums.StatusPayment;
import com.flexpag.paymentscheduler.repository.SchedulerRepository;

@Service
public class SchedulerService {
	
	@Autowired
	private SchedulerRepository repo;
	
	//Buscar Todos
	public List<FindAllDto> findAll(){
		List<Scheduler> obj = repo.findAll();
		List<FindAllDto> objDTO = obj.stream()
				.map(item -> new FindAllDto(item))
				.collect(Collectors.toList());
		return objDTO;
	}
	
	//Buscar Por Id
		public FindIdDto findId(@PathVariable Long id){
			Scheduler obj = repo.findById(id).get();
			FindIdDto objDto = new FindIdDto(obj);
			return objDto;
		}
	
	//Criar Agendamento
	public PaymentScheduleDto paymentSchedule(@RequestBody PaymentScheduleDto objDto) {
		objDto.setStatus(StatusPayment.Pending);
		Scheduler obj = new Scheduler();
		obj.setId(objDto.getId());
		obj.setDate(objDto.getDate());
		obj.setDescription(objDto.getDescription());
		obj.setPrice(objDto.getPrice());
		obj.setStatus(objDto.getStatus());
		repo.save(obj);
		PaymentScheduleDto dto = new PaymentScheduleDto(obj);
		return dto;
	}

	public ResponseEntity<Optional<Scheduler>> updateSchedule(@PathVariable Long id, @RequestBody Scheduler scheduler) {

		Optional<Scheduler> obj = repo.findById(id);
		//Permitido apenas a atualização da data:hora e descrição
		if(obj.isPresent()) {
			if(obj.get().getStatus() != StatusPayment.Paid) {
			scheduler.setStatus(StatusPayment.Pending);
			repo.save(scheduler);
			return ResponseEntity.ok().build();
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
					}else {
						return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj);
					}
		}
	//Deletar agendamento por ID
	public ResponseEntity<Optional<Scheduler>> deleteId(@PathVariable Long id){
		
		Optional<Scheduler> obj = repo.findById(id);
		
		if(obj.isPresent()) {
			if(obj.get().getStatus() != StatusPayment.Paid) {
			repo.deleteById(id);
			return ResponseEntity.ok().build();
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
					}else {
						return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj);
					}
		}

	
	//Filtrando Status
	public List<Scheduler> searchStatus(StatusPayment status) {
		List<Scheduler> obj = repo.findByStatus(status);
		return obj;
	}
	
	
	

}

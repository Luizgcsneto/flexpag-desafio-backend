package com.flexpag.paymentscheduler.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.flexpag.paymentscheduler.dto.PaymentScheduleDTO;
import com.flexpag.paymentscheduler.entities.Scheduler;
import com.flexpag.paymentscheduler.entities.enums.StatusPayment;
import com.flexpag.paymentscheduler.repository.SchedulerRepository;

@Service
public class SchedulerService {
	
	@Autowired
	private SchedulerRepository repo;
	
	//Buscar Todos
	public ResponseEntity<List<Scheduler>> buscarTodos(){
		List<Scheduler> obj = repo.findAll();
		return ResponseEntity.ok(obj);
	}
	
	//Buscar Por Id
		public ResponseEntity<Optional<Scheduler>> buscarId(@PathVariable Long id){
			Optional<Scheduler> obj = repo.findById(id);
			return ResponseEntity.ok().body(obj);
		}
	
	//Criar Agendamento
	public PaymentScheduleDTO criarAgendamento(@RequestBody Scheduler scheduler) {
		scheduler.setStatus(StatusPayment.Pending);
		Scheduler obj = repo.save(scheduler);
		PaymentScheduleDTO dto = new PaymentScheduleDTO(obj);
		return dto;
	}
	
	//Atualizar agendamento por ID
	public ResponseEntity<Scheduler> atualizarAgendamento(@PathVariable Long id, @RequestBody Scheduler scheduler) {
		
		Optional<Scheduler> obj = repo.findById(id);
		
		StatusPayment status = obj.get().getStatus();
		
		//Permitido apenas a atualização da data:hora e descrição
		if(obj.isPresent() && status  != StatusPayment.Paid) {
			scheduler.setStatus(StatusPayment.Pending);
			scheduler.setDate(scheduler.getDate());
			scheduler.setDescription(scheduler.getDescription());
			scheduler.setPrice(obj.get().getPrice());
			scheduler.setStatus(obj.get().getStatus());
			repo.save(scheduler);
		}else {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok().body(scheduler);
	}
	
	//Deletar agendamento por ID
	public void deletarAgendamento(@PathVariable Long id){
		
		Optional<Scheduler> obj = repo.findById(id);
		
		StatusPayment status = obj.get().getStatus();
		
		if(obj.isPresent() && status  != StatusPayment.Paid) {
			repo.deleteById(id);
			ResponseEntity.ok().build();
		}else {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	//Filtrando Status
	public List<Scheduler> consultarStatus(String status) {
		
		List<Scheduler> obj = repo.findByStatus(status);
		
		return obj;
		
	}
	
	
	

}

package com.flexpag.paymentscheduler.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flexpag.paymentscheduler.dto.PaymentScheduleDTO;
import com.flexpag.paymentscheduler.entities.Scheduler;
import com.flexpag.paymentscheduler.service.SchedulerService;

@RestController
public class SchedulerController {
	
	@Autowired
	private SchedulerService service;
	
	//Endpoint GET ALL
	@GetMapping("/scheduler")
	public ResponseEntity<List<Scheduler>> findAll(){
		return service.buscarTodos();
	}
	
	//Endpoint GET por ID
	@GetMapping("/scheduler/{id}")
	public ResponseEntity<Optional<Scheduler>> findId(@PathVariable Long id){
		return service.buscarId(id);
	}

	//Endpoint POST
	@PostMapping("/scheduler")
	public PaymentScheduleDTO paymentSchedule(@RequestBody Scheduler scheduler){
		PaymentScheduleDTO obj =  service.criarAgendamento(scheduler);
		return obj;
	}
	
	//Endpoint PUT
	@PutMapping("/scheduler/{id}")
	public ResponseEntity<Scheduler> updateSchedule(@PathVariable Long id, @RequestBody Scheduler scheduler){
		ResponseEntity<Scheduler> obj = service.atualizarAgendamento(id, scheduler);
		return obj;
	}
	
	//Endpoint DELETE
	
	@DeleteMapping("/scheduler/{id}")
	public void deletarId(@PathVariable Long id){
		service.deletarAgendamento(id);
	}
	
	//Endpoint GET por Status
	@GetMapping("/scheduler/search")
	public ResponseEntity<List<Scheduler>> search(@RequestParam(value="status", required = false) String status){
		
		List<Scheduler> obj = service.consultarStatus(status);
		
		return ResponseEntity.ok().body(obj);
	}
	
		
		

}

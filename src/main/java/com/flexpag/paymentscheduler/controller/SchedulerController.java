package com.flexpag.paymentscheduler.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flexpag.paymentscheduler.Scheduler;
import com.flexpag.paymentscheduler.service.SchedulerService;

@RestController
public class SchedulerController {
	
	@Autowired
	SchedulerService service;
	
	//Endpoint GET ALL
	@GetMapping("/scheduler")
	public ResponseEntity<List<Scheduler>> buscarTodos(){
		return service.buscarTodos();
	}
	
	//Endpoint GET por ID
	@GetMapping("/scheduler/{id}")
	public ResponseEntity<Optional<Scheduler>> buscarId(@PathVariable Long id, @RequestBody Scheduler scheduler){
		return service.buscarId(id);
	}

	//Endpoint POST
	
	@PostMapping("/scheduler")
	public ResponseEntity<Scheduler> agendarPagamento(@RequestBody Scheduler scheduler){
		Scheduler obj =  service.criarAgendamento(scheduler);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}
	
	//Endpoint PUT
	
	@PutMapping("/scheduler/{id}")
	public Scheduler atualizarPagamento(@PathVariable Long id, @RequestBody Scheduler scheduler){
		return service.atualizarAgendamento(id, scheduler);
	}
	
	//Endpoint DELETE
	
	@DeleteMapping("/scheduler/{id}")
	public ResponseEntity<Scheduler> deletarId(@PathVariable Long id, Scheduler scheduler){
		return null;
	}
	
	
		
		

}

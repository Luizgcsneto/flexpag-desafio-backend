package com.flexpag.paymentscheduler.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.flexpag.paymentscheduler.dto.FindAllDto;
import com.flexpag.paymentscheduler.dto.PaymentScheduleDto;
import com.flexpag.paymentscheduler.entities.Scheduler;
import com.flexpag.paymentscheduler.service.SchedulerService;

@RestController
public class SchedulerController {
	
	@Autowired
	private SchedulerService service;
	
	//Endpoint GET ALL
	@GetMapping("/schedules")
	public List<FindAllDto> findAll(){
		List<FindAllDto> obj = service.findAll();
		return obj;
	}
	
	//Endpoint GET por ID
	@GetMapping("/schedules/{id}")
	public ResponseEntity<Optional<Scheduler>> findId(@Valid @PathVariable Long id){
		return service.findId(id); 
	}

	//Endpoint POST
	@PostMapping("/schedules")
	public PaymentScheduleDto paymentSchedule(@Valid @RequestBody Scheduler scheduler){
		PaymentScheduleDto obj =  service.paymentSchedule(scheduler);
		return obj;
	}
	
	//Endpoint PUT
	@PutMapping("/schedules/{id}")
	public ResponseEntity<Optional<Scheduler>> updateSchedule(@Valid @PathVariable Long id, @RequestBody Scheduler scheduler){
		ResponseEntity<Optional<Scheduler>> obj = service.updateSchedule(id, scheduler);
		return obj;
	}
	
	//Endpoint DELETE
	
	@DeleteMapping("/schedules/{id}")
	public void deleteId(@PathVariable Long id){
		service.deleteId(id);
	}
	
	//Endpoint GET por Status
	@GetMapping("/schedules/search")
	public ResponseEntity<List<Scheduler>> searchStatus(@RequestParam(value="status", required = false) String status){
		
		List<Scheduler> obj = service.searchStatus(status);
		
		return ResponseEntity.ok().body(obj);
	}
	
		
		

}

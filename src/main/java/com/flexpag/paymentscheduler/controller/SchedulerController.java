package com.flexpag.paymentscheduler.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.flexpag.paymentscheduler.dto.FindIdDto;
import com.flexpag.paymentscheduler.dto.PaymentScheduleDto;
import com.flexpag.paymentscheduler.entities.Scheduler;
import com.flexpag.paymentscheduler.entities.enums.StatusPayment;
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
	public FindIdDto findId(@Valid @PathVariable Long id){
		return service.findId(id);
	}

	//Endpoint POST
	@PostMapping("/schedules")
	public ResponseEntity<PaymentScheduleDto> paymentSchedule(@Valid @RequestBody PaymentScheduleDto objDto){
		PaymentScheduleDto obj =  service.paymentSchedule(objDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}
	
	@PutMapping("/schedules/{id}")
	public ResponseEntity<Optional<Scheduler>> updateSchedule(@PathVariable Long id, @RequestBody Scheduler scheduler){
		ResponseEntity<Optional<Scheduler>> obj = service.updateSchedule(id, scheduler);
		return obj;
	}

	//Endpoint DELETE
	
	@DeleteMapping("/schedules/{id}")
	public ResponseEntity<Optional<Scheduler>> deleteId(@PathVariable Long id){
		return service.deleteId(id);
	}
	
	//Endpoint GET por Status
	@GetMapping("/schedules/search")
	public ResponseEntity<List<Scheduler>> searchStatus(@RequestParam(value="status", required = false) StatusPayment status){
		
		List<Scheduler> obj = service.searchStatus(status);
		
		return ResponseEntity.ok().body(obj);
	}
	
		
		

}

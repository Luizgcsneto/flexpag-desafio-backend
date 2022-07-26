package com.flexpag.paymentscheduler.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.flexpag.paymentscheduler.Scheduler;
import com.flexpag.paymentscheduler.repository.SchedulerRepository;

@Service
public class SchedulerService {
	
	@Autowired
	SchedulerRepository repo;
	
	//Buscar Todos
	public ResponseEntity<List<Scheduler>> buscarTodos(){
		List<Scheduler> obj = repo.findAll();
		return ResponseEntity.ok(obj);
	}
	
	//Buscar Por Id
		public ResponseEntity<Optional<Scheduler>> buscarId(@PathVariable Long id){
			Optional<Scheduler> obj = repo.findById(id);
			return ResponseEntity.ok(obj);
		}
	
	//Criar Agendamento
	public Scheduler criarAgendamento(@RequestBody Scheduler scheduler) {
		Scheduler obj = repo.save(scheduler);
		return obj;
	}
	
	//Atualizar agendamento por ID
	public Scheduler atualizarAgendamento(@PathVariable Long id, @RequestBody Scheduler scheduler) {
		Scheduler obj = repo.save(scheduler);
		return obj;
	}
	
	
	

}

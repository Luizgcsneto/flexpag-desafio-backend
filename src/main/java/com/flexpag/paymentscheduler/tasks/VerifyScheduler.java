package com.flexpag.paymentscheduler.tasks;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.flexpag.paymentscheduler.entities.Scheduler;
import com.flexpag.paymentscheduler.entities.enums.StatusPayment;
import com.flexpag.paymentscheduler.repository.SchedulerRepository;

@Component
public class VerifyScheduler {
	
	@Autowired
	private SchedulerRepository repo;
	
	@Scheduled(cron="0 0/1 * 1/1 * ?")
	public List<Scheduler> verificarAgendamento() {
		
		
		List<Scheduler> obj = repo.findByDateLessThanEqualAndStatus(LocalDateTime.now(), StatusPayment.Pending);
		
		obj.forEach(item -> {
				
				item.setStatus(StatusPayment.Paid);
				System.out.println("Rodando CRON");
				repo.save(item);
				});
		
		return obj;
		}
	}

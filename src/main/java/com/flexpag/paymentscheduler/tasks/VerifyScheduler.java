package com.flexpag.paymentscheduler.tasks;

import org.springframework.stereotype.Component;

@Component
public class VerifyScheduler {
//	
//	private SchedulerRepository repo;
//	
//	@Scheduled(cron="0/1 * 0 ? * * *")
//	public List<Scheduler> verificarAgendamento() {
//		
//		List<Scheduler> obj = repo.findAll();
//		
//		obj.forEach(item -> {
//			
//			Long id = item.getId();
//			String status = item.getStatus();
//			LocalDateTime date = item.getDate();
//			
//			if(date.isBefore(LocalDateTime.now()) || date.isEqual(LocalDateTime.now())){
//				item.setId(id);
//				item.setStatus("paid");
//				repo.save(item);
//			}
//			
//			
//		});
//		
//		return null;
//	}

}

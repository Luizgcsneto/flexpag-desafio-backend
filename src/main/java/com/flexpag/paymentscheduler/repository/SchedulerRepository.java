package com.flexpag.paymentscheduler.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexpag.paymentscheduler.entities.Scheduler;
import com.flexpag.paymentscheduler.entities.enums.StatusPayment;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long>{
	
	public List<Scheduler> findByStatus(StatusPayment status);

	public List<Scheduler> findByDateLessThanEqualAndStatus(LocalDateTime date, StatusPayment status);
	
}

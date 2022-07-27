package com.flexpag.paymentscheduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexpag.paymentscheduler.Scheduler;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long>{
	
	public List<Scheduler> findByStatus(String status);

}

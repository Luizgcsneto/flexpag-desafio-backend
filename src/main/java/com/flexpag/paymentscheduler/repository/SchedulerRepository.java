package com.flexpag.paymentscheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexpag.paymentscheduler.Scheduler;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long>{

}

package com.flexpag.paymentscheduler.repositories;

import com.flexpag.paymentscheduler.models.PaymentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<PaymentsModel,Long> { }

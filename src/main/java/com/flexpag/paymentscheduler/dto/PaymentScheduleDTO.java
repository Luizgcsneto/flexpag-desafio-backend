package com.flexpag.paymentscheduler.dto;

import java.io.Serializable;

import com.flexpag.paymentscheduler.entities.Scheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentScheduleDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	public PaymentScheduleDTO(Scheduler scheduler) {
		id = scheduler.getId();
	}

	
	
	


}

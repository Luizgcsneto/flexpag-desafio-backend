package com.flexpag.paymentscheduler.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusPayment {
	
	Pending(0),
	Paid(1);
	
	private int cod;

}

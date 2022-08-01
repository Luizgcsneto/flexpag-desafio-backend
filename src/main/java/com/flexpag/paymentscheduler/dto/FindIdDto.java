package com.flexpag.paymentscheduler.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.entities.Scheduler;
import com.flexpag.paymentscheduler.entities.enums.StatusPayment;

import lombok.Data;

@Data
public class FindIdDto {
	
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone="GMT-3")
	private LocalDateTime date;
	private BigDecimal price;
	private String description;
	private StatusPayment status;
	
	public FindIdDto(Scheduler scheduler) {
		this.id = scheduler.getId();
		this.date = scheduler.getDate();
		this.price = scheduler.getPrice();
		this.description = scheduler.getDescription();
		this.status = scheduler.getStatus();
	}
}

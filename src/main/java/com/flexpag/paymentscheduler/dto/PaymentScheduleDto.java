package com.flexpag.paymentscheduler.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.flexpag.paymentscheduler.entities.Scheduler;
import com.flexpag.paymentscheduler.entities.enums.StatusPayment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter()
@Setter
@NoArgsConstructor
@Validated
public class PaymentScheduleDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Getter(value = AccessLevel.NONE)
	@NotNull(message = "Data e hora não pode ser nulo.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone="GMT-3")
	private LocalDateTime date;
	@JsonIgnore
	@Min(value = 0, message = "Preço não pode ser menor Sque 0.")
	@NotNull(message = "Preço não pode ser nulo.")
	private BigDecimal price;
	@JsonIgnore
	private String description;
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private StatusPayment status;
	
	public PaymentScheduleDto(Scheduler scheduler) {
		id = scheduler.getId();
		date = scheduler.getDate();
		price = scheduler.getPrice();
		description = scheduler.getDescription();
		status = scheduler.getStatus();
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public LocalDateTime getDate() {
		return date;
	}

	

	
}
package com.flexpag.paymentscheduler.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class PaymentScheduleDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Future
	@Getter(value = AccessLevel.NONE)
	@NotNull(message = "Data e hora não pode ser nulo.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone="GMT-3")
	private LocalDateTime date;
	@Getter(value = AccessLevel.NONE)
	@Min(value = 0, message = "Preço não pode ser menor que 0.")
	@NotNull(message = "Preço não pode ser nulo.")
	private BigDecimal price;
	@Getter(value = AccessLevel.NONE)
	private String description;
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private StatusPayment status;
	
	public PaymentScheduleDto(Scheduler scheduler) {
		this.id = scheduler.getId();
		this.date = scheduler.getDate();
		this.price = scheduler.getPrice();
		this.description = scheduler.getDescription();
		this.status = scheduler.getStatus();
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public LocalDateTime getDate() {
		return date;
	}

 	@JsonProperty(access = Access.WRITE_ONLY)
	public BigDecimal getPrice() {
		return price;
	}

 	@JsonProperty(access = Access.WRITE_ONLY)
	public String getDescription() {
		return description;
	}
 	
 	
	

	
}
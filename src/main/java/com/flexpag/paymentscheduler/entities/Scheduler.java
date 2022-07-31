package com.flexpag.paymentscheduler.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.entities.enums.StatusPayment;

import lombok.Data;

@Data
@Entity
@Validated
public class Scheduler implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	@Future(message = "Data e hora inválida.")
	@NotNull(message = "Data e hora não pode ser nulo.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone="GMT-3")
	private LocalDateTime date;
	@Min(value = 0, message = "Preço não pode ser menor que 0.")
	@NotNull(message = "Preço não pode ser nulo.")
	private BigDecimal price;
	private String description;
	@Enumerated(EnumType.STRING)
	private StatusPayment status;
}

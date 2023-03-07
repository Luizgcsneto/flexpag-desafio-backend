package com.flexpag.paymentscheduler.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import java.math.BigDecimal;

public record PaymentsDto (@JsonProperty("_id") Long id,@NotNull BigDecimal valor) { }

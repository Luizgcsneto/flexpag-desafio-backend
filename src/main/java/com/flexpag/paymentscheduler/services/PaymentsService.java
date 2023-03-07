package com.flexpag.paymentscheduler.services;

import com.flexpag.paymentscheduler.dtos.PaymentsDto;
import com.flexpag.paymentscheduler.exception.RecordNotFoundException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public interface PaymentsService {

    public List<PaymentsDto> listPayments();

    public PaymentsDto findById(Long id);

    public PaymentsDto create(PaymentsDto paymentsDto);

    public PaymentsDto update(Long id, PaymentsDto paymentsDto);

    public void delete(Long id) ;
}

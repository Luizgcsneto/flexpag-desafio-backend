package com.flexpag.paymentscheduler.services;

import com.flexpag.paymentscheduler.dtos.PaymentsDto;
import com.flexpag.paymentscheduler.dtos.mapper.PaymentsMapper;
import com.flexpag.paymentscheduler.exception.RecordNotFoundException;
import com.flexpag.paymentscheduler.repositories.PaymentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.sun.istack.NotNull;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Collectors;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentsService {

    @Autowired
    private final PaymentsRepository paymentsRepository;
    @Autowired
    private final PaymentsMapper paymentsMapper;

    public List<PaymentsDto> listPayments()
    {
        return paymentsRepository.findAll()
                .stream().map(paymentsMapper::toDto)
                .collect(Collectors.toList());
    }


    public PaymentsDto findById(@PathVariable Long id)
    {
        return paymentsRepository.findById(id).map(paymentsMapper::toDto)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public PaymentsDto create(@NotNull PaymentsDto paymentsDto)
    {
        return paymentsMapper.toDto(paymentsRepository.save(paymentsMapper.toEntity(paymentsDto)));
    }

    public PaymentsDto update (@NotNull Long id, @NotNull PaymentsDto paymentsDto)
    {
        return paymentsRepository.findById(id).map(payments -> {
            payments.setValor(paymentsDto.valor());
            payments.setDataAtualizacao(LocalDateTime.now(ZoneId.of("GMT-3")));
            return paymentsMapper.toDto(paymentsRepository.save(payments));
        }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id)
    {
        paymentsRepository.delete(paymentsRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }





}

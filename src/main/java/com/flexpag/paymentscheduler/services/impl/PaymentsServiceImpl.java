package com.flexpag.paymentscheduler.services.impl;

import com.flexpag.paymentscheduler.dtos.PaymentsDto;
import com.flexpag.paymentscheduler.dtos.mapper.PaymentsMapper;
import com.flexpag.paymentscheduler.exception.RecordNotFoundException;
import com.flexpag.paymentscheduler.repositories.PaymentsRepository;
import com.flexpag.paymentscheduler.services.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentsServiceImpl implements PaymentsService {


    private final PaymentsRepository paymentsRepository;
    private final PaymentsMapper paymentsMapper;


    public List<PaymentsDto> listPayments() {
        return paymentsRepository.findAll()
                .stream().map(paymentsMapper::toDto)
                .collect(Collectors.toList());
    }

    public PaymentsDto findById(Long id) {
        return paymentsRepository.findById(id).map(paymentsMapper::toDto)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public PaymentsDto create(PaymentsDto paymentsDto) {
        return paymentsMapper.toDto(paymentsRepository.save(paymentsMapper.toEntity(paymentsDto)));
    }

    public PaymentsDto update(Long id, PaymentsDto paymentsDto) {
        return paymentsRepository.findById(id).map(payments -> {
            payments.setValor(paymentsDto.valor());
            payments.setDataAtualizacao(LocalDateTime.now(ZoneId.of("GMT-3")));
            return paymentsMapper.toDto(paymentsRepository.save(payments));
        }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(Long id) {
        paymentsRepository.delete(paymentsRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}

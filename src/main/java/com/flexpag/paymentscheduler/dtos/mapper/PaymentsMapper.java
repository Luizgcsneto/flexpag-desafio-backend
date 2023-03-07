package com.flexpag.paymentscheduler.dtos.mapper;

import com.flexpag.paymentscheduler.dtos.PaymentsDto;
import com.flexpag.paymentscheduler.models.PaymentsModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class PaymentsMapper {

    public PaymentsDto toDto(PaymentsModel paymentsModel){
        if (paymentsModel == null){
            return null;
        }

        return  new PaymentsDto(paymentsModel.getId(),paymentsModel.getValor());
    }

    public PaymentsModel toEntity(PaymentsDto paymentsDto){
        if (paymentsDto == null){
            return null;
        }

        PaymentsModel paymentsModel = new PaymentsModel();

        if (paymentsDto.id() != null){
            paymentsModel.setId(paymentsDto.id());
        }
        paymentsModel.setValor(paymentsDto.valor());
        paymentsModel.setData(LocalDateTime.now(ZoneId.of("GMT-3")));

        return paymentsModel;
    }
}

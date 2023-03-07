package com.flexpag.paymentscheduler.controllers;

import com.flexpag.paymentscheduler.dtos.PaymentsDto;
import com.flexpag.paymentscheduler.services.impl.PaymentsServiceImpl;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PaymentsController {

    @Autowired
    private final PaymentsServiceImpl paymentsServiceImpl;

    @GetMapping
    @ResponseBody
    public List<PaymentsDto> listPayments()
    {
        return paymentsServiceImpl.listPayments();
    }

    @GetMapping("/{id}")
    public PaymentsDto findById(@PathVariable @NotNull  Long id)
    {
        return paymentsServiceImpl.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentsDto create(@RequestBody  PaymentsDto paymentsDto)
    {
        return  paymentsServiceImpl.create(paymentsDto);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public PaymentsDto update(@PathVariable @NotNull  Long id,
                          @RequestBody  @NotNull PaymentsDto paymentsDto) {
        return paymentsServiceImpl.update(id, paymentsDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long id)
    {
        paymentsServiceImpl.delete(id);
    }
}

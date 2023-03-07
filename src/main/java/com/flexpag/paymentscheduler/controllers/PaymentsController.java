package com.flexpag.paymentscheduler.controllers;

import com.flexpag.paymentscheduler.dtos.PaymentsDto;
import com.flexpag.paymentscheduler.services.PaymentsService;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PaymentsController {

    @Autowired
    private final PaymentsService paymentsService;

    @GetMapping
    @ResponseBody
    public List<PaymentsDto> listPayments()
    {
        return paymentsService.listPayments();
    }

    @GetMapping("/{id}")
    public PaymentsDto findById(@PathVariable @NotNull  Long id)
    {
        return paymentsService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentsDto create(@RequestBody  PaymentsDto paymentsDto)
    {
        return  paymentsService.create(paymentsDto);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public PaymentsDto update(@PathVariable @NotNull  Long id,
                          @RequestBody  @NotNull PaymentsDto paymentsDto) {
        return paymentsService.update(id, paymentsDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long id)
    {
        paymentsService.delete(id);
    }
}

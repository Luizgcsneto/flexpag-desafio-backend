package com.flexpag.paymentscheduler.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.enums.TypeStatus;
import com.sun.istack.NotNull;
import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@SQLDelete(sql="Update PAYMENTS_MODEL set STATUS='DELETED' where ID=?")
@Where(clause = "STATUS = 'ACTIVE'")
public class PaymentsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone="GMT-3")
    private LocalDateTime data;

    private String status = "ACTIVE";

    @PreRemove
    public void setStaus() {

        this.status="DELETED";
    }

    public PaymentsModel(Long id, BigDecimal valor, LocalDateTime data) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.status = "ACTIVE";
    }

    public PaymentsModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = "ACTIVE";
    }
}

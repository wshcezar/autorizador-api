package com.autorizador.api.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_cartao")
public class CartaoEntity {

    @Id
    @Column(name = "numero_cartao", unique = true)
    private String numeroCartao;

    @Column(name = "senha_cartao", nullable = false)
    private String senha;

    @Column(name = "saldo_cartao", nullable = false)
    private BigDecimal saldo;

    @PrePersist
    void create() {
        saldo = BigDecimal.valueOf(500.0);
    }
}

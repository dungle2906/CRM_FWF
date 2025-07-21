package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "wallet")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Wallet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private User user;

    private BigDecimal balance = BigDecimal.ZERO;

    private String currency;

    @OneToMany()
    @JoinColumn(name = "wallet_trans_id", referencedColumnName = "id")
    private List<WalletTransaction> walletTransaction;
}
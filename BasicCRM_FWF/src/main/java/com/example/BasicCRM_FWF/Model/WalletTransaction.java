package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "wallet_transaction")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class WalletTransaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    @ToString.Exclude
    private Wallet wallet;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reference_order_id", referencedColumnName = "id")
    @ToString.Exclude
    private Order referenceOrder;

    @Lob
    private String note;

    public enum TransactionType {
        TopUp,
        Spend,
        Refund
    }
}

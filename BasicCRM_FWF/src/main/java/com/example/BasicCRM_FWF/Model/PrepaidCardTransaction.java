package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "prepaid_card_transaction")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PrepaidCardTransaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private PrepaidCard prepaidCard;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_order_id", referencedColumnName = "id")
    @ToString.Exclude
    private Order referenceOrder;

    @Lob
    private String note;

    public enum TransactionType {
        TopUp,
        Use,
        Refund
    }
}
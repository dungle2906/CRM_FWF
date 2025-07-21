package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "voucher_usages")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class VoucherUsage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Vouchers voucher;

    @OneToOne
    private Order referenceOrder;

    private BigDecimal usedAmount = BigDecimal.ZERO;

    private LocalDateTime usedAt;
}

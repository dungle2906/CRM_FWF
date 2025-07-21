package com.example.BasicCRM_FWF.Model;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "debts")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Debts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;

    private BigDecimal amount;

    private LocalDate paymentDeadline;

    @Enumerated(EnumType.STRING)
    private DebtStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_order_id", referencedColumnName = "id")
    @ToString.Exclude
    private Order referenceOrder;

    @Lob
    private String note;

    public enum DebtStatus {
        Pending,
        Paid,
        Overdue
    }
}


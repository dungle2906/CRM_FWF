package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "payment_method")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PaymentMethod extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Method paymentMethod;

    private Long amount = 0L;

    public enum Method {
        CASH,
        WALLET,
        PREPAIDCARD
    }
}

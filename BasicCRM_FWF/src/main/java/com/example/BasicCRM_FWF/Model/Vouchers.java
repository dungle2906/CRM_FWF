package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "vouchers")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Vouchers extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private BigDecimal discount;

    private BigDecimal amount;

    private String status;

    private LocalDate startDate;

    private LocalDate expiryDate;

    @Lob
    private String note;
}
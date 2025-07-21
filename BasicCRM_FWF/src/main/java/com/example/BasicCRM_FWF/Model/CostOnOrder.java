package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "cost_on_order")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CostOnOrder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Order order;

    private BigDecimal serviceCost;

    private BigDecimal surcharge;

    @OneToOne
    @JoinColumn(name = "shift_employee_id")
    private Employee shiftEmployee;

    @OneToOne
    @JoinColumn(name = "performing_staff_id")
    private Employee performingStaff;

    private BigDecimal employeeSalary;

    private BigDecimal discount;

    private BigDecimal priceIncrease;
}

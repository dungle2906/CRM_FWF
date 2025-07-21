package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CustomerType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column
    private String customerType;

    @Column(columnDefinition = "INT CHECK ('priceThreshold >= 0')")
    private BigDecimal priceThreshold;

    @Column
    private String colorPresent;

    @Column
    private String description;

    @OneToMany(mappedBy = "customerType")
    private List<User> users;
}
package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    private String firstName;

    private String lastName;

    private Character gender;

    private LocalDate dob;

    private String phoneNumber;

    private String email;

    private LocalDate hireDate;

    private String position;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @ToString.Exclude
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_id")
    @ToString.Exclude
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @Column(precision = 18, scale = 2)
    private BigDecimal salary;

    private String employmentStatus;

    @OneToOne(mappedBy = "performingStaff", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CostOnOrder performedCost;

    @OneToOne(mappedBy = "shiftEmployee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CostOnOrder shiftCost;
}

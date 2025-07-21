package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(length = 255)
    private String addressDescription;

    @Column(length = 100)
    private String addressDetail;

    @Column(length = 50)
    private String ward;

    @Column(length = 50)
    private String district;

    @Column(length = 50)
    private String province;

    @Column(length = 50)
    private String city;

    @OneToOne(mappedBy = "address")
    private User user;
}

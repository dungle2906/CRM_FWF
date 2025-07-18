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
@Table(name = "facility")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Facility extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id")
    private Integer id;

    @Column
    private String shopName;

    @Column
    private String shopType;

    @Column
    private String facility;

    @Column
    private String region;

    @Column
    private String statusOpening;

    @Column(columnDefinition = "SHORT CHECK (`price` >= 0)")
    private short totalBedService;
}

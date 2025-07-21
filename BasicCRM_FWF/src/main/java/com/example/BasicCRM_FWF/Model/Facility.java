package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    @Column(name = "id")
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
    private StatusOpening statusOpening;

    @Column(columnDefinition = "INT CHECK (`TotalServiceBed` >= 0)")
    private Integer totalBedService;

    public enum StatusOpening {
        OPENING,
        CLOSED,
        MAINTENANCE,
    }

    @OneToMany(mappedBy = "facility")
    private List<User> user;

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Employee> employees;
}

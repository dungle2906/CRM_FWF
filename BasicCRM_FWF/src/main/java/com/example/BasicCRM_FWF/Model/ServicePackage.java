package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "service_package")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ServicePackage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String serviceName;

    @Column
    private String serviceType;

    @Lob
    private String serviceDetail;
}

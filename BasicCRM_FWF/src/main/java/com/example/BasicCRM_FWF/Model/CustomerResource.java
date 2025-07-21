package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer_resource")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CustomerResource extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(length = 50)
    private String resource;

    @OneToMany(mappedBy = "customerResource")
    private List<User> users;
}

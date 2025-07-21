package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "prepaid_card")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PrepaidCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private User user;

    private BigDecimal balance = BigDecimal.ZERO;

    private String currency;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "prepaid_card_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<PrepaidCardTransaction> prepaidCardTransaction;
}

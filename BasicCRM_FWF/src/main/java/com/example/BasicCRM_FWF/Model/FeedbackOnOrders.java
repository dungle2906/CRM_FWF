package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "feedback_on_orders")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FeedbackOnOrders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;

    @Enumerated(EnumType.STRING)
    private FeedbackStatus status;

    private Integer rating;

    @Lob
    private String feedbackContent;

    @Lob
    private String description;

    public enum FeedbackStatus {
        Good,
        Bad,
        Neutral
    }
}

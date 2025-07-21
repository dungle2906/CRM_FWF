package com.example.BasicCRM_FWF.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "app_info_check")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AppInfoCheck extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String installedDevice;

    @Enumerated(EnumType.STRING)
    private AppStatus status;

    private LocalDateTime installedDate;

    public enum AppStatus {
        Installed,
        Removed,
        Inactive
    }

    @OneToOne(mappedBy = "appInfoCheck")
    private User user;
}
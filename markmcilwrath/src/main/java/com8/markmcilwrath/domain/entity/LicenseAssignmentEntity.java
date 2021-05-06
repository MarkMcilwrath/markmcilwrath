package com8.markmcilwrath.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "license_assignment")
@EqualsAndHashCode()
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LicenseAssignmentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "internal_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "assignment_id", nullable = false, unique = true)
    private String UUID;

    @OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "license_key")
    @Getter
    @Setter
    private LicenseEntity licenseEntity;

    @OneToOne  (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "user_id")
    @Getter
    @Setter
    private UserEntity userEntity ;

    @Getter
    @Setter
    @Column(name = "assignment_date", nullable = false)
    private LocalDate assignmentDate;

    @Getter
    @Setter
    @Column(name = "approved", nullable = false)
    private Boolean approved;

    // tag entity

    public LicenseAssignmentEntity (String UUID,
                                    LicenseEntity licenseEntity,
                                    UserEntity userEntity,
                                    LocalDate assignmentDate,
                                    Boolean approved)
    {
        this.UUID = UUID;
        this.licenseEntity = licenseEntity;
        this.userEntity = userEntity;
        this.assignmentDate = assignmentDate;
        this.approved = approved;
    }
}

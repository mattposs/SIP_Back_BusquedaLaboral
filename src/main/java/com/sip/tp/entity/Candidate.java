package com.sip.tp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidate extends UserData {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String currentRoleTitle;

    private String headline;
    private String profilePhoto;
    private String phone;
    private String linkedIn;

    @Column(nullable = false)
    private Boolean identityVerified = false;

    @Transient // Not stored permanently according to spec
    private String dni;

    @Column(nullable = false)
    private Integer profileCompletion = 0;
}
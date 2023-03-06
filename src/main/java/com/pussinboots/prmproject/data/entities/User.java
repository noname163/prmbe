package com.pussinboots.prmproject.data.entities;

import com.pussinboots.prmproject.data.constans.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users_tbl")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_sequence")
    private long id;

    @Column(name = "phone", nullable = false, unique = true, length = 13)
    private String phone;
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;
    @Column(name = "username", nullable = false, unique = false, length = 50)
    private String username;
    @Column(name = "first_name", nullable = false, unique = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, unique = false, length = 20)
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, unique = false, length = 10)
    private ERole role;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}

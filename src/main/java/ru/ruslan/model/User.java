package ru.ruslan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    @UniqueElements
    private String username;
    private String password;
    private boolean enabled;
    private String activationCode;

    // OneToMany relationship contain in separate table
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "verification_id")
    private VerificationToken verificationToken;

   /* @OneToOne(targetEntity = VerificationToken.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private VerificationToken verificationToken;*/
}
package com.groupone.membershipgym.entity;

import com.groupone.membershipgym.response.AdminResponse;
import com.groupone.membershipgym.response.UserResponse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.ZonedDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE users SET inactive = true WHERE admin_id=?")
@Where(clause = "inactive = false")
@Builder
public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger adminId;
    private String firstName;

    private String lastName;
    @Column(unique = true)
    private String userName;

    @Enumerated(EnumType.STRING)
    private LevelAdmin level;

    private String email;

    private String password;

    private boolean inactive = Boolean.FALSE;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public AdminResponse convertToResponse(){
        return AdminResponse.builder().adminId(this.adminId).firstName(this.firstName).
                lastName(this.lastName).userName(this.userName).level(this.level).email(this.email)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt).build();
    }

    @Override
    public String toString() {
        return "\nAdmins{" +
                "adminId=" + adminId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", inactive=" + inactive +
                ", level=" + level +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
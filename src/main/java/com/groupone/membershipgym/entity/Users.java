package com.groupone.membershipgym.entity;

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
@SQLDelete(sql = "UPDATE users SET inactive = true WHERE user_id=?")
@Where(clause = "inactive = false")
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;

    private String lastName;
    @Column(unique = true)
    private String userName;

    private String email;

    private String password;

    private boolean inactive = Boolean.FALSE;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
    public UserResponse convertToResponse(){
        return UserResponse.builder().userId(this.userId).firstName(this.firstName).
        lastName(this.lastName).userName(this.userName).email(this.email)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt).build();
    }

    @Override
    public String toString() {
        return "\nUsers{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", inactive=" + inactive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
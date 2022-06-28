package com.groupone.membershipgym.entity;

import com.groupone.membershipgym.response.BookingResponse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE class_gym SET is_deleted = true WHERE booking_id=?")
@Where(clause = "is_deleted = false")
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger booking_id;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name ="class_id")
    private ClassGym classGyms;

    private boolean isDeleted = Boolean.FALSE;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public BookingResponse convertToResponse(){
        return BookingResponse.builder().bookingId(this.booking_id).user(this.user).classGyms(this.classGyms).build();
    }

    @Override
    public String toString() {
        return "\nBooking{" +
                "booking_id=" + booking_id +
                ", user=" + user +
                ", classGyms=" + classGyms +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
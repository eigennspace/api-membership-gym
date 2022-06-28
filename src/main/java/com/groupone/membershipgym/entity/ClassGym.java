package com.groupone.membershipgym.entity;

import com.groupone.membershipgym.response.ClassGymResponse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE class_gym SET is_deleted = true WHERE class_id=?")
@Where(clause = "is_deleted = false")
@Builder
public class ClassGym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    private String className;

    private LocalDate scheduledDate;

    private LocalTime scheduledTime;

    @Enumerated(EnumType.STRING)
    private TypeGymClass typeOfClass;

    private boolean isDeleted = Boolean.FALSE;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public ClassGymResponse convertToResponse(){
        return ClassGymResponse.builder().classId(this.classId)
                .className(this.className).scheduleDate(this.scheduledDate).scheduleTime(this.scheduledTime)
                .typeOfClass(this.typeOfClass).build();
    }

    @Override
    public String toString() {
        return "\nClassGym{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", scheduledDate=" + scheduledDate +
                ", scheduledTime=" + scheduledTime +
                ", typeOfClass=" + typeOfClass +
                ", isDeleted=" + isDeleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
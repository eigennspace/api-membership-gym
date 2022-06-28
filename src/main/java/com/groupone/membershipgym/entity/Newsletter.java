package com.groupone.membershipgym.entity;

import com.groupone.membershipgym.response.NewsletterResponse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Newsletter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsletterId;

    private String title;

    private String slug;

    private String category;

    private String body;

    @ManyToOne
    @JoinColumn(name ="admin_id")
    private Admins admin;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public NewsletterResponse convertToResponse(){
        return NewsletterResponse.builder().newsletterId(this.newsletterId)
                .title(this.title).slug(this.slug).category(this.category)
                .body(this.body).adminId(this.admin.getAdminId()).createdAt(this.createdAt)
                .updatedAt(this.updatedAt).build();
    }
}
package com.groupone.membershipgym.response;

import com.groupone.membershipgym.entity.Admins;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsletterResponse {

    private Long newsletterId;

    private String title;

    private String slug;

    private String category;

    private String body;

    private BigInteger adminId;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
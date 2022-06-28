package com.groupone.membershipgym.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsletterRequest {
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Slug cannot be empty")
    private String slug;

    @NotEmpty(message = "Category cannot be empty")
    private String category;

    @NotEmpty(message = "Body cannot be empty")
    private String body;

    @NotEmpty(message = "Admin Id cannot be empty")
    private BigInteger adminId;

}
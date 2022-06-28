package com.groupone.membershipgym.service;

import com.groupone.membershipgym.entity.Newsletter;
import com.groupone.membershipgym.request.NewsletterRequest;
import com.groupone.membershipgym.utils.DataException;

import java.util.List;

public interface NewsletterService {
    List<Newsletter> getAllNewsletter();

    Newsletter createNewsletter(Newsletter newsletter, NewsletterRequest request);

    Newsletter getOneNewsletter(Long id) throws DataException;

    Newsletter updateNewsletter(Newsletter newsletter) throws DataException;

    void deleteNewsletter(Long id) throws DataException;


}
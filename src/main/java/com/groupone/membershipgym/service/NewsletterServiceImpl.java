package com.groupone.membershipgym.service;

import com.groupone.membershipgym.entity.Admins;
import com.groupone.membershipgym.entity.Newsletter;
import com.groupone.membershipgym.repository.AdminRepository;
import com.groupone.membershipgym.repository.NewsletterRepository;
import com.groupone.membershipgym.request.NewsletterRequest;
import com.groupone.membershipgym.utils.DataException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NewsletterServiceImpl implements NewsletterService {

    private final NewsletterRepository newsletterRepository;

    private final AdminRepository adminRepository;

    @Override
    public List<Newsletter> getAllNewsletter() {
        return this.newsletterRepository.findAll();
    }

    @Override
    public Newsletter createNewsletter(Newsletter newsletter, NewsletterRequest request) {
        Admins admins = this.adminRepository.findByAdminId(request.getAdminId());
        newsletter.setAdmin(admins);
        newsletter.setTitle(request.getTitle());
        newsletter.setSlug(request.getSlug());
        newsletter.setCategory(request.getCategory());
        newsletter.setBody(request.getBody());

        newsletter.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tokyo")));
        newsletter.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tokyo")));

        return this.newsletterRepository.save(newsletter);
    }

    @Override
    public Newsletter getOneNewsletter(Long id) throws DataException {
        Optional<Newsletter> optional = this.newsletterRepository.findById(id);
        if (optional.isEmpty()){
            throw new DataException("Newsletter is not found");
        }
        return optional.get();
    }

    @Override
    public Newsletter updateNewsletter(Newsletter newsletter) throws DataException {
        Newsletter updateNewsletter = this.getOneNewsletter(newsletter.getNewsletterId());

        return this.newsletterRepository.save(updateNewsletter);
    }

    @Override
    public void deleteNewsletter(Long id) throws DataException {
        Newsletter deleteNewsletter = this.getOneNewsletter(id);

        this.newsletterRepository.delete(deleteNewsletter);
    }
}
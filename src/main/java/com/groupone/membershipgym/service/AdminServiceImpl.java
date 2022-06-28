package com.groupone.membershipgym.service;

import com.groupone.membershipgym.entity.Admins;
import com.groupone.membershipgym.repository.AdminRepository;
import com.groupone.membershipgym.utils.DataException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    @Override
    public List<Admins> getAllAdmins() {
        return this.adminRepository.findAll();
    }

    @Override
    public Admins saveAdmin(Admins admin) {

        admin.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tokyo")));
        admin.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tokyo")));

        return this.adminRepository.save(admin);
    }

    @Override
    public Admins getOneAdmin(BigInteger id) throws DataException {
        Optional<Admins> optionalAdmin = this.adminRepository.findById(id);
        if (optionalAdmin==null) {
            throw new DataException("Admin is not found");
        }
        return optionalAdmin.get();
    }

    @Override
    public Admins updateAdmin(Admins admin) throws DataException {
        Admins updatedAdmin = this.getOneAdmin(admin.getAdminId());

        return this.adminRepository.save(updatedAdmin);
    }

    @Override
    public void deleteAdmin(BigInteger id) throws DataException {
        Optional<Admins> optionalAdmin = this.adminRepository.findById(id);
        if (optionalAdmin==null) {
            throw new DataException("Admin is not found");
        }
        this.adminRepository.delete(optionalAdmin.get());
    }
}
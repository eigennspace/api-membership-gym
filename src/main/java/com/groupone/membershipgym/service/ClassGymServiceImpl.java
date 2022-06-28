package com.groupone.membershipgym.service;

import com.groupone.membershipgym.entity.ClassGym;
import com.groupone.membershipgym.repository.ClassGymRepository;
import com.groupone.membershipgym.utils.DataException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassGymServiceImpl implements ClassGymService{

    private final ClassGymRepository classGymRepository;

    @Override
    public List<ClassGym> getAllClassesGym() {
        return this.classGymRepository.findAll();
    }

    @Override
    public ClassGym getOneClassGym(Long id) throws DataException {
        Optional<ClassGym> optionalClassGym = this.classGymRepository.findById(id);
        if (optionalClassGym.isEmpty()){
            throw new DataException("Class of Gym is not found");
        }
        return optionalClassGym.get();
    }

    @Override
    public ClassGym saveClassGym(ClassGym classGym) {

        classGym.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tokyo")));
        classGym.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tokyo")));

        return this.classGymRepository.save(classGym);
    }

    @Override
    public ClassGym updateClassGym(ClassGym classGym) throws DataException {
        this.getOneClassGym(classGym.getClassId());

        return this.classGymRepository.save(classGym);
    }

    @Override
    public void deleteClassGym(Long id) throws DataException {
        ClassGym classGym = this.getOneClassGym(id);

        this.classGymRepository.delete(classGym);
    }
}
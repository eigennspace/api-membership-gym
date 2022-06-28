package com.groupone.membershipgym.service;

import com.groupone.membershipgym.entity.Users;
import com.groupone.membershipgym.repository.UserRepository;
import com.groupone.membershipgym.utils.DataException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Users getOneUser(Long id) throws DataException {
        Optional<Users> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new DataException("User is not found");
        }
        return optionalUser.get();
    }

    @Override
    public Users saveUser(Users users) throws DataException {
        users.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tokyo")));
        users.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tokyo")));

        if(this.userRepository.existsCurrentUserByUserName(users.getUserName())){
            throw new DataException("UserName Already Exist, try another one");
        }

        return this.userRepository.save(users);
    }

    @Override
    public Users updateUser(Users users) throws DataException {
        this.getOneUser(users.getUserId());

        return this.userRepository.save(users);
    }

    @Override
    public void deleteUser(Long id) throws DataException {
        Optional<Users> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new DataException("User is not found");
        }
        this.userRepository.delete(optionalUser.get());
    }
}
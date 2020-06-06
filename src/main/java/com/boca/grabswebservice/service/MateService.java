package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.Mate;
import com.boca.grabswebservice.repository.MateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MateService {

    @Autowired
    private MateRepository mateRepository;

    public List<Mate> getAll(){
        return mateRepository.findAll();
    }

    public Mate get(long id){
        return mateRepository.findById(id).get();
    }

    public void save(Mate mate){
        mateRepository.save(mate);
    }

    public void delete (Long id){
        mateRepository.deleteById(id);
    }

    public void softDelete(Integer deleted_by, long id){ mateRepository.softDelete(deleted_by, new Date(),id); }

    public void updateStatus(String status, long id){ mateRepository.updateStatus(status,id); }

    public boolean isLicenseExisting(String license_no){
        if (mateRepository.getExistingLicenseNo(license_no)==null){
            return false;
        }
        return true;
    }
    public boolean isEmailExisting(String email){
        if (mateRepository.getExistingEmail(email)==null){
            return false;
        }
        return true;
    }

    public List<Mate> getAllByStatus(String status) {
        return mateRepository.getAllByStatus(status);
    }

    public Mate getByEmail(String currentUserEmail) {
        return mateRepository.getByEmail(currentUserEmail);
    }

    public Mate getAllUserDetails(long id) {
        return mateRepository.getAllUserDetails(id);
    }

    public Mate getUserProfile(Long id){
        return mateRepository.getUserProfile(id);
    }


    public Mate getUserProfile(String email){
        return mateRepository.getUserProfileByEmail(email);
    }

    public List<Mate> findAllByOrderByIdDesc(){
        return mateRepository.findAllByOrderByIdDesc();
    }
}

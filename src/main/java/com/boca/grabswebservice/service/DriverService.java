package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.Driver;
import com.boca.grabswebservice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public List<Driver> getAll(){
        return driverRepository.findAll();
    }

    public Driver get(long id){
        return driverRepository.findById(id).get();
    }

    public void save(Driver driver){
        driverRepository.save(driver);
    }

    public void delete (Long id){
        driverRepository.deleteById(id);
    }

    public void softDelete(Integer deleted_by, long id){ driverRepository.softDelete(deleted_by, new Date(),id); }

    public void updateStatus(String status, long id){ driverRepository.updateStatus(status,id); }

    public boolean isLicenseExisting(String license_no){
        if (driverRepository.getExistingLicenseNo(license_no)==null){
            return false;
        }
        return true;
    }
    public boolean isEmailExisting(String email){
        if (driverRepository.getExistingEmail(email)==null){
            return false;
        }
        return true;
    }

    public List<Driver> getAllByStatus(String status) {
        return driverRepository.getByStatus(status);
    }

    public Driver getByEmail(String currentUserEmail) {
       return driverRepository.getByEmail(currentUserEmail);
    }

    public Driver getAllUserDetails(Long id){
        return  driverRepository.getAllUserDetails(id);
    }

    public Driver getUserProfile(Long id){
        return driverRepository.getUserProfile(id);
    }
    public Driver getUserProfileByEmail(String email){
        return driverRepository.getUserProfileByEmail(email);
    }

    public List<Driver> findAllByOrderByIdDesc(){
        return driverRepository.findAllByOrderByIdDesc();
    }

}

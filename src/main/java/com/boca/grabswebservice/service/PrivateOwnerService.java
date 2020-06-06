package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.PrivateOwner;
import com.boca.grabswebservice.repository.PrivateOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PrivateOwnerService {

    @Autowired
    private PrivateOwnerRepository privateOwnerRepository;

    public List<PrivateOwner> getAll(){
        return privateOwnerRepository.findAll();
    }

    public PrivateOwner get(long id){
        return privateOwnerRepository.findById(id).get();
    }

    public void save(PrivateOwner privateOwner){
        privateOwnerRepository.save(privateOwner);
    }

    public void delete (Long id){
        privateOwnerRepository.deleteById(id);
    }

    public void softDelete(Integer deleted_by, long id){ privateOwnerRepository.softDelete(deleted_by, new Date(),id); }

    public void updateStatus(String status, long id){ privateOwnerRepository.updateStatus(status,id); }

    public boolean isEmailExisting(String email){
        if (privateOwnerRepository.getExistingEmail(email)==null){
            return false;
        }
        return true;
    }

    public List<PrivateOwner> getAllByStatus(String status) {
        return privateOwnerRepository.getByStatus(status);
    }

    public PrivateOwner getByEmail(String currentUserEmail) {
        return privateOwnerRepository.getByEmail(currentUserEmail);
    }

    public PrivateOwner getAllUserDetails(Long id){
        return  privateOwnerRepository.getAllUserDetails(id);
    }

    public PrivateOwner getUserProfile(Long id){
        return privateOwnerRepository.getUserProfile(id);
    }


    public PrivateOwner getUserProfileByEmail(String email){
        return privateOwnerRepository.getUserProfileByEmail(email);
    }

    public List<PrivateOwner> getAllPrivateOwners(){
        return privateOwnerRepository.getAllPrivateOwners();
    }


    public PrivateOwner getOnePrivateOwner(Long id) {
        return privateOwnerRepository.getOnePrivateOwner(id);
    }
}

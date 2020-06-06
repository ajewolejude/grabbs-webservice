package com.boca.grabswebservice.service;
import com.boca.grabswebservice.model.CompanyOwner;
import com.boca.grabswebservice.repository.CompanyOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CompanyOwnerService {

    @Autowired
    private CompanyOwnerRepository companyOwnerRepository;

    public List<CompanyOwner> getAll(){
        return companyOwnerRepository.findAll();
    }

    public CompanyOwner get(long id){
        return companyOwnerRepository.findById(id).get();
    }

    public void save(CompanyOwner companyOwner){
        companyOwnerRepository.save(companyOwner);
    }

    public void delete (Long id){
        companyOwnerRepository.deleteById(id);
    }

    public void softDelete(Integer deleted_by, long id){ companyOwnerRepository.softDelete(deleted_by, new Date(),id); }

    public void updateStatus(String status, long id){ companyOwnerRepository.updateStatus(status,id); }

    public boolean isEmailExisting(String email){
        if (companyOwnerRepository.getExistingEmail(email)==null){
            return false;
        }
        return true;
    }

    public boolean isContactEmailExisting(String email){
        if (companyOwnerRepository.getExistingContactEmail(email)==null){
            return false;
        }
        return true;
    }

    public boolean isRCExisting(String email){
        if (companyOwnerRepository.getExistingRC(email)==null){
            return false;
        }
        return true;
    }

    public List<CompanyOwner> getAllByStatus(String status) {
        return companyOwnerRepository.getByStatus(status);
    }

    public CompanyOwner getByEmail(String currentUserEmail) {
        return companyOwnerRepository.getByEmail(currentUserEmail);
    }

    public CompanyOwner getAllUserDetails(Long id){
        return  companyOwnerRepository.getAllUserDetails(id);
    }
    public CompanyOwner getAllUserDetailsByEmail(String email){
        return  companyOwnerRepository.getAllUserDetailsByEmail(email);
    }

    public CompanyOwner getUserProfile(Long id){
        return companyOwnerRepository.getUserProfile(id);
    }

    public List<CompanyOwner> getAllCompanyOwners(){
        return companyOwnerRepository.getAllCompanyOwners();
    }
    public CompanyOwner getOneCompanyOwner(Long id){
        return companyOwnerRepository.getOneCompanyOwner(id);
    }

}

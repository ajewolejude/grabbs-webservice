package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.Merchant;
import com.boca.grabswebservice.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    public List<Merchant> getAll(){
        return merchantRepository.findAll();
    }

    public Merchant get(long id){
        return merchantRepository.findById(id).get();
    }

    public void save(Merchant merchant){
        merchantRepository.save(merchant);
    }

    public void delete (Long id){
        merchantRepository.deleteById(id);
    }

    public void softDelete(Integer deleted_by, long id){ merchantRepository.softDelete(deleted_by, new Date(),id); }

    public void updateStatus(String status, long id){ merchantRepository.updateStatus(status,id); }

    public boolean isEmailExisting(String email){
        if (merchantRepository.getExistingEmail(email)==null){
            return false;
        }
        return true;
    }

    public Merchant getUserProfile(Long id){
        return merchantRepository.getUserProfile(id);
    }

    public Merchant getUserProfileByEmail(String email){
        return merchantRepository.getUserProfileByEmail(email);
    }

    public List<Merchant> findAllByOrderByIdDesc(){
        return merchantRepository.findAllByOrderByIdDesc();
    }


}

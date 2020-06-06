package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.Tyre;
import com.boca.grabswebservice.repository.TyreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TyreService {

    @Autowired
    private TyreRepository tyreRepository;

    public List<Tyre> getAll(){
        return tyreRepository.findAll();
    }

    public Tyre get(long id){
        return tyreRepository.findById(id).get();
    }

    public void save(Tyre tyre){
        tyreRepository.save(tyre);
    }

    public void delete (Long id){
        tyreRepository.deleteById(id);
    }

    public List<Tyre> getTyres(Long truck_id){
        return tyreRepository.getTyres(truck_id);
    }

    public List<Tyre> getTyresByStatus(String status){
        return tyreRepository.getTyresByStatus(status);
    }

    public List<Tyre> getTyresWithTruck(){
        return tyreRepository.getTyresWithTruck();
    }

    public boolean isSerialNoExisting(String serial_number){
        if (tyreRepository.getExistingSerialNo(serial_number)==null){
            return false;
        }
        return true;
    }

    public List<Tyre> findAllByOrderByIdDesc(){
        return tyreRepository.findAllByOrderByIdDesc();
    }


}

package com.boca.grabswebservice.service;
import com.boca.grabswebservice.model.Accessory;
import com.boca.grabswebservice.repository.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccesoryService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    public List<Accessory> getAll() {
        return accessoryRepository.findAll();
    }

    public Accessory get(long id) {
        return accessoryRepository.findById(id).get();
    }

    public void save(Accessory accessory) {
        accessoryRepository.save(accessory);
    }

    public void delete(Long id) {
        accessoryRepository.deleteById(id);
    }


    public List<Accessory> getTruckAccessory(Long truck_id) {
        return accessoryRepository.getTruckAccessory(truck_id);
    }
}

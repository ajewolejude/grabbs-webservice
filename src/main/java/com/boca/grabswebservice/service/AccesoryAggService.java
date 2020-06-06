package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.AccessoryAgg;
import com.boca.grabswebservice.repository.AccessoryAggRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccesoryAggService {

    @Autowired
    private AccessoryAggRepository accessoryAggRepository;

    public List<AccessoryAgg> getAll() {
        return accessoryAggRepository.findAll();
    }

    public AccessoryAgg get(long id) {
        return accessoryAggRepository.findById(id).get();
    }

    public void save(AccessoryAgg accessoryAgg) {
        accessoryAggRepository.save(accessoryAgg);
    }

    public void delete(Long id) {
        accessoryAggRepository.deleteById(id);
    }

    public List<AccessoryAgg> getAvailableAccessory() {
        return accessoryAggRepository.getAvailableAccessory();
    }


    public AccessoryAgg getByType(String type) {

        return accessoryAggRepository.getByType(type);
    }


    public Long getById(int i) {
        return accessoryAggRepository.getById(i);
    }

    public AccessoryAgg getAllById(int i) {
        return accessoryAggRepository.getAllById(i);
    }

    public void updateById(int i, Long agg) {
        accessoryAggRepository.updateById((long) i, agg);
    }

}

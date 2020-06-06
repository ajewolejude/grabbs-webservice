package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.Repair;
import com.boca.grabswebservice.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    public List<Repair> getAll() {
        return repairRepository.findAll();
    }

    public Repair get(long id) {
        return repairRepository.findById(id).get();
    }

    public void save(Repair repair) {
        repairRepository.save(repair);
    }

    public void delete(Long id) {
        repairRepository.deleteById(id);
    }

    public void softDelete(Integer deleted_by, Date date, long id){
        repairRepository.softDelete(deleted_by, date,id);
    }

    public List<Repair> getAllRepairsByJobCard(Long jobcard_id) {
        return repairRepository.getAllRepairsByJobCard(jobcard_id);
    }



}

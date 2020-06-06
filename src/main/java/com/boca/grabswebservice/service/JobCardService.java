package com.boca.grabswebservice.service;



import com.boca.grabswebservice.model.JobCard;
import com.boca.grabswebservice.repository.JobCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class JobCardService {

    @Autowired
    private JobCardRepository jobCardRepository;

    public List<JobCard> getAll() {
        return jobCardRepository.findAll();
    }

    public JobCard get(long id) {
        return jobCardRepository.findById(id).get();
    }

    public void softDelete(Integer deleted_by, Date date, long id){ jobCardRepository.softDelete(deleted_by, date,id); }


    public void save(JobCard jobCard) {
        jobCardRepository.save(jobCard);
    }

    public void delete(Long id) {
        jobCardRepository.deleteById(id);
    }


    public List<JobCard> getAllJobCardsByTruckId(Long truck_id) {
        return jobCardRepository.getAllJobCardsByTruckId(truck_id);
    }


    public BigDecimal selectTotalMaintenanceThisMonth(int month, int year){
        return jobCardRepository.selectTotalMaintenanceThisMonth(month, year);
    }


    public BigDecimal selectTotalMaintenanceThisMonth(Long truck_id,int month, int year){
        return jobCardRepository.selectTotalMaintenanceThisMonth(truck_id,month, year);
    }

    public BigDecimal selectTotalMaintenanceThisMonth(Long[] truck_id_list,int month, int year){
        return jobCardRepository.selectTotalMaintenanceThisMonth(truck_id_list,month, year);
    }

    public BigDecimal selectTotalMaintenance(){
        return jobCardRepository.selectTotalMaintenance();
    }

    public BigDecimal selectTotalMaintenance(Long truck_id){
        return jobCardRepository.selectTotalMaintenance(truck_id);
    }

    public BigDecimal selectTotalMaintenance(Long[] truck_id_list){
        return jobCardRepository.selectTotalMaintenance(truck_id_list);
    }

}

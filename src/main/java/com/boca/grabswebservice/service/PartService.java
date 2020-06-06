package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.Part;
import com.boca.grabswebservice.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PartService {

    @Autowired
    private PartRepository partRepository;

    public List<Part> getAll() {
        return partRepository.findAll();
    }

    public Part get(long id) {
        return partRepository.findById(id).get();
    }

    public void save(Part part) {
        partRepository.save(part);
    }

    public void delete(Long id) {
        partRepository.deleteById(id);
    }


    public void softDelete(Integer deleted_by, Date date, long id){
        partRepository.softDelete(deleted_by, date,id);
    }

    public List<Part> getAllPartsByJobCard(Long jobcard_id) {
        return partRepository.getAllPartsByJobCard(jobcard_id);
    }

    public BigDecimal getTotalAmount(Long jobcard_id){
        return partRepository.selectTotalAmount(jobcard_id);
    }
    public BigDecimal selectTotalOverallAmount(){
        return partRepository.selectTotalOverallAmount();
    }

    public BigDecimal selectTotalOverallAmount(Long truck_id){
        return partRepository.selectTotalOverallAmount(truck_id);
    }


    public BigDecimal selectTotalOverallAmount(Long[] truck_id_list){
        return partRepository.selectTotalOverallAmount(truck_id_list);
    }

    public BigDecimal selectTotalOverallAmountThisMonth(int month, int year){
        return partRepository.selectTotalOverallAmountThisMonth(month, year);
    }

    public BigDecimal selectTotalOverallAmountThisMonth(Long[] truck_id_list, int month, int year){
        return partRepository.selectTotalOverallAmountThisMonth(truck_id_list,month, year);
    }



    public BigDecimal selectTotalOverallAmountThisMonth(Long truck_id,int month, int year){
        return partRepository.selectTotalOverallAmountThisMonth(truck_id, month, year);
    }


}

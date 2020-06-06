package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.TruckTransaction;
import com.boca.grabswebservice.repository.TruckTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TruckTransactionService {

    @Autowired
    private TruckTransactionRepository truckTransactionRepository;

    public List<TruckTransaction> getAll() {
        return truckTransactionRepository.findAll();
    }

    public TruckTransaction get(long id) {
        return truckTransactionRepository.findById(id).get();
    }

    public void save(TruckTransaction truckTransaction) {
        truckTransactionRepository.save(truckTransaction);
    }

    public void delete(Long id) {
        truckTransactionRepository.deleteById(id);
    }

    public TruckTransaction getCurrentTruckTrans(Long truck_id) {
        return truckTransactionRepository.getCurrentTruckTrans(truck_id);
    }

    public List<TruckTransaction> findAllByOrderByIdDesc(){
        return truckTransactionRepository.findAllByOrderByIdDesc();
    }

}

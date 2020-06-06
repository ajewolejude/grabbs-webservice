package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.TruckRequest;
import com.boca.grabswebservice.repository.TruckRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TruckRequestService {

    @Autowired
    private TruckRequestRepository truckRequestRepository;

    public List<TruckRequest> getAll(){
        return truckRequestRepository.findAll();
    }

    public TruckRequest get(long id){
        return truckRequestRepository.findById(id).get();
    }

    public void save(TruckRequest truckRequest){
        truckRequestRepository.save(truckRequest);
    }

    public void delete (Long id){
        truckRequestRepository.deleteById(id);
    }

    public List<TruckRequest> getRequestByMerchantId(Long id) {
        return truckRequestRepository.getRequestByMerchantId(id);
    }

    public List<TruckRequest> findAllByOrderByIdDesc(){
        return truckRequestRepository.findAllByOrderByIdDesc();
    }
}

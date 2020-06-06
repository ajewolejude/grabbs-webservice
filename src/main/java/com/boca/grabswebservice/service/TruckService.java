package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.Truck;
import com.boca.grabswebservice.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TruckService {

    @Autowired
    private TruckRepository truckRepository;

    public List<Truck> getAll() {
        return truckRepository.findAll();
    }

    public Truck get(long id) {
        return truckRepository.findById(id).get();
    }

    public void save(Truck truck) {
        truckRepository.save(truck);
    }

    public void delete(Long id) {
        truckRepository.deleteById(id);
    }

    public void softDelete(Integer deleted_by, long id) {
        truckRepository.softDelete(deleted_by, id);
    }

    public boolean isLicenseExisting(String license_no) {
        if (truckRepository.getExistingLicenseNo(license_no) == null) {
            return false;
        }
        return true;
    }

    public List<Truck> getMyTrucks(Long id, String role) {
        return truckRepository.getMyTrucks(id, role);
    }


    public void updateStatus(String status, Long id) {
        truckRepository.updateStatus(status, id);
    }

    public void updateTripStatus(String trip_status, Long id) {
        truckRepository.updateTripStatus(trip_status, id);
    }

    public List<Truck> getAllIdleActiveTrucks(){
        return truckRepository.getAllIdleActiveTrucks();
    }

    public List<Truck> findAllByOrderByIdDesc(){
        return truckRepository.findAllByOrderByIdDesc();
    }
}

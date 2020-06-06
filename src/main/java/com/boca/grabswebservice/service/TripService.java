package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.Trip;
import com.boca.grabswebservice.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getAll(){
        return tripRepository.findAll();
    }

    public Trip get(long id){
        return tripRepository.findById(id).get();
    }

    public void save(Trip trip){
        tripRepository.save(trip);
    }

    public void delete (Long id){
        tripRepository.deleteById(id);
    }

    public List<Trip> getAllByStatus(String status){
        return tripRepository.getAllByStatus(status);
    }


    public List<Trip> getAllByStatusAndDriver(Long id, String status) {
        return tripRepository.getAllByStatusAndDriver(id, status);
    }

    public List<Trip> getAllByDriver(Long id) {
        return tripRepository.getAllByDriver(id);
    }

    public List<Trip> getAllTripChargeForTruck(Long truck_id) {
        return tripRepository.getAllTripChargeForTruck(truck_id);
    }

    public List<Trip> findAllByOrderByIdDesc(){
        return tripRepository.findAllByOrderByIdDesc();
    }

    public BigDecimal selectTotalTripChargeByStatus(String status){
        return tripRepository.selectTotalTripChargeByStatus(status);
    }
    public BigDecimal selectTotalTripCharge(){
        return tripRepository.selectTotalTripCharge();
    }

    public BigDecimal selectTotalTripCharge(Long truck_id){
        return tripRepository.selectTotalTripCharge(truck_id);
    }

    public BigDecimal selectTotalTripCharge(Long[] truck_id_list){
        return tripRepository.selectTotalTripCharge(truck_id_list);
    }

    public BigDecimal selectTotalTripChargeThisMonth(int month, int year){
        return tripRepository.selectTotalTripChargeThisMonth( month,  year);
    }


    public BigDecimal selectTotalTripChargeThisMonth(Long[] truck_id_list, int month, int year){
        return tripRepository.selectTotalTripChargeThisMonth( truck_id_list, month,  year);
    }

    public BigDecimal selectTotalTripChargeThisMonthForTruck(Long id, int month, int year){
        return tripRepository.selectTotalTripChargeThisMonthForTruck( id, month,  year);
    }

//    public Sum selectTotalTripChargeThisMonthAndTotal(int month, int year){
//        int i=0;
//        return tripRepository.selectTotalTripChargeThisMonthAndTotal( month,  year);
//    }
}

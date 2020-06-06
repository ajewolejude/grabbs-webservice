package com.boca.grabswebservice.service;

import com.boca.grabswebservice.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boca.grabswebservice.model.Process;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    public List<Process> getAll(){
        return processRepository.findAll();
    }

    public Process get(long id){
        return processRepository.findById(id).get();
    }

    public void save(Process process){
        processRepository.save(process);
    }

    public void delete (Long id){
        processRepository.deleteById(id);
    }

    public Process getRecentProcessByTripId(Long id){
        return processRepository.getRecentProcessByTripId(id);
    }


    public List<Process> getAllProcessesByTripId(long id) {
        return processRepository.getAllProcessesByTripId(id);
    }

}

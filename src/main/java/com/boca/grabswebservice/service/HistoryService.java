package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.History;
import com.boca.grabswebservice.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public History get(long id){
       return historyRepository.findById(id).get();
    }

    public List<History> getAll(){
        return historyRepository.findAll();
    }

    public void save(History history){
        historyRepository.save(history);
    }

    public void delete(long id){
        historyRepository.deleteById(id);
    }

    public  List<History> getHistory(long type_id, String type){
        return historyRepository.getHistory(type_id,type);
    }

    public List<History> getHistoryBySubtype(Long id, String type, String sub_type) {
        return historyRepository.getHistoryBySubtype(id,type,sub_type);
    }

    public List<History> getHistoryByRepair(Long id, String type, String sub_type1, String sub_type2) {
        return historyRepository.getHistoryByRepair(id,type,sub_type1,sub_type2);
    }
}

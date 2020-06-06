package com.boca.grabswebservice.service;

import com.boca.grabswebservice.model.Expense;
import com.boca.grabswebservice.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAll(){
        return expenseRepository.findAll();
    }

    public Expense get(long id){
        return expenseRepository.findById(id).get();
    }

    public void save(Expense expense){
        expenseRepository.save(expense);
    }

    public void delete (Long id){
        expenseRepository.deleteById(id);
    }


    public List<Expense> getAllTripExpenseByTripId(Long id) {
        return expenseRepository.getAllTripExpenseByTripId(id);
    }

    public BigDecimal selectTotalExpenses(){
        return expenseRepository.selectTotalExpenses();
    }

    public BigDecimal selectTotalExpenses(Long truck_id){
        return expenseRepository.selectTotalExpenses( truck_id);
    }

    public BigDecimal selectTotalExpenses(Long[] truck_id_list){
        return expenseRepository.selectTotalExpenses( truck_id_list);
    }

    public BigDecimal selectTotalExpensesThisMonth(int month, int year){
        return expenseRepository.selectTotalExpensesThisMonth( month,  year);
    }

    public BigDecimal selectTotalExpensesThisMonth(Long truck_id, int month, int year){
        return expenseRepository.selectTotalExpensesThisMonth(truck_id, month,  year);
    }
    public BigDecimal selectTotalExpensesThisMonth(Long[] truck_id_list, int month, int year){
        return expenseRepository.selectTotalExpensesThisMonth(truck_id_list, month,  year);
    }
}

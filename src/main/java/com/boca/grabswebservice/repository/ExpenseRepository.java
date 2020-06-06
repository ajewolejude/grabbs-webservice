package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(value = "select * from expense e where e.trip_id= :trip_id ORDER BY e.id DESC",
            nativeQuery=true)
    List<Expense> getAllTripExpenseByTripId(Long trip_id);



    @Query(value = "SELECT SUM(CASE WHEN t.status ='End' THEN e.amount ELSE 0 END)  FROM expense e inner join process p on p.id =  e.process_id inner join  trip t on t.id = p.trip_id", nativeQuery = true)
    BigDecimal selectTotalExpenses();

    @Query(value = "SELECT SUM(CASE WHEN t.status ='End' and t.truck_id=:truck_id THEN e.amount ELSE 0 END)  FROM expense e inner join process p on p.id =  e.process_id inner join  trip t on t.id = p.trip_id", nativeQuery = true)
    BigDecimal selectTotalExpenses(Long truck_id);

    @Query(value = "SELECT SUM(CASE WHEN t.status ='End' and t.truck_id in :truck_id_list THEN e.amount ELSE 0 END)  FROM expense e inner join process p on p.id =  e.process_id inner join  trip t on t.id = p.trip_id", nativeQuery = true)
    BigDecimal selectTotalExpenses(Long[] truck_id_list);

    @Query(value = "SELECT SUM(CASE WHEN t.status ='End' and MONTH(e.date_of_reg)=:month and YEAR(e.date_of_reg)=:year THEN e.amount ELSE 0 END)  FROM expense e inner join process p on p.id =  e.process_id inner join  trip t on t.id = p.trip_id", nativeQuery = true)
    BigDecimal selectTotalExpensesThisMonth(int month, int year);

    @Query(value = "SELECT SUM(CASE WHEN t.status ='End' and t.truck_id=:truck_id  and MONTH(e.date_of_reg)=:month and YEAR(e.date_of_reg)=:year THEN e.amount ELSE 0 END)  FROM expense e inner join process p on p.id =  e.process_id inner join  trip t on t.id = p.trip_id", nativeQuery = true)
    BigDecimal selectTotalExpensesThisMonth(Long truck_id, int month, int year);

    @Query(value = "SELECT SUM(CASE WHEN t.status ='End'  and t.truck_id in :truck_id_list and MONTH(e.date_of_reg)=:month and YEAR(e.date_of_reg)=:year THEN e.amount ELSE 0 END)  FROM expense e inner join process p on p.id =  e.process_id inner join  trip t on t.id = p.trip_id", nativeQuery = true)
    BigDecimal selectTotalExpensesThisMonth(Long[] truck_id_list, int month, int year);

}
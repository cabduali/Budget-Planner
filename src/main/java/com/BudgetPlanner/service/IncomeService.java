package com.BudgetPlanner.service;
import com.BudgetPlanner.entity.Income;
import com.BudgetPlanner.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {
public  final  IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }


    public void insertIncome(Income income){
        incomeRepository.save(income);

    }


    public  void deleteIncome(Long id){
   incomeRepository.deleteById(id);
    }
    public Income getIncomeByid(Long id){
        return  incomeRepository.findById(id).orElse(null);
    }

    public List<Income> getaincomeTable(){
        return  incomeRepository.findAll();
    }
}

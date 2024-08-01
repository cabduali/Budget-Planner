package com.BudgetPlanner.controller;

import com.BudgetPlanner.entity.Budget;
import com.BudgetPlanner.entity.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.BudgetPlanner.service.IncomeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IncomeController {
  private  final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/addincome")
    public  String saveIncome(@ModelAttribute Income income){
        incomeService.insertIncome(income);
        return  "redirect:/showIncome";
    }



  @GetMapping("/showIncome")
    public String showtable(Model model){
      List<Income> inc=incomeService.getaincomeTable();
      model.addAttribute("icomes",inc);
        return "incomeTable";
    }

  @RequestMapping("/deleteIncome/{id}")
  public  String deleteIncome(@PathVariable Long id){
    incomeService.deleteIncome(id);
    return "incomeTable";
  }

  @RequestMapping("/Updateincomes/{id}")
  public  String updateincme(@PathVariable Long id, Model model){
    Income inccc=incomeService.getIncomeByid(id);
    model.addAttribute("incomeses",inccc);
    return "UpdateIncome";

  }

}

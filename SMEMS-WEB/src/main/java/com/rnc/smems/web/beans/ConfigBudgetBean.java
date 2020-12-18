package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Budget;
import com.rnc.smems.web.enums.BudgetStatus;
import com.rnc.smems.web.services.BudgetService;


/**
 * @author Yadanar Myint Maw
 * @since 18-12-2020
 *  
 *  */

@Named
@ViewScoped
public class ConfigBudgetBean implements Serializable {


	private static final long serialVersionUID = 1L;

	private Budget budget;

	private List<Budget> budgets;
	
	private BudgetStatus [] statuses;
	
	@Inject
	private BudgetService budgetService;
	
	@PostConstruct
	public void initialize () {
		budget = new Budget();
		budgets = budgetService.findAll();
		statuses = BudgetStatus.values();
	}	
	
	public void save () {
		budgetService.save(budget);
		initialize();
	}
	
	public void update (Budget budget) {
		this.budget = budget;
	}
	
	public void delete (Budget budget) {
		budgetService.delete(budget);
		initialize();
	}
	
	public String generateStockColor (BudgetStatus budgetStatus) {
		if ( (budgetStatus.equals(BudgetStatus.Salary_OutCome)) && (budgetStatus.equals(BudgetStatus.Sharer_OutCome)) ) {
			return "text-primary";
		} else{
			return "text-danger";
		}
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public List<Budget> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<Budget> budgets) {
		this.budgets = budgets;
	}

	public BudgetStatus[] getStatuses() {
		return statuses;
	}

	public void setStatuses(BudgetStatus[] statuses) {
		this.statuses = statuses;
	}
	
	
}

package com.rnc.smems.web.services;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rnc.smems.web.entities.Budget;
import com.rnc.smems.web.repositories.BudgetRepository;

/**
 
 * @author Yadanar Myint Maw
 * @since 18-12-2020
 *  
 * 
 *  */

@LocalBean
@Stateless
public class BudgetService {
	
	@Inject
	private BudgetRepository budgetRepository;
	
	public void save(Budget budget) {
		if(budget.getId() == 0) {
			budget.setDate(LocalDate.now());
			budgetRepository.save(budget);
		}
		else {
			budgetRepository.update(budget);
		}
	}
	
	public void update(Budget budget) {
		budgetRepository.update(budget);
	}
	
	public void delete (Budget budget) {
		budget.setErase(true);
		update(budget);
	}
	
	public Budget findByID (long id) {
		return budgetRepository.findByID(id);
	}
	
	public List<Budget> findAll () {
		return budgetRepository.findAll();
	}

}
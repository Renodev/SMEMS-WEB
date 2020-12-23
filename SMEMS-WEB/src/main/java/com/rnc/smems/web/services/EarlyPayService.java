package com.rnc.smems.web.services;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rnc.smems.web.entities.EarlyPay;
import com.rnc.smems.web.repositories.EarlyPayRepository;

@LocalBean
@Stateless
public class EarlyPayService {
	@Inject
	private EarlyPayRepository earlypayRepository;
	
	public void save (EarlyPay earlypay) {
		if (earlypay.getId() == 0) {
			earlypay.setDate(LocalDate.now());
			earlypayRepository.save(earlypay);
		} else {
			earlypayRepository.update(earlypay);
		}
	}
	public void delete (EarlyPay earlypay) {
		EarlyPay mtr = findByID(earlypay.getId());
		earlypayRepository.delete(mtr);
	}
	public EarlyPay findByID (long id) {
		return earlypayRepository.findByID(id);
	}
	public List<EarlyPay> findAll () {
		return earlypayRepository.findAll();
	}
}

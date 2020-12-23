package com.rnc.smems.web.beans;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import com.rnc.smems.web.entities.EarlyPay;
import com.rnc.smems.web.entities.Staff;
import com.rnc.smems.web.services.EarlyPayService;
import com.rnc.smems.web.services.StaffService;

@Named
@ViewScoped
public class ConfigEarlyPayBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EarlyPay earlyPay;
	private List<EarlyPay> earlypays;
	private List<Staff> staffs;
	
	@Inject
	private EarlyPayService earlyPayService;
	
	@Inject
	private StaffService staffService;

	@PostConstruct
	public void initialize() {
		earlyPay = new EarlyPay();
		staffs = staffService.findAll();
		earlypays = earlyPayService.findAll();
	}
	public void save () {
		earlyPayService.save(earlyPay);
		initialize();
	}
	public void update (EarlyPay earlyPay) {
		this.earlyPay = earlyPay;
	}
	public void delete (EarlyPay earlyPay) {
		earlyPayService.delete(earlyPay);
		initialize();
	}
	public EarlyPay getEarlyPay() {
		return earlyPay;
	}
	public void setEarlyPay(EarlyPay earlyPay) {
		this.earlyPay = earlyPay;
	}
	public List<Staff> getStaffs() {
		return staffs;
	}
	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}
	public List<EarlyPay> getEarlypays() {
		return earlypays;
	}
	public void setEarlypays(List<EarlyPay> earlypays) {
		this.earlypays = earlypays;
	}
	
	
}
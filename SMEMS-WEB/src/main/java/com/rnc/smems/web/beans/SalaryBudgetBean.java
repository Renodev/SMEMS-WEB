package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Attendance;
import com.rnc.smems.web.entities.EarlyPay;
import com.rnc.smems.web.entities.OverTime;
import com.rnc.smems.web.entities.Staff;
import com.rnc.smems.web.services.AttendanceService;
import com.rnc.smems.web.services.EarlyPayService;
import com.rnc.smems.web.services.OverTimeService;
import com.rnc.smems.web.services.StaffService;

@Named
@ViewScoped
public class SalaryBudgetBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Staff staff;
	
	private OverTime overtime;
	
	private List<Staff> staffs;
	
	private List<OverTime> overtimes;
	
	private List<EarlyPay> earlypays;
	
	private List<Attendance> attendances;
	
	private LocalDate Date;
	
	private double OneDay;
	
	private double OneHour;
	
	private double TotalAmount;
	
	private EarlyPay earlypay;
	
	@Inject
	private StaffService staffService;
	
	@Inject
	private OverTimeService overtimeService;
	
	@Inject
	private EarlyPayService earlypayService;
	
	@Inject
	private AttendanceService attendanceService;
	
	@PostConstruct
	public void initialize () {
		
		overtime = new OverTime();
		staff = new Staff();
		earlypay = new EarlyPay();
		staffs = staffService.findAll();
		
	}
	
	public double ForOneDay(Staff staff) {
		
		Date = LocalDate.now();
        //System.out.println(Date.getDayOfMonth());
		OneDay = staff.getSalary() / Date.lengthOfMonth();
		return OneDay;
	}
	
	
	
	public double ForOneHour(Staff staff) {	
		
		//OneDay = staff.getSalary() / 23;
		OneHour = ForOneDay(staff) / 8;	
		return OneHour;
	}
	
	public double TotalOvertime(Staff staff) {
		
		int TotalHours = 0;
		
		overtimes = overtimeService.findByStaff(staff);
		
		for(OverTime overtime : overtimes) {
			TotalHours += overtime.getHour();
		}
		return TotalHours;	
	}
	
	public double totalAmount(Staff staff) {
			
			TotalAmount = ForOneHour(staff) * TotalOvertime(staff);
			
			return TotalAmount;
		}
	
	public double earlypay(Staff staff) {
		
		double earlypayAmount = 0;
		
		earlypays = earlypayService.findByStaff(staff);
		
		for(EarlyPay earlypay : earlypays) {
			earlypayAmount += earlypay.getAmount();
		}
		return earlypayAmount;
	}
	
	public double Absent(Staff staff) {
		
		attendances = attendanceService.findByAbsent(staff);
		int i = attendances.size();
		double absentAmount = ForOneDay(staff) * i;
		return absentAmount;
		
	}
	
	public double TotalSalary(Staff staff) {
		
		double Total = ( staff.getSalary() + totalAmount(staff) ) - ( earlypay(staff) + Absent(staff) );
		return Total;
		
	}
	
	public List<EarlyPay> getEarlypays() {
		return earlypays;
	}

	public void setEarlypays(List<EarlyPay> earlypays) {
		this.earlypays = earlypays;
	}

	public EarlyPay getEarlypay() {
		return earlypay;
	}

	public void setEarlypay(EarlyPay earlypay) {
		this.earlypay = earlypay;
	}

	public EarlyPayService getEarlypayService() {
		return earlypayService;
	}

	public void setEarlypayService(EarlyPayService earlypayService) {
		this.earlypayService = earlypayService;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public OverTime getOvertime() {
		return overtime;
	}

	public void setOvertime(OverTime overtime) {
		this.overtime = overtime;
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	public List<OverTime> getOvertimes() {
		return overtimes;
	}

	public void setOvertimes(List<OverTime> overtimes) {
		this.overtimes = overtimes;
	}

	

	public double getOneDay() {
		return OneDay;
	}

	public void setOneDay(double oneDay) {
		OneDay = oneDay;
	}

	public double getOneHour() {
		return OneHour;
	}

	public void setOneHour(double oneHour) {
		OneHour = oneHour;
	}

	public double getTotalAmount() {
		return TotalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		TotalAmount = totalAmount;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public OverTimeService getOvertimeService() {
		return overtimeService;
	}

	public void setOvertimeService(OverTimeService overtimeService) {
		this.overtimeService = overtimeService;
	}

	
	
	
	
	
	
	
	
	
}

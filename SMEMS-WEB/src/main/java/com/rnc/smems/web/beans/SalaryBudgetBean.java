package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.rnc.smems.web.entities.Attendance;
import com.rnc.smems.web.entities.EarlyPay;
import com.rnc.smems.web.entities.OverTime;
import com.rnc.smems.web.entities.Staff;
import com.rnc.smems.web.enums.BudgetStatus;
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
	
	private LocalDate Date = LocalDate.now();
	
	private double OneDay;
	
	private double OneHour;
	
	private double TotalAmount;
	
	private int year;
	
	private EarlyPay earlypay;
	
	private double Total;
	
	private double absentAmount;
	
	private String date;
	
	private String m;
	
	private String dateFrom = Date.toString().substring(0,4)+"-"+Date.getMonthValue()+"-01";
	
	private String dateTo = Date.toString().substring(0,4)+"-"+Date.getMonthValue()+"-"+Date.getDayOfMonth();
	
	@Enumerated(EnumType.STRING)
	private Month month;
	
	private Month [] months;
	
	@Inject
	private StaffService staffService;
	
	@Inject
	private OverTimeService overtimeService;
	
	@Inject
	private EarlyPayService earlypayService;
	
	@Inject
	private AttendanceService attendanceService;
	
	DecimalFormat df = new DecimalFormat("#.00");
	
	
	@PostConstruct
	public void initialize () {
		
		overtime = new OverTime();
		staff = new Staff();
		earlypay = new EarlyPay();
		staffs = staffService.findAll();
		months = Month.values();
		
	}
	
	public String ForOneDay(Staff staff) {
       
		if (date == "date") {
			
			if(m.equals("FEBRUARY")) {
				if(checkLeapYear(year)){
					double OneDay = staff.getSalary() / 29;
					return df.format(OneDay);
				}else {
					double OneDay = staff.getSalary() / 28;
					return df.format(OneDay);
				}	
			}
			
			else if (m.equals("SEPTEMBER") || m.equals("APRIL") || m.equals("JUNE") || m.equals("NOVEMBER")) {
				double OneDay = staff.getSalary() / 30;
				return df.format(OneDay);
			}
			else {
				double OneDay = staff.getSalary() / 31;
				return df.format(OneDay);
			}
		}
		
		else {
			double OneDay = staff.getSalary() / Date.lengthOfMonth();
			return df.format(OneDay);
		}
	}
	
	public String ForOneHour(Staff staff) {	
		
		double OneHour = Double.parseDouble(ForOneDay(staff)) / 8;	
		return df.format(OneHour);
	}
	
	public double TotalOvertime(Staff staff) {
		
		int TotalHours = 0;
		overtimes = overtimeService.findByStaffDateFromAndDateTo(staff, dateFrom, dateTo);
		for(OverTime overtime : overtimes) {
			TotalHours += overtime.getHour();
		}
		return TotalHours;	
	}
	
	/*
	private String getEndOfMonth(LocalDate date) {
		
		String month = date.getMonth().toString();
		if (month.equals(2)) {
			return "28";
		}
		else if (month.equals(4) || month.equals(6) || month.equals(9) || month.equals(11)) {
			return "30";
		}else {
			return "31";
		}	
	}*/
	
	private boolean checkLeapYear(int year) {
		
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					return true;
				}else {
					return false;
				}	
			}else {
				return false;
			}	
		}else {
			return false;
		}
	}
	

	public String totalAmount(Staff staff) {
		
		double TotalAmount = Double.parseDouble(ForOneHour(staff)) * TotalOvertime(staff);
		return df.format(TotalAmount);
		}
	
	public double earlypay(Staff staff) {
		
		double earlypayAmount = 0;
		earlypays = earlypayService.findByStaffDateFromAndDateTo(staff, dateFrom, dateTo);
		for(EarlyPay earlypay : earlypays) {
			earlypayAmount += earlypay.getAmount();
		}
		return earlypayAmount;
	}
	
	public String Absent(Staff staff) {
		
		attendances = attendanceService.findByStaffDateFromAndDateTo(staff, dateFrom, dateTo);
		double absentAmount = Double.parseDouble(ForOneDay(staff)) * attendances.size();
		return df.format(absentAmount);
		
	}
	
	public String TotalSalary(Staff staff) {
		
		double Total = ( staff.getSalary() + Double.parseDouble(totalAmount(staff)) ) -
				( earlypay(staff) + Double.parseDouble(Absent(staff)) );
		return df.format(Total);	
	}
	
	
	
	public void Search() {
		
		for(Staff staff : staffs) {
			date = String.valueOf(Date);
			date = "date";
			m = String.valueOf(month);
			
			if(m.equals("JANUARY")) {
				dateFrom  = String.valueOf(year) +"-1-01";
				dateTo = String.valueOf(year)+"-1-31";
			}
			else if(m.equals("FEBRUARY")) {
				
				if(m.equals("FEBRUARY")) {
					if(checkLeapYear(year)){
						dateFrom  = String.valueOf(year) +"-2-01";
						dateTo = String.valueOf(year)+"-2-29";
					}
					else {
						dateFrom  = String.valueOf(year) +"-2-01";
						dateTo = String.valueOf(year)+"-2-28";
					}	
				}					
			}
			else if(m.equals("MARCH")) {
				//dateFrom  = String.valueOf(year) +"-3-01";
				//dateTo = String.valueOf(year)+"-3-31";
				dateFrom  = "2021-1-01";
				dateTo = "2021-1-7";
				
				//System.out.println(Date);
				//dateFrom = Date.toString().substring(0,4)+"-"+Date.getMonthValue()+"-01";
				//dateTo = Date.toString().substring(0,4)+"-"+Date.getMonthValue()+"-"+Date.getDayOfMonth();
				
			}
			else if(m.equals("APRIL")) {
				dateFrom  = String.valueOf(year) +"-4-01";
				dateTo = String.valueOf(year)+"-4-30";
			}
			else if(m.equals("MAY")) {
				dateFrom  = String.valueOf(year) +"-5-01";
				dateTo = String.valueOf(year)+"-5-31";
			}
			else if(m.equals("JUNE")) {
				dateFrom  = String.valueOf(year) +"-6-01";
				dateTo = String.valueOf(year)+"-6-30";
			}
			else if(m.equals("JUNLY")) {
				dateFrom  = String.valueOf(year) +"-7-01";
				dateTo = String.valueOf(year)+"-7-31";
			}
			else if(m.equals("AUGUST")) {
				dateFrom  = String.valueOf(year) +"-8-01";
				dateTo = String.valueOf(year)+"-8-31";
			}
			else if(m.equals("SEPTEMBER")) {
				dateFrom  = String.valueOf(year) +"-9-01";
				dateTo = String.valueOf(year)+"-9-31";
			}
			else if(m.equals("OCTOBER")) {
				dateFrom  = String.valueOf(year) +"-10-01";
				dateTo = String.valueOf(year)+"-10-31";
			}
			else if(m.equals("NOVEMBER")) {
				dateFrom  = String.valueOf(year) +"-11-01";
				dateTo = String.valueOf(year)+"-11-31";
			}
			else if(m.equals("DECEMBER")){
				dateFrom  = String.valueOf(year) +"-12-01";
				dateTo = String.valueOf(year)+"-12-30";
			}
						
			ForOneDay(staff);
			ForOneHour(staff);
			TotalOvertime(staff);
			totalAmount(staff);
			earlypay(staff);
			Absent(staff);
			TotalSalary(staff);		
		}
		
	}
	
	
	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public Month[] getMonths() {
		return months;
	}

	public void setMonths(Month[] months) {
		this.months = months;
	}


	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}

	public double getAbsentAmount() {
		return absentAmount;
	}

	public void setAbsentAmount(double absentAmount) {
		this.absentAmount = absentAmount;
	}

	
	
	
	
	
	
	
	
}

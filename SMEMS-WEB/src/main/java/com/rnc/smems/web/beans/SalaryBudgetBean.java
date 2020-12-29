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
        //System.out.println(Date.getDayOfMonth());
		double OneDay = staff.getSalary() / Date.lengthOfMonth();
		return df.format(OneDay);
	}
	
	
	
	public String ForOneHour(Staff staff) {	
		
		
		double OneHour = Double.parseDouble(ForOneDay(staff)) / 8;	
		return df.format(OneHour);
	}
	
	public double TotalOvertime(Staff staff) {
		
		int TotalHours = 0;
		
		String dateFrom = Date.toString().substring(0,4)+"-"+Date.getMonthValue()+"-01";
		String dateTo = Date.toString().substring(0,4)+"-"+Date.getMonthValue()+"-"+Date.getDayOfMonth();
		overtimes = overtimeService.findByStaffDateFromAndDateTo(staff, dateFrom, dateTo );
		for(OverTime overtime : overtimes) {
			TotalHours += overtime.getHour();
		}
		//System.out.println(TotalHours);
		return TotalHours;	
	}
	
	/*private String getEndOfMonth(LocalDate date) {
		
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
	

	public String totalAmount(Staff staff) {
					
		double TotalAmount = Double.parseDouble(ForOneHour(staff)) * TotalOvertime(staff);
			
			return df.format(TotalAmount);
		}
	
	public double earlypay(Staff staff) {
		
		double earlypayAmount = 0;
		
		earlypays = earlypayService.findByStaff(staff);
		
		for(EarlyPay earlypay : earlypays) {
			earlypayAmount += earlypay.getAmount();
		}
		return earlypayAmount;
	}
	
	public String Absent(Staff staff) {
		
		String dateFrom = Date.toString().substring(0,4)+"-"+Date.getMonthValue()+"-01";
		String dateTo = Date.toString().substring(0,4)+"-"+Date.getMonthValue()+"-"+Date.getDayOfMonth();
		
		attendances = attendanceService.findByStaffDateFromAndDateTo(staff, dateFrom, dateTo);
		double absentAmount = Double.parseDouble(ForOneDay(staff)) * attendances.size();
		return df.format(absentAmount);
		
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

	public String TotalSalary(Staff staff) {
		
		double Total = ( staff.getSalary() + Double.parseDouble(totalAmount(staff)) ) -
				( earlypay(staff) + Double.parseDouble(Absent(staff)) );
		return df.format(Total);
		
	}
	
	/*public void DateFromDateTo(Staff staff) {
		
		int TotalHours = 0;
		System.out.println(year);
		System.out.println(month);
		
		
		if(month.equals("JANUARY")) {
			this.OneDay = staff.getSalary() / 31;
			this.dateFrom  = String.valueOf(year) +"1-01";
			this.dateTo = String.valueOf(year)+"-1-31";
		}
		else if(month.equals("FEBRUARY")) {
			this.dateFrom  = String.valueOf(year) +"2-01";
			this.dateTo = String.valueOf(year)+"-2-28";
		}
		else if(month.equals("March")) {
			this.dateFrom  = String.valueOf(year) +"3-01";
			this.dateTo = String.valueOf(year)+"-3-31";
		}
		else if(month.equals("April")) {
			this.dateTo = String.valueOf(year)+"-4-30";
		}
		else if(month.equals("May")) {
			this.dateFrom  = String.valueOf(year) +"5-01";
			this.dateTo = String.valueOf(year)+"-5-31";
		}
		else if(month.equals("June")) {
			this.dateFrom  = String.valueOf(year) +"6-01";
			this.dateTo = String.valueOf(year)+"-6-30";
		}
		else if(month.equals("Junly")) {
			this.dateFrom  = String.valueOf(year) +"7-01";
			this.dateTo = String.valueOf(year)+"-7-31";
		}
		else if(month.equals("August")) {
			this.dateFrom  = String.valueOf(year) +"8-01";
			this.dateTo = String.valueOf(year)+"-8-31";
		}
		else if(month.equals("September")) {
			this.dateFrom  = String.valueOf(year) +"9-01";
			this.dateTo = String.valueOf(year)+"-9-31";
		}
		else if(month.equals("October")) {
			this.dateFrom  = String.valueOf(year) +"10-01";
			this.dateTo = String.valueOf(year)+"-10-31";
		}
		else if(month.equals("November")) {
			this.dateFrom  = String.valueOf(year) +"11-01";
			this.dateTo = String.valueOf(year)+"-11-31";
		}
		else{
			this.dateFrom  = String.valueOf(year) +"12-01";
			this.dateTo = String.valueOf(year)+"-12-30";
		}
		
		this.OneHour = OneDay / 8;
		
		overtimes = overtimeService.findByStaffDateFromAndDateTo(staff, dateFrom, dateTo);
		for(OverTime overtime : overtimes) {
			TotalHours += overtime.getHour();
		}
		this.TotalAmount = OneHour * TotalHours;
		
		double earlypayAmount = 0;
		
		earlypays = earlypayService.findByStaff(staff);
		
		for(EarlyPay earlypay : earlypays) {
			earlypayAmount += earlypay.getAmount();
		}
		
		attendances = attendanceService.findByStaffDateFromAndDateTo(staff, dateFrom, dateTo);
		this.absentAmount = OneDay * attendances.size();
		
		this.Total = ( staff.getSalary() + TotalAmount ) -
				( earlypayAmount + absentAmount );	
		
	}*/
	
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

	
	
	
	
	
	
	
	
	
}

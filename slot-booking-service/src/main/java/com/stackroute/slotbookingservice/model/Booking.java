package com.stackroute.slotbookingservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    private int appointmentId;

	private String emailId;
	@JsonFormat(pattern="dd/MM/yyyy")
    private Date appointmentDate;
    private String appointmentTime;
    private String customername;
    private String centerName;
    private String mobile;
    private String address;
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Booking [appointmentId=" + appointmentId + ", appointmentDate=" + appointmentDate + ", appointmentTime="
				+ appointmentTime + ", customername=" + customername + ", centerName=" + centerName + ", mobile="
				+ mobile + ", address=" + address + "]";
	}
    
    
}

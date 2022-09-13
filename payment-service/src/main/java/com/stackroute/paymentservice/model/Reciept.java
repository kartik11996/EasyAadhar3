package com.stackroute.paymentservice.model;

import java.sql.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class Reciept {

	
	public Reciept() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	//@GeneratedValue(strategy = GenerationType.AUTO)
 //private int slno;
	@Id
 private String ORDERID;
 private String BANKNAME;
 private String BANKTXNID;   
 private String CURRENCY;
 private String GATEWAYNAME;
 private String PAYMENTMODE ;
 private String RESPCODE;
 private String   RESPMSG;
 private String  STATUS;
 private String TXNAMOUNT;
 private String TXNDATE ;
 private String TXNID;
// private Map<String, String> details;
 
// public int getSlno() {
//		return slno;
//	}
//	public void setSlno(int slno) {
//		this.slno = slno;
//	}

	public String getORDERID() {
		return ORDERID;
	}
	public void setORDERID(String oRDERID) {
		ORDERID = oRDERID;
	}
public String getBANKNAME() {
	return BANKNAME;
}
public void setBANKNAME(String bANKNAME) {
	BANKNAME = bANKNAME;
}

public String getCURRENCY() {
	return CURRENCY;
}
public void setCURRENCY(String cURRENCY) {
	CURRENCY = cURRENCY;
}
public String getGATEWAYNAME() {
	return GATEWAYNAME;
}
public void setGATEWAYNAME(String gATEWAYNAME) {
	GATEWAYNAME = gATEWAYNAME;
}
public String getPAYMENTMODE() {
	return PAYMENTMODE;
}
public void setPAYMENTMODE(String pAYMENTMODE) {
	PAYMENTMODE = pAYMENTMODE;
}
public String getRESPCODE() {
	return RESPCODE;
}
public void setRESPCODE(String rESPCODE){
	RESPCODE = rESPCODE;
}
public String getRESPMSG() {
	return RESPMSG;
}
public void setRESPMSG(String rESPMSG) {
	RESPMSG = rESPMSG;
}
public String getSTATUS() {
	return STATUS;
}
public void setSTATUS(String sTATUS) {
	STATUS = sTATUS;
}


public String getTXNID() {
	return TXNID;
}
public void setTXNID(String tXNID) {
	TXNID = tXNID;
}
public String getTXNAMOUNT() {
	return TXNAMOUNT;
}
public void setTXNAMOUNT(String tXNAMOUNT) {
	TXNAMOUNT = tXNAMOUNT;
}
public String getTXNDATE() {
	return TXNDATE;
}
public void setTXNDATE(String tXNDATE) {
	TXNDATE = tXNDATE;
}

public String getBANKTXNID() {
	return BANKTXNID;
}
public void setBANKTXNID(String bANKTXNID) {
	BANKTXNID = bANKTXNID;
}
@Override
public String toString() {
	return "PaymentHistory [ORDERID=" +this.ORDERID + ", BANKNAME=" + this.BANKNAME + ", BANKTXNID=" + this.BANKNAME + ", CURRENCY="
			+ this.CURRENCY + ", GATEWAYNAME=" + this.GATEWAYNAME + ", PAYMENTMODE=" + this.BANKNAME + ", RESPCODE=" + this.BANKNAME
			+ ", RESPMSG=" + this.RESPMSG + ", STATUS=" + this.STATUS + ", TXNAMOUNT=" + this.TXNAMOUNT + ", TXNDATE=" + this.TXNDATE
			+ ", TXNID=" + this.TXNID + "]";
}


} 
	    
	   
	   
	   
	    
	   
	    
	   
	   
	
	
	


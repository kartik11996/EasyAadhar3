package com.customerservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CustomerList")

public class CustomerList {

	private String name;
    private String email;
    private String mobile;
    private String address;
    private String nationality;
    private String gender;
    private String DOB;
    private String parentName;
    private String typeOfRelation;
    private String relativeAadharNumber;
	
    
    public CustomerList(String name, String email, String mobile, String address, String nationality, String gender,
			String dOB, String parentName, String typeOfRelation, String relativeAadharNumber) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.nationality = nationality;
		this.gender = gender;
		DOB = dOB;
		this.parentName = parentName;
		this.typeOfRelation = typeOfRelation;
		this.relativeAadharNumber = relativeAadharNumber;
	}


	public CustomerList() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getDOB() {
		return DOB;
	}


	public void setDOB(String dOB) {
		DOB = dOB;
	}


	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	public String getTypeOfRelation() {
		return typeOfRelation;
	}


	public void setTypeOfRelation(String typeOfRelation) {
		this.typeOfRelation = typeOfRelation;
	}


	public String getRelativeAadharNumber() {
		return relativeAadharNumber;
	}


	public void setRelativeAadharNumber(String relativeAadharNumber) {
		this.relativeAadharNumber = relativeAadharNumber;
	}


	@Override
	public String toString() {
		return "CustomerList [name=" + this.name + ", email=" + this.email + ", mobile=" + this.mobile + ", address=" + this.address
				+ ", nationality=" + this.nationality + ", gender=" + this.gender + ", DOB=" + this.DOB + ", parentName=" + this.parentName
				+ ", typeOfRelation=" + this.typeOfRelation + ", relativeAadharNumber=" + this.relativeAadharNumber + "]";
	}

    
}

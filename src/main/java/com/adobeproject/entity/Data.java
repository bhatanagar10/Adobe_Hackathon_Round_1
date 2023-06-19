package com.adobeproject.entity;

import java.util.List;

public class Data {
    private String Business_City;
    private String Business_Country;
    private String Business_Description;
    private String Business_Name;
    private String Business_StreetAddress;
    private String Business_Zipcode;
    private String Customer_Address_line1;
    private String Customer_Address_line2;
    private String Customer_Email;
    private String Customer_Name;
    private String Customer_PhoneNumber;
    private List<InvoiceDetails> Invoice_BillDetails;
    private String Invoice_Description;
    private String Invoice_DueDate;
    private String Invoice_IssueDate;
    private String Invoice_Number;
    private String Invoice_Tax;

    public String getBusiness_City() {
        return Business_City;
    }

    public void setBusiness_City(String business_City) {
        Business_City = business_City;
    }

    public String getBusiness_Country() {
        return Business_Country;
    }

    public void setBusiness_Country(String business_Country) {
        Business_Country = business_Country;
    }

    public String getBusiness_Description() {
        return Business_Description;
    }

    public void setBusiness_Description(String business_Description) {
        Business_Description = business_Description;
    }

    public String getBusiness_Name() {
        return Business_Name;
    }

    public void setBusiness_Name(String business_Name) {
        Business_Name = business_Name;
    }

    public String getBusiness_StreetAddress() {
        return Business_StreetAddress;
    }

    public void setBusiness_StreetAddress(String business_StreetAddress) {
        Business_StreetAddress = business_StreetAddress;
    }

    public String getBusiness_Zipcode() {
        return Business_Zipcode;
    }

    public void setBusiness_Zipcode(String business_Zipcode) {
        Business_Zipcode = business_Zipcode;
    }

    public String getCustomer_Address_line1() {
        return Customer_Address_line1;
    }

    public void setCustomer_Address_line1(String customer_Address_line1) {
        Customer_Address_line1 = customer_Address_line1;
    }

    public String getCustomer_Address_line2() {
        return Customer_Address_line2;
    }

    public void setCustomer_Address_line2(String customer_Address_line2) {
        Customer_Address_line2 = customer_Address_line2;
    }

    public String getCustomer_Email() {
        return Customer_Email;
    }

    public void setCustomer_Email(String customer_Email) {
        Customer_Email = customer_Email;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getCustomer_PhoneNumber() {
        return Customer_PhoneNumber;
    }

    public void setCustomer_PhoneNumber(String customer_PhoneNumber) {
        Customer_PhoneNumber = customer_PhoneNumber;
    }

    public List<InvoiceDetails> getInvoice_BillDetails() {
        return Invoice_BillDetails;
    }

    public void setInvoice_BillDetails(List<InvoiceDetails> invoice_BillDetails) {
        Invoice_BillDetails = invoice_BillDetails;
    }

    public String getInvoice_Description() {
        return Invoice_Description;
    }

    public void setInvoice_Description(String invoice_Description) {
        Invoice_Description = invoice_Description;
    }

    public String getInvoice_DueDate() {
        return Invoice_DueDate;
    }

    public void setInvoice_DueDate(String invoice_DueDate) {
        Invoice_DueDate = invoice_DueDate;
    }

    public String getInvoice_IssueDate() {
        return Invoice_IssueDate;
    }

    public void setInvoice_IssueDate(String invoice_IssueDate) {
        Invoice_IssueDate = invoice_IssueDate;
    }

    public String getInvoice_Number() {
        return Invoice_Number;
    }

    public void setInvoice_Number(String invoice_Number) {
        Invoice_Number = invoice_Number;
    }

    public String getInvoice_Tax() {
        return Invoice_Tax;
    }

    public void setInvoice_Tax(String invoice_Tax) {
        Invoice_Tax = invoice_Tax;
    }

    @Override
    public String toString() {
        return "Data{" +
                "Business_City='" + Business_City + '\'' +
                ", Business_Country='" + Business_Country + '\'' +
                ", Business_Description='" + Business_Description + '\'' +
                ", Business_Name='" + Business_Name + '\'' +
                ", Business_StreetAddress='" + Business_StreetAddress + '\'' +
                ", Business_Zipcode='" + Business_Zipcode + '\'' +
                ", Customer_Address_line1='" + Customer_Address_line1 + '\'' +
                ", Customer_Address_line2='" + Customer_Address_line2 + '\'' +
                ", Customer_Email='" + Customer_Email + '\'' +
                ", Customer_Name='" + Customer_Name + '\'' +
                ", Customer_PhoneNumber='" + Customer_PhoneNumber + '\'' +
                ", Invoice_BillDetails=" + Invoice_BillDetails +
                ", Invoice_Description='" + Invoice_Description + '\'' +
                ", Invoice_DueDate='" + Invoice_DueDate + '\'' +
                ", Invoice_IssueDate='" + Invoice_IssueDate + '\'' +
                ", Invoice_Number='" + Invoice_Number + '\'' +
                ", Invoice_Tax='" + Invoice_Tax + '\'' +
                '}';
    }
}
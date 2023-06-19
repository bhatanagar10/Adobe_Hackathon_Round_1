package com.adobeproject.entity;

public class InvoiceDetails {
    private String Invoice_BillDetails_Name;
    private String Invoice_BillDetails_Quantity;
    private String Invoice_BillDetails_Rate;

    public String getInvoice_BillDetails_Name() {
        return Invoice_BillDetails_Name;
    }

    public void setInvoice_BillDetails_Name(String invoice_BillDetails_Name) {
        Invoice_BillDetails_Name = invoice_BillDetails_Name;
    }

    public String getInvoice_BillDetails_Quantity() {
        return Invoice_BillDetails_Quantity;
    }

    public void setInvoice_BillDetails_Quantity(String invoice_BillDetails_Quantity) {
        Invoice_BillDetails_Quantity = invoice_BillDetails_Quantity;
    }

    public String getInvoice_BillDetails_Rate() {
        return Invoice_BillDetails_Rate;
    }

    public void setInvoice_BillDetails_Rate(String invoice_BillDetails_Rate) {
        Invoice_BillDetails_Rate = invoice_BillDetails_Rate;
    }

    @Override
    public String toString() {
        return "InvoiceDetails{" +
                "Invoice_BillDetails_Name='" + Invoice_BillDetails_Name + '\'' +
                ", Invoice_BillDetails_Quantity='" + Invoice_BillDetails_Quantity + '\'' +
                ", Invoice_BillDetails_Rate='" + Invoice_BillDetails_Rate + '\'' +
                '}';
    }
}

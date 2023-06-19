package com.adobeproject.insertDatatoCSV;

import com.adobeproject.entity.Data;
import com.adobeproject.entity.InvoiceDetails;
import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InsertDataToCSV {
    public void insertData(List<Data> data) throws IOException {
        //create a CSV printer
        CSVPrinter printer = new CSVPrinter(new FileWriter("ExtractedData.csv"), CSVFormat.DEFAULT);

        //create header row
        printer.printRecord("Bussiness__City","Bussiness__Country","Bussiness__Description","Bussiness__Name","Bussiness__StreetAddress","Bussiness__Zipcode","Customer__Address__line1","Customer__Address__line2","Customer__Email","Customer__Name","Customer__PhoneNumber","Invoice__BillDetails__Name","Invoice__BillDetails__Quantity","Invoice__BillDetails__Rate","Invoice__Description","Invoice__DueDate","Invoice__IssueDate","Invoice__Number","Invoice__Tax");

        // create data rows
        for (Data element : data) {
            for( InvoiceDetails i : element.getInvoice_BillDetails()){
                List<String> l = new ArrayList<>();
                l.add(element.getBusiness_City());
                l.add(element.getBusiness_Country());
                l.add(element.getBusiness_Description());
                l.add(element.getBusiness_Name());
                l.add(element.getBusiness_StreetAddress());
                l.add(element.getBusiness_Zipcode());
                l.add(element.getCustomer_Address_line1());
                l.add(element.getCustomer_Address_line2());
                l.add(element.getCustomer_Email());
                l.add(element.getCustomer_Name());
                l.add(element.getCustomer_PhoneNumber());

                l.add(i.getInvoice_BillDetails_Name());
                l.add(i.getInvoice_BillDetails_Quantity());
                l.add(i.getInvoice_BillDetails_Rate());

                l.add(element.getInvoice_Description());
                l.add(element.getInvoice_DueDate());
                l.add(element.getInvoice_IssueDate());
                l.add(element.getInvoice_Number());
                l.add(element.getInvoice_Tax());

                String[] temp = l.toArray(new String[l.size()]);

                printer.printRecord(temp);
            }
        }

        //close the printer after the file is complete
        printer.flush();
        printer.close();
    }

    public static void main(String[] args) throws IOException {
        InsertDataToCSV obj = new InsertDataToCSV();
        obj.insertData(null);
    }
}

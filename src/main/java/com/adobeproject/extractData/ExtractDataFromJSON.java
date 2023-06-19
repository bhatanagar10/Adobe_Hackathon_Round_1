package com.adobeproject.extractData;

import com.adobe.pdfservices.operation.internal.dto.response.error.Detail;
import com.adobeproject.entity.Data;
import com.adobeproject.entity.InvoiceDetails;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ExtractDataFromJSON {
    //contains text data of elements
    List<String> text = new ArrayList<>();


    //contains bounds data of elements
    List<List<Double> >bounds = new ArrayList<>();
    Data model = new Data();
    Integer i=0;
    Integer total_elements;
    Integer index;

    //to display the list of strings
    public void display(List<String>l){
        for(String element: l ){
            System.out.println(element);
        }
    }


    /*
     * For extracting data from json file.
     * Here we are extraction bounds and text of all elements and storing in a list respectively
     */
    public void Data()  {

        try {
            System.out.println("Reading:-"+"UnzippedFiles/unzip"+index);
            //Reading json file using buffer reader
            BufferedReader in = new BufferedReader(new FileReader("src/main/resources/UnzippedFiles/unzip"+index+"/structuredData.json"));

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(in);
            // get elements list from json
            JSONArray elements = (JSONArray) jsonObject.get("elements");
            total_elements = elements.size();

            //iterating elements list
            for( Object s:elements){
                JSONObject o = (JSONObject) parser.parse(s.toString());

                //extracting the Text field
                text.add((String)o.get("Text"));

                //extraction the bounds array
                JSONArray boundsjson = (JSONArray) o.get("Bounds");
                List<Double>temp = new ArrayList<>();
                if(boundsjson == null){
                    bounds.add(Arrays.asList(0.0,0.0,0.0,0.0));
                }
                else{

                    for(Object obj : boundsjson){
                        temp.add((Double)obj);
                    }
                    bounds.add(temp);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }



    /*
     * Extraction phase 1 includes business related data
     * Business name , business address
     * extracting elements with first bound 76.72799682617188
     */
    public void extractionPhase1(){
        System.out.println("---------------Phase 1----------------------------");
        List<String>l = new ArrayList<>();

        for( ; bounds.get(i).get(0) == 76.72799682617188 ; i++){
            l.add(text.get(i));
        }

        // filling the data in model object
        model.setBusiness_Name(l.get(0).trim());
        StringBuilder builder =  new StringBuilder();
        for(int x = 1 ; x < l.size() ; x++){
            builder.append(l.get(x));
        }
        String result = builder.toString();
        String []resultArray = result.split(",");
        model.setBusiness_StreetAddress(resultArray[0].trim());
        model.setBusiness_City(resultArray[1].trim());
        model.setBusiness_Country(resultArray[2].trim()+ "," + resultArray[3].trim().split(" ")[0]);
        model.setBusiness_Zipcode(resultArray[3].trim().split(" ")[1]);

        //display
        display(l);
    }



    /*
     * Extraction phase 2 includes data related to invoice
     * Invoice number and issue date
     * extracting elements with 3rd bound >= 542
     */
    public void extractionPhase2(){
        System.out.println("---------------Phase 2----------------------------");
        List<String>l = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for( ; i < total_elements && bounds.get(i).get(2) >= 542 ; i++){
            l.add(text.get(i));
            builder.append(text.get(i));
        }

        String result = builder.toString();
        String []resultArray = result.split(" ");

        model.setInvoice_Number(resultArray[1].trim());
        model.setInvoice_IssueDate(resultArray[4].trim());

        display(l);

    }


    /*
     * Extraction phase 3 includes data related to
     * name of the compnay and about the company
     * extracting elements with first bound 76.72799682617188
     */
    public void extractionPhase3() throws IOException {
        System.out.println("---------------Phase 3----------------------------");
        List<String>l = new ArrayList<>();
        int count=1;
        for( ; i < total_elements && bounds.get(i).get(0) == 76.72799682617188 ; i++){
            l.add(text.get(i));
            if(count == 0) {
                model.setBusiness_Description(text.get(i).trim());
                break;
            }

            count=count-1;
        }

        display(l);
    }



    /*
     * Extraction phase 4 includes data related to customer details
     * Name , email, phone number , address
     * extracting elements with first bound 81.04800415039062
     */
    public void extractionPhase4() throws IOException {
        System.out.println("---------------Phase 4----------------------------");
        List<String>l = new ArrayList<>();
        for(int x=i; x < total_elements  ; x++){
            if(bounds.get(x).get(0) == 81.04800415039062 && !text.get(x).equals("null"))
                l.add(text.get(x));
        }

        // filling the data in model object

            StringBuilder builder = new StringBuilder();
            for(int x=0 ; x < l.size() ;  x++){
                builder.append(l.get(x));
            }
            String result = builder.toString();
            String []resultArray = result.split(" ");

            int x=2;
            StringBuilder temp= new StringBuilder();
            while(!resultArray[x].contains("@")){
                temp.append(resultArray[x]).append(" ");
                x=x+1;
            }
            model.setCustomer_Name(temp.toString().trim());

            if(resultArray[x].contains("@") && resultArray[x].contains(".com")){
                model.setCustomer_Email(resultArray[x].trim());
                x=x+1;
            }
            else{
                model.setCustomer_Email(resultArray[x].trim() + resultArray[x+1].trim());
                x=x+2;
            }
            model.setCustomer_PhoneNumber(resultArray[x].trim());
            x=x+1;

            model.setCustomer_Address_line1(resultArray[x] + " " + resultArray[x+1] + " " + resultArray[x+2]);
            x=x+3;

            temp.setLength(0);
            while( x < resultArray.length ){
                temp.append(resultArray[x]).append(" ");
                x=x+1;
            }
            model.setCustomer_Address_line2(temp.toString().trim());
        display(l);
    }



    //for invoice description
    /*
     * Extraction phase 5 includes data related to Invoice description
     * extracting elements with first bound 240.25999450683594
     *
     */
    public void extractionPhase5() throws IOException {
        System.out.println("---------------Phase 5----------------------------");
        List<String>l = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for(int x=i; x < total_elements  ; x++){
            if(bounds.get(x).get(0) == 240.25999450683594 && !text.get(x).equals("null")) {
                l.add(text.get(x));
                builder.append(text.get(x));
            }
        }
        String s = builder.toString();
        model.setInvoice_Description((s.substring(8)).trim());
        display(l);
    }



    /*
     * Extraction phase 6 includes data related to payment details
     * Due date and subtotal
     * extracting elements with first bound 412.8000030517578 or 410.63999938964844
     */
    public void extractionPhase6() {
        System.out.println("---------------Phase 6----------------------------");
        List<String>l = new ArrayList<>();
        int x;
        StringBuilder builder = new StringBuilder();
        for(x=i; x < total_elements  ; x++){
            if((bounds.get(x).get(0) == 412.8000030517578 || bounds.get(x).get(0) == 410.63999938964844 )&& !text.get(x).equals("null")) {
                l.add(text.get(x));
                builder.append(text.get(x));
            }
        }

            String result = builder.toString();
            String []resultArray = result.split(" ");

            for(String s : resultArray){
                if(s.contains("-")){
                    model.setInvoice_DueDate(s.trim());
                    break;
                }
            }
            display(l);
        }



    /*
     * Extraction phase 7 is just to iterate the header row
     */
    public void extractionPhase7() {
        System.out.println("---------------Phase 7----------------------------");

        while(!text.get(i).trim().equals("AMOUNT")){
            i=i+1;
        }
        i=i+1;

    }




    /*
     * Extraction phase is to extract data for item details
     * ITEM , QTY , RATE , AMOUNT
     */
    public void extractionPhase8() throws IOException {
        System.out.println("---------------Phase 8----------------------------");

        int countInRow=4;
        List<List<String>> l = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        // extract data  until subtotal text comes up
            while(!text.get(i).trim().startsWith("Subtotal")){
                if(countInRow == 0){
                    l.add(temp);
                    temp = new ArrayList<>();
                    countInRow=4;
                }
                if(!text.get(i).equals("null")){
                    temp.add(text.get(i));
                    countInRow=countInRow-1;
                }
                i=i+1;
            }

            List<InvoiceDetails> detailsList = new ArrayList<>();
            // setting data in object
            for(int x =0 ; x < l.size() ; x++){
                InvoiceDetails details = new InvoiceDetails();

                details.setInvoice_BillDetails_Name(l.get(x).get(0).trim());
                details.setInvoice_BillDetails_Quantity(l.get(x).get(1).trim());
                details.setInvoice_BillDetails_Rate(l.get(x).get(2).trim());

                detailsList.add(details);
            }
            model.setInvoice_BillDetails(detailsList);
            for(List<String> ele : l){
                for(String ele1 : ele){
                    System.out.print(ele1 + "     ");
                }
                System.out.println();
            }
    }



    /*
     * Extraction phase 9 is to extract data for TAX rate
     * data with first bound > 485 and that does not starts with $
     * this way we will get data of tax not subtotal
     */
    public void extractionPhase9(){
        System.out.println("---------------Phase 9----------------------------");
        boolean flag=false;
        for( ; i < total_elements  ; i++){
            if((bounds.get(i).get(0) > 485) && !text.get(i).equals("null") && !text.get(i).startsWith("$") && Integer.parseInt(text.get(i).trim()) <= 100){
                    System.out.println(text.get(i).trim());
                    model.setInvoice_Tax(text.get(i).trim());
                    flag= true;
                    break;

            }
        }

        if(!flag){
            System.out.println("10");
            model.setInvoice_Tax("10");
        }
    }



    /*
     * getData() function is one which calls all the extraction phase functions() and return the model object
     */
    public Data getData(int idx) throws IOException {
        index=idx;
        Data();

        //replaing null values with string "null"
        Collections.replaceAll(text , null , "null");

    /*    for(int x=0 ; x < text.size() ; x++){
            System.out.println(text.get(x)+"-------------------------"+ bounds.get(x));
        }
*/
        extractionPhase1();
        extractionPhase2();
        extractionPhase3();
        extractionPhase4();
        extractionPhase5();
        extractionPhase6();
        extractionPhase7();
        extractionPhase8();
        extractionPhase9();


        System.out.println("\n *" +
                "\n *" +
                "\n *" +
                "\n *" +
                "\n *");


        return model;
    }



    public static void main(String[] args) throws IOException {
        ExtractDataFromJSON extractDataFromJSONTest = new ExtractDataFromJSON();
        extractDataFromJSONTest.getData(51);
    }
}

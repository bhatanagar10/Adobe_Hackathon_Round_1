package com.adobeproject.extractData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtractDataFromJSON_trash {
    List<String> path = new ArrayList<>();
    List<String> text = new ArrayList<>();
    List<String> filePath = new ArrayList<>();
    List<List<Double> >bounds = new ArrayList<>();
    ExtractDataFromCSV csVextraction = new ExtractDataFromCSV();
    Integer paraNo = 1;
    Integer i=0;
    Integer index;
    public void Data()  {

        try {
            System.out.println("Reading:-"+"UnzippedFiles/unzip"+index+"/structuredData.json");
            BufferedReader in = new BufferedReader(new FileReader("src/main/resources/UnzippedFiles/unzip"+index+"/structuredData.json"));
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(in);
            JSONArray elements = (JSONArray) jsonObject.get("elements");
//            System.out.println(elements);

            for( Object s:elements){
                JSONObject o = (JSONObject) parser.parse(s.toString());
                path.add((String)o.get("Path"));
                text.add((String)o.get("Text"));
//                System.out.println((String)o.get("Path") + "     " + (String)o.get("Text"));

                JSONArray boundsjson = (JSONArray) o.get("Bounds");
                List<Double>temp = new ArrayList<>();
                for(Object obj : boundsjson){
                    temp.add((Double)obj);
                }
                bounds.add(temp);
                JSONArray file = (JSONArray) o.get("filePaths");
                filePath.add( (file== null)? "null":file.get(0).toString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


    public void extractionPhase1(){
        List<String> dataArray = new ArrayList<>();
        StringBuilder data = new StringBuilder();

        String lastTag = path.get(i).split("/")[4];
        for(i=0 ; !path.get(i).split("/")[4].equals("Title") ; i++){

            if(!path.get(i).split("/")[4].equals(lastTag)){
                dataArray.add(data.toString());
                lastTag = path.get(i).split("/")[4];
                data.setLength(0);
            }
            data.append(text.get(i));
        }
        dataArray.add(data.toString());
        for(String s: dataArray){
            System.out.println(s);
        }
    }

    public void extractionPhase2(){
        List<String> dataArray = new ArrayList<>();
        dataArray.add(text.get(i));
        i=i+1;
        paraNo=paraNo+1;
        dataArray.add(text.get(i));
        i=i+1;

        for(String s: dataArray){
            System.out.println(s);
        }
    }

    public void extractionPhase3() throws IOException {

        // check for table
        String [] pathSplit = path.get(i).split("/");
        String tableName = pathSplit[pathSplit.length-1];
        if(tableName.equals("Table")){
            //extract data from csv
            List<List<String>> l = csVextraction.getData(1, filePath.get(i) , index);

            while(path.get(i).split("/")[4].equals(tableName)){
                i=i+1;
            }

            for(List<String> ele : l){
                for(String ele2:ele){
                    System.out.print(ele2 + " ");
                }
                System.out.println();
            }
        }

        //normal extraction
        else{
            String []headers = { "DETAILS" , "PAYMENT" };
            int h=0;
            i=i+1;
            List<String>dataArray = new ArrayList<>();
            StringBuilder data = new StringBuilder();

            while(   h < headers.length){
                if(text.get(i) != null) {
                    if(text.get(i).replaceAll("\\s+$", "").equals(headers[h])){
                        dataArray.add(data.toString());
                        h=h+1;
                        data.setLength(0);
                    }
                    else {
                        data.append(text.get(i));
                    }
                }
                i=i+1;
            }
            data.append(text.get(i));
            i=i+1;
            data.append(text.get(i));
            i=i+1;
            dataArray.add(data.toString());
            for(String ele : dataArray){
                System.out.println(ele);
            }

        }

    }

    public void extractionPhase4() throws IOException {
        String []p = path.get(i).split("/");
        String tableName = p[p.length-1];
        while(path.get(i).split("/")[4].equals(tableName)){
            i=i+1;
        }

        //extract data from csv file
        p=path.get(i).split("/");
        tableName = p[p.length-1];
        System.out.println(filePath.get(i));
        List<List<String>> l = csVextraction.getData(0, filePath.get(i) , index);
        for(List<String> ele : l){
            for(String ele2:ele){
                System.out.print(ele2 + " ");
            }
            System.out.println();
        }
        while(path.get(i).split("/")[4].equals(tableName)){
            i=i+1;
        }
    }

    public void extractionPhase5() throws IOException {
        String file = filePath.get(i);
        System.out.println(file);
        List<List<String>> l =  csVextraction.getData( 0 , file, index);
        for(List<String> ele : l){
            for(String ele2:ele){
                System.out.print(ele2 + " ");
            }
            System.out.println();
        }


        //check if phase 6 is there
        if(l.size() == 3){
            //subtotal tax and total due

        }
        //go for phase 6
        else{
            extractionPhase6();
        }
    }

    public void extractionPhase6() {
        System.out.println("---------------Phase 6----------------------------");
        String tableName = path.get(i).split("/")[4];
        while(path.get(i).split("/")[4].equals(tableName) && i < path.size()){
            i=i+1;
        }

        List<String> l = new ArrayList<>();
        l.add(text.get(i));
        l.add(text.get(i+1));
        for(String element: l){
            System.out.println(element);
        }
    }
    public void getData(int idx) throws IOException {
        index=idx;
        Data();


        for(int x=0 ; x < text.size() ; x++){
            System.out.println(text.get(x)+"-------------------------"+ bounds.get(x));
        }
        /*System.out.println("---------------Phase 1----------------------------");
        extractionPhase1();
        System.out.println("---------------Phase 2----------------------------");
        extractionPhase2();
        System.out.println("---------------Phase 3----------------------------");
        extractionPhase3();
        System.out.println("---------------Phase 4----------------------------");
        extractionPhase4();
        System.out.println("---------------Phase 5----------------------------");
        extractionPhase5();

        System.out.println("\n *" +
                "\n *" +
                "\n *" +
                "\n *" +
                "\n *");

         */
    }
}

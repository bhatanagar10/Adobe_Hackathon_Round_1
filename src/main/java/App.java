import com.adobeproject.entity.Data;
import com.adobeproject.extractData.ExtractDataFromJSON;
import com.adobeproject.extractData.ExtractDataFromPDF;
import com.adobeproject.extractData.ExtractFilesFromZip;
import com.adobeproject.insertDatatoCSV.InsertDataToCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
 *  This is a Maven Project, all dependencies are configured in pom.xml
 *
 * Entry point of the application
 *
 * First calls the ExtractDataFromPDF to extract data from .pdf file into zip file
 * Second calls the ExtractFilesFromZip to extract files from the compressed zip files.
 * Third calls the ExtractDataFromJSON to get meaningful data into Data model class list.
 * Fourth calls the InsertDataToCSV to insert data into ExtractedData.csv file
 */
public class App {
    public static void main(String[] args) throws IOException {

        /*
         * This list will store all the data for each record
         */
        List<Data> data = new ArrayList<>();


        for(int index=0 ; index < 100 ; index++){

   /*         // extract data from the pdf files
            System.out.println("Extracting data from pdf for file "+ index);
            ExtractDataFromPDF extractDataFromPDF = new ExtractDataFromPDF();
            extractDataFromPDF.getData(index);

            //unzip the file
            System.out.println("Extracting files from zip "+ index);
            ExtractFilesFromZip extractFilesFromZip = new ExtractFilesFromZip();
            extractFilesFromZip.unzipFile(index);*/

            //extract data from json
            System.out.println("Extracting data from json");
            ExtractDataFromJSON extractDataFromJSON = new ExtractDataFromJSON();
            data.add( extractDataFromJSON.getData(index) );
        }

        InsertDataToCSV insertDataToCSV = new InsertDataToCSV();
        insertDataToCSV.insertData(data);
    }
}

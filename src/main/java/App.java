import com.adobeproject.entity.Data;
import com.adobeproject.extractData.ExtractDataFromJSON;
import com.adobeproject.extractData.ExtractDataFromPDF;
import com.adobeproject.extractData.ExtractFilesFromZip;
import com.adobeproject.insertDatatoCSV.InsertDataToCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/* Go through the README.md file in section HOW APPLICATION WORKS
 *  This is a Maven Project, all dependencies are configured in pom.xml
 *
 *
 * First calls the ExtractDataFromPDF to extract data from .pdf file into zip file
 * Second calls the ExtractFilesFromZip to extract files from the compressed zip files.
 * Third calls the ExtractDataFromJSON to get meaningful data from json into Data model class list.
 * Fourth calls the InsertDataToCSV to insert data into ExtractedData.csv file
 */
public class App {
    public static void main(String[] args) throws IOException {

        /*
         * This list will store all the data for each record
         */
        List<Data> data = new ArrayList<>();

 /*       for(int index=0 ; index < 100 ; index++){

            // extract data for resources/Output/output0.pdf to output99.pdf
            System.out.println("Extracting data from pdf for file "+ index);
            ExtractDataFromPDF extractDataFromPDF = new ExtractDataFromPDF();
            extractDataFromPDF.getData(index);

            //unzip file for output/output0.zip to output/output99.zip
            System.out.println("Extracting files from zip "+ index);
            ExtractFilesFromZip extractFilesFromZip = new ExtractFilesFromZip();
            extractFilesFromZip.unzipFile(index);

            //extract data from json
            System.out.println("Extracting data from json");
            ExtractDataFromJSON extractDataFromJSON = new ExtractDataFromJSON();
            data.add( extractDataFromJSON.getData(index) );
        }
*/

        /*
         * WHEN compressed files has been extracted
         * Extracting data for resources/UnzippedFiles/unzip0 to unzip99 folder
         */
        for(int index=0 ; index < 100 ; index++){

            //extract data from json
            System.out.println("Extracting data from json");
            ExtractDataFromJSON extractDataFromJSON = new ExtractDataFromJSON();
            data.add( extractDataFromJSON.getData(index) );
        }



        /*
         * Inserting data to csv using object of Data class which has all the data
         */
        InsertDataToCSV insertDataToCSV = new InsertDataToCSV();
        insertDataToCSV.insertData(data);

    }
}

package com.adobeproject.extractData;

import java.io.IOException;
import java.util.Arrays;
import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.auth.Credentials;
import com.adobe.pdfservices.operation.exception.SdkException;
import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.exception.ServiceUsageException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.ExtractPDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractPDFOptions;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractElementType;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.TableStructureType;
import org.slf4j.LoggerFactory;
/**
 * This sample illustrates how to extract Text and Table Information from PDF.
 * <p>
 * Refer to README.md for instructions on how to run the samples & understand output zip file.
 */
public class ExtractDataFromPDF {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ExtractDataFromPDF.class);

    public void getData(int index) {

        try {
            // Initial setup, create credentials instance.
            Credentials credentials = Credentials.serviceAccountCredentialsBuilder()
                    .fromFile("pdfservices-api-credentials.json")
                    .build();

            // Create an ExecutionContext using credentials.
            ExecutionContext executionContext = ExecutionContext.create(credentials);

            ExtractPDFOperation extractPDFOperation = ExtractPDFOperation.createNew();

            // Provide an input FileRef for the operation
            FileRef source = FileRef.createFromLocalFile("src/main/resources/Output/output"+ index+".pdf");
            extractPDFOperation.setInputFile(source);

            // Build ExtractPDF options and set them into the operation
            ExtractPDFOptions extractPDFOptions = ExtractPDFOptions.extractPdfOptionsBuilder()
                    .addElementsToExtract(Arrays.asList(ExtractElementType.TEXT, ExtractElementType.TABLES))
                    .addTableStructureFormat(TableStructureType.CSV)
                    .build();
            extractPDFOperation.setOptions(extractPDFOptions);

            // Execute the operation
            FileRef result = extractPDFOperation.execute(executionContext);

            // Save the result at the specified location
            String outputFilePath = createOutputFilePath(index);
            result.saveAs(outputFilePath);


        } catch (ServiceApiException | IOException | SdkException | ServiceUsageException e) {
            LOGGER.error("Exception encountered while executing operation", e);
        }

    }

    //Generates a string containing a directory structure and file name for the output file.
    public static String createOutputFilePath(int index){
        return("output/ExtractTextTableInfoFromPDF/output" + index + ".zip");
    }

    public static void main(String[] args) {
        ExtractDataFromPDF obj = new ExtractDataFromPDF();
        obj.getData(91);
    }

}

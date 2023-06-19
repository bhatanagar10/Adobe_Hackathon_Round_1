# Adobe_Hackathon_Round_1
Submission for the round 1 of Adobe PapyrusNebula Hackathon

## Task
The problem is Extracting information from PDF invoices using Adobe PDF Services Extract API.
Extract data from these 100 invoices in the same form as ExtractedData.csv file 

## What this application does ?

1. Extracts data in form of JSON from .pdf file using **Adobe PDF Services Extract API**
2. Using the JSON to find the meaningful data and fill the ExtractedData.csv file accordingly

## How to run
App.java is the main class which runs all the functions.Run it to execute the program.

## What each classes are for
1. **App.java** : This is the main class which executes all the required functions.
2. **ExtractDataFromPDF.java** : Contains all the functions to extract data from pdf files from _src/main/resources/Output_ folder to output/ directory.
3. **ExtractFilesFromZIP.java** : extracts files from compressed folder to _src/main/resources/UnzippedFiles_.
4. **ExtractDataFromJSON.java** : reads json files from _src\main\resources\UnzippedFiles_ and gets data in order to be filled in ExtractedData.csv.
5. **Data.java** : It is a model class which has variables, data is filled in to the list of this class which we got from _ExtractDataFromJSON.java_
6. **InsertDataToCSV.java** : List of data class is is used to fill data into ExtractedData.csv

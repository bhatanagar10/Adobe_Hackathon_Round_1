package com.adobeproject.extractData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExtractDataFromCSV {
    public List<List<String>> getData(int skipLine , String fileName,int index) throws FileNotFoundException, IOException {
        Pattern pattern = Pattern.compile(",");
        List<List<String>> l = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("src/main/resources/UnzippedFiles/unzip"+index+"/"+fileName))){
            l = in.lines().skip(skipLine).map(line -> {
                String[] x = pattern.split(line);

                return Arrays.asList(x);
            }).collect(Collectors.toList());

            return l;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factory;

/**
 *
 * @author User
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.io.File;
import java.net.URL;
import java.lang.StringBuilder;

public class CSVReader {
    private String itemCSVFile;
    private String storeCSVFile;
    final Class<?> referenceClass = MallDriver.class;
    final URL url = referenceClass.getProtectionDomain().getCodeSource().getLocation();

    
    public CSVReader() {
        //itemCSVFile = "C:\\Users\\User\\Desktop\\Java\\ItemCSV.csv";
        //storeCSVFile = "C:\\Users\\User\\Desktop\\Java\\StoreCSV.csv";
    }
    
    public CSVReader(String itemfile, String storefile) {
        itemCSVFile = itemfile;
        storeCSVFile = storefile;
    }
    
    public ArrayList<String[]> readCSV(String csvfile) {
        File jarPath;
        try {
            jarPath = new File(url.toURI()).getParentFile();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            jarPath = new File(csvfile);
        }
        StringBuilder sb = new StringBuilder();
        String file = sb.append(jarPath.toString()).append(csvfile).toString();
	BufferedReader br = null;
	String line;
	String csvSplitBy = ",";
        ArrayList <String[]> csvResult = new ArrayList<>();
 
	try {
 
            br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) {
                String[] temp = line.split(csvSplitBy);
                csvResult.add(temp);
            }
 
	} catch (FileNotFoundException e) {
            e.printStackTrace();
	} catch (IOException e) {
            e.printStackTrace();
	} finally {
            if (br != null) {
                try {
                        br.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
            }
	}
        return csvResult;
    }
    
}

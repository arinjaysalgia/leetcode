package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class FileReading {
    public List<String> inputData = new LinkedList<>();
    public List<String> queryData = new LinkedList<>();
    public FileReading(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "C:\\CFE\\FNMS-75891\\DataToDelete.txt"));
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                // read next line
                inputData.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            reader = new BufferedReader(new FileReader(
                    "C:\\CFE\\FNMS-75891\\DataFromQuery.txt"));
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                // read next line
                if(inputData.contains(line)){
                    inputData.remove(line);
                }
                else{
                    queryData.add(line);
                }

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Contents of the inputData after removal: ");
        System.out.println(inputData);
        System.out.println("Contents of the inputData after removal: " + inputData.size());
        System.out.println();
        System.out.println("Contents of the queryData after removal: ");
        System.out.println(queryData);
        System.out.println("Contents of the queryData after removal: " + queryData.size());

    }

    public static void testFileReading(){
        FileReading fr = new FileReading();
    }
}

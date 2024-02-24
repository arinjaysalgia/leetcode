package com.company;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.security.spec.ECField;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*
* The problem here is to check for a given file if that file has been modified if modified than return the file latest Modification
* date if not then return the old date from the memory.
*
* Logic : We will build a cache in form of hashMap and the file attributes in form of the
* */
public class FileModified extends Exception{
    Map<String, FileTime> fileTimeMap  = null;
    public FileModified()
    {
        fileTimeMap = new HashMap<>();
    }

    public FileTime getFileData(String FileName) throws Exception {
        FileTime fileTime = null;
        try {
            Path path = FileSystems.getDefault().getPath(FileName);
            BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
            BasicFileAttributes attributes  = view.readAttributes();
            fileTime =  attributes.lastModifiedTime();

            //System.out.println("Last Modified time is : " + fileTime + " and time in second from Epoch time is : " + fileTime.to(TimeUnit.SECONDS));
            //System.out.println("Path : " + path);
            //System.out.println("BasicAttributesView : " + view);
            //System.out.println("attributes : " + attributes);

        }
        catch(NoSuchFileException fne){
            System.out.println("Check the file name again " + fne);
            throw new Exception(fne.getMessage());
        }

        return fileTime;
    }

    public void checkOrGetFileInformation(String [] filePaths) {

        FileTime ft = null;
        for(String filePath : filePaths){
            try{
                if(fileTimeMap.containsKey(filePath) && fileTimeMap.get(filePath).to(TimeUnit.SECONDS) >= getFileData(filePath).to(TimeUnit.SECONDS)) {
                    ft = fileTimeMap.get(filePath);
                    System.out.println("File " + filePath + " was last modified on : " + ft);
                }
                else{
                    System.out.println("File not found in the cache");
                    ft = getFileData(filePath) ;
                    fileTimeMap.put(filePath, ft);
                    System.out.println("File " + filePath + " was last modified on : " + ft);
                }
            }
            catch(Exception e){
                    System.out.println("Exception occured : " + e.getMessage());

            }
        }
    }

    public static void testFileModified(){
        String[] filePaths =
                {
                    "C:\\from_old_machine\\Java_projects\\LeetCode\\src\\com\\company\\AccountTransaction.java",
                    "C:\\from_old_machine\\Java_projects\\LeetCode\\src\\com\\company\\GoogleInterviewQuestion.java",
                    "C:\\from_old_machine\\Java_projects\\LeetCode\\src\\com\\company\\LRUCache.java"
                };
        FileModified fm = new FileModified();
        fm.checkOrGetFileInformation(filePaths);
        System.out.println();
        System.out.println("Running for the second time");
        fm.checkOrGetFileInformation(filePaths);
    }


}

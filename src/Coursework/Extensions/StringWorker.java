package Coursework.Extensions;

/*****************************************************
 Project Name:      B00714027 CW3
 File Name:         StringWorker
 Created by: 		Melissa Brennan
 Student No:        B00714027
 Comments:          Extension Class to work with Strings
 ******************************************************/

public class StringWorker {
    public static boolean searchContains(String searchFor, String searchFrom) {
        return searchFrom.toUpperCase().contains(searchFor.toUpperCase())
                || searchFor == null
                || searchFor.isEmpty();
    }
}

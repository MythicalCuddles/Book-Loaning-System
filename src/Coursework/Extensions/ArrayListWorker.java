package Coursework.Extensions;

import Coursework.Enums.TypeOfFiction;
import Coursework.Enums.TypeOfNonFiction;
import Coursework.Objects.Book;
import Coursework.Objects.Fiction;
import Coursework.Objects.NonFiction;

import java.util.ArrayList;
import java.util.stream.IntStream;

/*****************************************************
 Project Name:      B00714027 CW3
 File Name:         ArrayListWorker
 Created by: 		Melissa Brennan
 Student No:        B00714027
 Comments:          Extension class to work with ArrayLists
 ******************************************************/

public class ArrayListWorker {
    public static int getIndexOfIdForBook(ArrayList<Book> arrayList, int bookId) {
        return IntStream.range(0, arrayList.size()).filter(i -> arrayList.get(i).getId() == bookId).findFirst().orElse(-1);
    }

    public static int getIndexOfIdForFiction(ArrayList<Fiction> arrayList, int bookId) {
        return IntStream.range(0, arrayList.size()).filter(i -> arrayList.get(i).getId() == bookId).findFirst().orElse(-1);
    }

    public static int getIndexOfIdForNonFiction(ArrayList<NonFiction> arrayList, int bookId) {
        return IntStream.range(0, arrayList.size()).filter(i -> arrayList.get(i).getId() == bookId).findFirst().orElse(-1);
    }

    public static boolean doesListContainBook(ArrayList<Fiction> arrayList, String title, String author, TypeOfFiction genre) {
        for(int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getTitle().toUpperCase().equals(title.toUpperCase())){
                if(arrayList.get(i).getAuthor().toUpperCase().equals(author.toUpperCase())) {
                    if(arrayList.get(i).getGenre().equals(genre)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean doesListContainBook(ArrayList<NonFiction> arrayList, String title, String author, TypeOfNonFiction genre) {
        for(int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getTitle().toUpperCase().equals(title.toUpperCase())){
                if(arrayList.get(i).getAuthor().toUpperCase().equals(author.toUpperCase())) {
                    if(arrayList.get(i).getGenre().equals(genre)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

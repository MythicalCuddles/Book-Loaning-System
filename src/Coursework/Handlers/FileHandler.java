package Coursework.Handlers;

import Coursework.Enums.TypeOfFiction;
import Coursework.Enums.TypeOfNonFiction;
import Coursework.Extensions.ArrayListWorker;
import Coursework.Objects.Book;
import Coursework.Objects.Fiction;
import Coursework.Objects.NonFiction;

import javax.swing.*;
import java.io.*;

/*****************************************************
 Project Name:      B00714027 CW3
 File Name:         FileHandler
 Created by: 		Melissa Brennan
 Student No:        B00714027
 Comments:          Class to handle external files.
 ******************************************************/

public class FileHandler {
    private static File FictionBooks = new File("fictions.ser"), // File to store Fiction Books.
            NonFictionBooks = new File("non-fictions.ser"); // File to store Non-Fiction Books.
    private static File file; // Selected File from User.
    private static final JFileChooser fileChooser = new JFileChooser(); // File Chooser

    public static void ensureExistsAndLoad() { // Method to check if book files exist.
        if(didFictionFileExist()) { // If the file has existed:
            attemptToLoadBooksFromFile(FictionBooks);  // Load the books from that file.
        }

        if(didNonFictionFileExist()) { // If the file has existed:
            attemptToLoadBooksFromFile(NonFictionBooks); // Load the books from that file.
        }
    }

    private static boolean didFictionFileExist() { // If the fiction file existed, returns true, else returns false.
        try {
            if(!FictionBooks.exists()) { // If the file doesn't exist:
                FictionBooks.createNewFile(); // Create the file.
                System.out.println("[DEBUG] " + FictionBooks.getName() + " created."); // Console message for debugging purposes.
                return false; // Returns false as file was just created.
            } else { return true; } // Returns true as file existed and may have content.
        } catch (Exception exception) {
            DialogBoxHandler.ShowMessageDialog("Error", "An error has occurred whilst attempting to create a file to store fiction books.", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private static boolean didNonFictionFileExist() { // If the non-fiction file existed, returns true, else returns false.
        try {
            if(!NonFictionBooks.exists()) { // If the file doesn't exist:
                NonFictionBooks.createNewFile();  // Create the file.
                System.out.println("[DEBUG] " + NonFictionBooks.getName() + " created."); // Console message for debugging purposes.
                return false; // Returns false as file was just created.
            } else { return true; } // Returns true as file existed and may have content.
        } catch (Exception exception) {
            DialogBoxHandler.ShowMessageDialog("Error", "An error has occurred whilst attempting to create a file to store non-fiction books.", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static boolean selectFile()
    {
        int selOption = fileChooser.showOpenDialog(null);

        if(selOption == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile();

            System.out.println("[DEBUG] User has selected File: " + file.getName());
            attemptToLoadBooksFromFile(file);

            return true;
        }
        else
        {
            DialogBoxHandler.ShowMessageDialog("Alert", "Unable to Load Books. No file was selected.", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    public static void writeBooksToFile() {
        didFictionFileExist();
        didNonFictionFileExist();

        try {
            ObjectOutputStream fictionObjectStream = new ObjectOutputStream(new FileOutputStream(FictionBooks)),
                    nonFictionObjectStream = new ObjectOutputStream(new FileOutputStream(NonFictionBooks));

            for(int i = 0; i < Book.fictionArrayList.size(); i++) {
                fictionObjectStream.writeObject(Book.fictionArrayList.get(i));
            }

            for(int i = 0; i < Book.nonFictionArrayList.size(); i++) {
                nonFictionObjectStream.writeObject(Book.nonFictionArrayList.get(i));
            }

            fictionObjectStream.close();
            nonFictionObjectStream.close();
        } catch (FileNotFoundException exception) {
            return;
        } catch (IOException exception) {
            return;
        } catch (Exception exception) {
            DialogBoxHandler.ShowMessageDialog("Error", "An error has occurred whilst trying to write the books to files.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void attemptToLoadBooksFromFile(File file) {
        try {
            ObjectInputStream fictionObjectStream = new ObjectInputStream(new FileInputStream(file));

            try {
                Fiction fictionObject = null;
                do {
                    fictionObject = (Fiction) fictionObjectStream.readObject();
                    if(!ArrayListWorker.doesListContainBook(Book.fictionArrayList, fictionObject.getTitle(), fictionObject.getAuthor(), (TypeOfFiction) fictionObject.getGenre())) {
                        Book.fictionArrayList.add(new Fiction(fictionObject.getTitle(), fictionObject.getAuthor(), fictionObject.getGenre(), fictionObject.isOutOnLoan(), fictionObject.getLoanHolder(), fictionObject.getDateOfLoan()));
                    } else {
                        System.out.println("[Warning] The Book - " + fictionObject.getTitle() + " by " + fictionObject.getAuthor() + " - already exists in the program.");
                    }
                } while(fictionObject != null);
            } catch(Exception e) {
                // Do Nothing - Error will be thrown when all objects read and attempting to continue to read.
            }

            fictionObjectStream.close();
        } catch (Exception e) {

        }

        try {
            ObjectInputStream nonFictionObjectStream = new ObjectInputStream(new FileInputStream(file));

            try {
                NonFiction nonFictionObject = null;
                do {
                    nonFictionObject = (NonFiction)nonFictionObjectStream.readObject();
                    if(!ArrayListWorker.doesListContainBook(Book.nonFictionArrayList, nonFictionObject.getTitle(), nonFictionObject.getAuthor(), (TypeOfNonFiction) nonFictionObject.getGenre())) {
                        Book.nonFictionArrayList.add(new NonFiction(nonFictionObject.getTitle(), nonFictionObject.getAuthor(), nonFictionObject.getGenre(), nonFictionObject.isOutOnLoan(), nonFictionObject.getLoanHolder(), nonFictionObject.getDateOfLoan()));
                    } else {
                        System.out.println("[Warning] The Book - " + nonFictionObject.getTitle() + " by " + nonFictionObject.getAuthor() + " - already exists in the program.");
                    }
                } while(nonFictionObject != null);
            } catch(Exception e) {
                // Do Nothing - Error will be thrown when all objects read and attempting to continue to read.
            }

            nonFictionObjectStream.close();
        } catch (Exception e) {

        }

        System.out.println("[DEBUG] " + Book.getNoOfBooks() + " Books After Fiction Import.");
        System.out.println("[DEBUG] " + Book.getNoOfBooks() + " Books After Non-Fiction Import.");
    }
}

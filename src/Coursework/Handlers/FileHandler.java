package Coursework.Handlers;

import Coursework.Objects.Book;
import Coursework.Objects.Fiction;
import Coursework.Objects.NonFiction;

import javax.swing.*;
import java.io.*;

public class FileHandler {
    private static File FictionBooks = new File("fictions.ser"),
            NonFictionBooks = new File("non-fictions.ser");
    private static File file;
    static final JFileChooser fileChooser = new JFileChooser();

    public static void ensureExists() throws Exception {
        if(!FictionBooks.exists()) {
            FictionBooks.createNewFile();
            System.out.println(FictionBooks.getName() + " created.");
        }

        if(!NonFictionBooks.exists()) {
            NonFictionBooks.createNewFile();
            System.out.println(NonFictionBooks.getName() + " created.");
        }
    }

    public static boolean selectFile()
    {
        int selOption = fileChooser.showOpenDialog(null);

        if(selOption == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile();

            System.out.println("[INFO] User has changed File to: " + file.getName());

            return true;
        }
        else
        {
            DialogBoxHandler.ShowMessageDialog("Alert", "Unable to Load Books. No file was selected.", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    public static void writeBooksToFile() throws Exception {
        FileOutputStream fictionStream = new FileOutputStream(FictionBooks),
                nonFictionStream = new FileOutputStream(NonFictionBooks);

        ObjectOutputStream fictionObjectStream = new ObjectOutputStream(fictionStream),
                nonFictionObjectStream = new ObjectOutputStream(nonFictionStream);

        for(Fiction f : Book.fictionArrayList) {
            fictionObjectStream.writeObject(f);
        }

        for(NonFiction n : Book.nonFictionArrayList) {
            nonFictionObjectStream.writeObject(n);
        }

        fictionObjectStream.close();
        nonFictionObjectStream.close();
    }

    public static void loadBooksFromSerFile() throws Exception {
        FileInputStream fictionStream = new FileInputStream(FictionBooks),
                nonFictionStream = new FileInputStream(NonFictionBooks);

        ObjectInputStream fictionObjectStream = new ObjectInputStream(fictionStream),
                nonFictionObjectStream = new ObjectInputStream(nonFictionStream);

        boolean continueFiction = true, continueNonFiction = true;

        while(continueFiction) {
            Object o = fictionObjectStream.readObject();
            if(o != null) {
                Book.fictionArrayList.add((Fiction) o);
            } else {
                continueFiction = false;
            }
        }

        while(continueNonFiction) {
            Object o = nonFictionObjectStream.readObject();
            if(o != null) {
                Book.nonFictionArrayList.add((NonFiction) o);
            } else {
                continueNonFiction = false;
            }
        }

        fictionObjectStream.close();
        nonFictionObjectStream.close();
    }
}

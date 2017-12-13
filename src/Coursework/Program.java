package Coursework;

import Coursework.Enums.TypeOfFiction;
import Coursework.Enums.TypeOfNonFiction;
import Coursework.Objects.Book;
import Coursework.Objects.Fiction;
import Coursework.Objects.NonFiction;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Program extends Application {
    private static Image iconLogo = new Image("http://scm.ulster.ac.uk/~B00714027/files/icons/books.png");

    @Override
    public void start(Stage stage) throws Exception {
        setUpBooks();
        loadFXML("FXMLs/MainMenu.fxml", "Book System", true);
    }
    public static void main(String[] args) {
        launch(args);
    }

    private static void setUpBooks() // TODO: REMOVE
    {
        Book.fictionArrayList.add(new Fiction("The Lord of the Rings", "J. R. R. Tolkien", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The Name of the Wind", "Patrick Rothfuss", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("Dune", "Frank Herbert", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The Hobbit", "J. R. R. Tolkien", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("American Gods", "Neil Gaiman", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("A Game of Thrones", "George R. R. Martin", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("Jonathan Strange & Mr Norrell", "Susanna Clarke", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The Way of Kings", "Brandon Sanderson", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("A Wizard of Earthsea", "Ursula K. Le Guin", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("Old Man's War", "John Scalzi", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The Last Unicorn", "Peter S. Beagle", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("Harry Potter and the Philosopher's Stone", "J. K. Rowling", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The Belgariad", "David Eddings", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("Stranger in a Strange Land", "Robert A. Heinlein", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The Lies of Locke Lamora", "Scott Lynch", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The Left Hand of Darkness", "Ursula K. Le Guin", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The Time Machine", "H. G. Wells", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("Ender's Game", "Orson Scott Card", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The Mists of Avalon", "Marion Zimmer Bradley", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("Dragonflight", "Anne McCaffrey", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The War of the Worlds", "H. G. Wells", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("Assassin's Apprentice", "Robin Hobb", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("A Canticle for Leibowitz", "Walter M. Miller Jr.", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The Painted Man", "Peter V. Brett", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The Forever War", "Joe Haldeman", TypeOfFiction.FANTASY));
        Book.fictionArrayList.add(new Fiction("The Martian Chronicles", "Ray Bradbury", TypeOfFiction.FANTASY));

        Book.nonFictionArrayList.add(new NonFiction("Book 0", "Melissa Brennan", TypeOfNonFiction.SELFHELP));
        Book.nonFictionArrayList.add(new NonFiction("Book 1", "Melissa Brennan", TypeOfNonFiction.BIOGRAPHY));
        Book.nonFictionArrayList.add(new NonFiction("Book 2", "Melissa Brennan", TypeOfNonFiction.SCIENCE));
        Book.nonFictionArrayList.add(new NonFiction("Book 3", "Melissa Brennan", TypeOfNonFiction.DECOR));
    }

    public void loadFXML(String resource, String title, boolean closeAllOnClose) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(resource));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.getIcons().add(iconLogo);

        if(closeAllOnClose)
            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });

        stage.show();
    }
}

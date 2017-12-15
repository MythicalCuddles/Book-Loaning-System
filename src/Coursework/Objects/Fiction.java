package Coursework.Objects;

import Coursework.Enums.TypeOfFiction;
import Coursework.Extensions.BooleanWorker;

import java.io.Serializable;
import java.time.LocalDate;

public class Fiction extends Book implements Serializable {
    // Instance Variables
    private TypeOfFiction genre;

    // Constructors
    public Fiction() { this(null, null, null); }
    public Fiction(String title, String author, TypeOfFiction genre) { this(title, author, genre, false, null, null); }
    public Fiction(String title, String author, TypeOfFiction genre, boolean outOnLoan, String loanHolder, LocalDate dateOfLoan) {
        super(title, author, outOnLoan, loanHolder, dateOfLoan);

        this.genre = genre;
    }

    // Instance Getters
    public TypeOfFiction getGenre() { return this.genre; }

    // Instance Setters
    public void setGenre(TypeOfFiction genre) { this.genre = genre; }

    // Override Methods
    @Override
    public String toString() {
        return "[FICTION]\nBook ID: " + super.getId() + "\nBook Title: " + super.getTitle() + "\nBook Author: " + super.getAuthor()  +
                "\nOn Loan? " + BooleanWorker.toYesNo(super.isOutOnLoan()) + "\nLoan Holder: " + super.getLoanHolder() + "\nDate of Loan: " + super.getDateOfLoan() +
                "\nBook Genre: " + genre.toString();
    }
}

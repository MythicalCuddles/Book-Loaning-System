package Coursework.Objects;

import Coursework.Enums.TypeOfNonFiction;
import Coursework.Extensions.BooleanWorker;

import java.time.LocalDate;

public class NonFiction extends Book {
    // Instance Variables
    private TypeOfNonFiction genre;

    // Constructors
    public NonFiction() { this(null, null, null); }
    public NonFiction(String title, String author, TypeOfNonFiction genre) { this(title, author, genre, false, null, null); }
    public NonFiction(String title, String author, TypeOfNonFiction genre, boolean outOnLoan, String loanHolder, LocalDate dateOfLoan) {
        super(title, author, outOnLoan, loanHolder, dateOfLoan);

        this.genre = genre;
    }

    // Instance Getters
    public TypeOfNonFiction getGenre() { return this.genre; }

    // Instance Setters
    public void setGenre(TypeOfNonFiction genre) { this.genre = genre; }

    // Override Methods
    @Override
    public String toString() {
        return "[NON-FICTION]\nBook ID: " + super.getId() + "\nBook Title: " + super.getTitle() + "\nBook Author: " + super.getAuthor()  +
                "\nOn Loan? " + BooleanWorker.toYesNo(super.isOutOnLoan()) + "\nLoan Holder: " + super.getLoanHolder() + "\nDate of Loan: " + super.getDateOfLoan() +
                "\nBook Genre: " + genre.toString();
    }
}

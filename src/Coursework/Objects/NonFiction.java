package Coursework.Objects;

import Coursework.Enums.TypeOfNonFiction;

import java.util.Date;

public class NonFiction extends Book {
    // Instance Variables
    private TypeOfNonFiction genre;

    // Constructors
    public NonFiction() { this(null, null, null); }
    public NonFiction(String title, String author, TypeOfNonFiction genre) { this(title, author, genre, false, null, null); }
    public NonFiction(String title, String author, TypeOfNonFiction genre, boolean outOnLoan, String loanHolder, Date dateOfLoan) {
        super.setTitle(title);
        super.setAuthor(author);

        this.genre = genre;

        super.setOutOnLoan(outOnLoan);
        super.setLoanHolder(loanHolder);
        super.setDateOfLoan(dateOfLoan);
    }

    // Instance Getters
    public TypeOfNonFiction getGenre() { return this.genre; }

    // Instance Setters
    public void setGenre(TypeOfNonFiction genre) { this.genre = genre; }
}

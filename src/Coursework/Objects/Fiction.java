package Coursework.Objects;

import Coursework.Enums.TypeOfFiction;

import java.util.Date;

public class Fiction extends Book{
    // Instance Variables
    private TypeOfFiction genre;

    // Constructors
    public Fiction() { this(null, null, null); }
    public Fiction(String title, String author, TypeOfFiction genre) { this(title, author, genre, false, null, null); }
    public Fiction(String title, String author, TypeOfFiction genre, boolean outOnLoan, String loanHolder, Date dateOfLoan) {
        super.setTitle(title);
        super.setAuthor(author);

        this.genre = genre;

        super.setOutOnLoan(outOnLoan);
        super.setLoanHolder(loanHolder);
        super.setDateOfLoan(dateOfLoan);
    }

    // Instance Getters
    public TypeOfFiction getGenre() { return this.genre; }

    // Instance Setters
    public void setGenre(TypeOfFiction genre) { this.genre = genre; }
}

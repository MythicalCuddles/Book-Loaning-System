<p align="center">
  <img src ="http://imgserv.mythicalcuddles.xyz/Ulster%20University/COM187/CW3/logo.png" />
</p>

# Book Loaning System ![COM187/CW3](https://img.shields.io/badge/COM187-CW3-blue.svg) ![GitHub release](https://img.shields.io/github/release/MythicalCuddles/Book-Loaning-System.svg) ![GitHub Release Date](https://img.shields.io/github/release-date/MythicalCuddles/Book-Loaning-System.svg) ![GitHub last commit](https://img.shields.io/github/last-commit/MythicalCuddles/Book-Loaning-System.svg) ![GitHub issues](https://img.shields.io/github/issues/MythicalCuddles/Book-Loaning-System.svg) ![GitHub license](https://img.shields.io/github/license/MythicalCuddles/Book-Loaning-System.svg)

# Table of Contents

* [Scenario](#scenario)
  * [Functional Requirements](#functional-requirements)
  * [Non-Functional Requirements](#functional-requirements)
* [Screenshots](#screenshots)
  * [Main Menu](#main-menu)
  * [Add Book](#add-book)
  * [Display Available Books](#display-available-books)
  * [Display Loaned Books](#display-loaned-books)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Running](#running)
* [Additional Information](#additional-information)
  * [What I learned from this project](#what-i-learned-from-this-project)

## Scenario

You are the teacher of a primary school class and you want to have a system that allows you to record which fiction and non-fiction books are lent to your pupils.

Your system should have a menu system along the following lines (you can have sub-menus if you deem that to be necessary)

**1) Add Books**
- The teacher should be able to add relevant details about fiction and non-fiction books available for loan (NB: there is only one copy of a book available for loan).

**2) Display the books available for loan**
- The system should display the fiction and non-fiction books available for loan

**3) Display the books currently out on loan**
- The system should display the fiction and non-fiction books that are currently out on loan and which pupil has borrowed them

**4) Make book loan**
- If a pupil borrows a book, the system should record the date that it is rented, the name of the book is and what the pupil’s name is

**5) Return book**
- Once a pupil returns a book, then the book should be made available for loan

**6) Write details to file**
- Your system should be able save the details of a loan to file

### Functional Requirements

Your Java program must have the following:
- A Book superclass with appropriate properties and methods  (including accessor and mutator)
- A Fiction subclass and a NonFiction subclass – these subclasses will extend the Book superclass and have their own properties and methods
- Appropriate use of arrays of objects (or ArrayLists of objects) that utilise the two subclasses
- Appropriate use of toString methods
- Appropriate use of validation of user entry
- Appropriate use of comments
- An application class that will run the menu described in the scenario

### Non-Functional Requirements

There will be no extra marks given for the following, but you can implement them for your system if you so choose:
- Instead of a menu-based system on the console window, you can have a GUI menu system
- Instead of the teacher entering in the details of book available for loan, the items’ details can be read in from text files
- Using the Java Date class to record the dates of loans

___

# Screenshots

## Main Menu

![Main Menu](http://imgserv.mythicalcuddles.xyz/Ulster%20University/COM187/CW3/MainMenu.png)

The Main Menu is the first screen you see when the application is running. The user has multiple choices to choose between, with options to add books and view books.

## Add Book

![Add Book](http://imgserv.mythicalcuddles.xyz/Ulster%20University/COM187/CW3/AddBook.png)

The Add Book screen is shown when the user selects "Add Book" from the main menu. The user selects if the book is Fiction or Non-Fiction, followed by adding the Title of the book and the Author. The user can select from a wide range of genres from the dropdown menu to apply to the book. They can also set the book to be currently out on loan from this screen.

## Display Available Books

![Display Available Books](http://imgserv.mythicalcuddles.xyz/Ulster%20University/COM187/CW3/DisplayAvailableBooks.png)

The Display Available Books screen is shown when the user selects "Display Available Books" from the main menu. The user will be shown a full list of the books currently available, and can use the options on the right side of the screen to filter through the list easier and quicker. Once a book is selected, the book can then be loaned out by clicking the "Loan Book" button. The user will be asked for the name of who is loaning the book.

## Display Loaned Books

![Display Loaned Books](http://imgserv.mythicalcuddles.xyz/Ulster%20University/COM187/CW3/DisplayLoanedBooks.png)

The Display Books on Loan screen is shown when the user selects "Display Books on Loan" from the main menu. The user will be shown a full list of books that are currently out on loan, and can use the options on the right side of the screen to filter through the list easier and quicker. Once a book is returned, the book can be selected from the list and by clicking the "Return Book" button, the book is set to available again and will appear in the available books screen.

___

# Getting Started

## Prerequisites

- Java 8

## Running

1. Head over to [releases](https://github.com/MythicalCuddles/Book-Loaning-System/releases) and download the version you wish to use.
2. Open Book-Loaning-System.jar to start the application.

___

# Additional information

## What I learned from this project
- JavaFX & FXML
- Superclass and subclass development
- Creating array of objects
- Saving objects to files
- Importing and Exporting objects to files
- Setting up Parents, Scenes and Stages
- Exception Handling
- Enum datatypes

___

<p align="center">
  <a href="https://mythicalcuddles.xyz"><img src="http://imgserv.mythicalcuddles.xyz/Signature.png"></a>
  <a href="https://www.paypal.me/mythicalcuddles"><img src="https://img.shields.io/badge/Support%20the%20Developer-Donate%20via%20PayPal-ffa329.svg"></a>
  <a href="https://www.patreon.com/mythicalcuddles"><img src="https://img.shields.io/badge/Support%20the%20Developer-Become%20a%20Patreon-ffa329.svg"></a>
</p>

package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.io.Serializable;
import java.text.*;

public class Libary implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Customer> customers;
	ArrayList<Books> books;
	private final int DAYS = 1000 * 60 * 60 * 24; 
	

	public Libary() {

		this.customers = new ArrayList<Customer>();
		this.books = new ArrayList<Books>();

	}
	
	public int getTotal() {
		return this.customers.size();
	}
	
	public void addCustomer(String name, String phoneNumber, String streetName, int streetNum, int zipCode, String city,
			String country) {

		Customer cus = new Customer(name, phoneNumber, streetName, streetNum, zipCode, city, country);
        int length=this.customers.size();
        cus.setLibaryCard(length+1);
		this.customers.add(cus);
		System.out.println(cus.toString());

	}

	public void addBook(String title, String author, String genre, String publisher, String shelf) {
		Books book = new Books(title, author, genre, publisher, shelf);
		int length=this.books.size();
		book.setId(length+1);
		this.books.add(book);
		System.out.println(book.toString());
	}

	public void retrieveBookList() {
		sortByAuthor(); 
		System.out.println("\n" + "Currently there are: " + books.size() + " books in the library.");
		for (int i = 0; i < books.size(); i++) {
			System.out.println("(Book ID: "+ books.get(i).getBookId() +")" + " " + books.get(i).getTitle() + ", by " + books.get(i).getAuthor());
			System.out.println("Status: " + books.get(i).getStatus() + "\n");
			
		}
	}
	
public void retrieveDelayedBookList() {
	 Date date = new Date();
	    // date.setTime(date.getTime() + (15 * DAYS)); // Setting date to +15 days from today just to check if it works.
	        int count = 0;
	        System.out.println("The delayed book list:");
	        for (int i = 0; i < books.size(); i++) {
	            if(((date.getTime() / DAYS
	            		) - books.get(i).getBorrowDate() > 14)) { // Takes current date and subtract it by the date user borrowed book (also checks if book is borrowed)
	                count++;
	                if (count > 0) {
	                long amountOfDays = (date.getTime() / DAYS) - (books.get(i).getBorrowDate() + 14); // Returns how many day it is late (counts after 14+)
	                
	                System.out.println("(Book ID: "+ books.get(i).getBookId() +")" + " " + books.get(i).getTitle() + ", by " + books.get(i).getAuthor() + " is late by " + amountOfDays + " days");
	                }
	                
	                
	            } 
	             
	            else if(count == 0) {
	                System.out.println("There is no delayed books.");
	                break;
	        
	            }
	        } 
	    }


	

	public void retrieveCustomerHistory(int libraryCard) {
		if (checkCustomerId(libraryCard) == true) {
			String info = retrieveCustomerObject(libraryCard).customerInformation();
			
	
			System.out.println(info);
		}
		

		else {
			System.out.println("Customer not found.");

		}
	}

	
	

	public void addBorrowedBook(int bookId) {

		books.get(bookIndex(bookId)).addCounter();
		books.get(bookIndex(bookId)).setStatus("Borrowed");;

	}
	
	public int returnBorrowedBook(int bookId) {
		Date date = new Date();
		int lateDays = 0; // date.getTime / DAYS
		if(18570  - (retrieveBookObject(bookId).getBorrowDate()) > 14) {
			lateDays = (int) (18570 - (retrieveBookObject(bookId).getBorrowDate() + 14));
		}
		
		return lateDays;
		
	}
	
	public String returnDate(int bookId) {
		Date date = new Date();
		date.setTime(date.getTime() + (14 * DAYS)); // Takes today's date and adds 14 days to it (to specifiy return date)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); // Converts the output to "YEAR-MONTH-DAY" format
		return sdf.format(date);
	}
	
	public void bookStatistics() {
		sortByPopularity();
		System.out.println("List of the three most borrowed books in the library");
		for (int i = 0; i < 3; i++) {
			System.out.println(books.get(i).getTitle() + " has been borrowed out: " + books.get(i).getCounter() + " times");
		}
	}
	
	
	
	public void sortByAuthor() {
		Collections.sort(books, new Comparator<Books>() {
			public int compare(Books book1, Books book2) {
				int comparasion = 0;
				comparasion = book1.getAuthor().compareTo(book2.getAuthor());
				return comparasion;
			}
		});
	}
	
	public void sortByPopularity() {
		Collections.sort(books, new Comparator<Books>() {
			public int compare(Books book1, Books book2) {
				int comparasion = 0; 
					comparasion = Integer.compare(book2.getCounter(), book1.getCounter());
				return comparasion;
			}
		});
	}
	
	public Books retrieveBookObject(int bookId) {
		Books found = books.get(bookIndex(bookId));
		return found;

	}
	
	

	public Customer retrieveCustomerObject(int libraryCard) {
		
			Customer found = customers.get(customerIndex(libraryCard));
			return found;
	}

	public boolean checkCustomerId(int libaryCard) {
		boolean check = false;
		for (int i = 0; i < customers.size(); i++) {

			if (customers.get(i).getLibaryCard() == libaryCard) {
				check = true;

			}

		}
		return check;
	}

	public int customerIndex(int libaryCard) {

		for (int i = 0; i < customers.size(); i++) {

			if (customers.get(i).getLibaryCard() == libaryCard) {
				return i;

			}

		}
		return -1;
	}

	public boolean checkbookId(int bookId) {
		boolean check = false;
		for (int i = 0; i < books.size(); i++) {

			if (books.get(i).getId() == bookId) {
				check = true;

			}

		}
		return check;
	}

	public int bookIndex(int bookId) {

		for (int i = 0; i < books.size(); i++) {

			if (books.get(i).getId() == bookId) {
				return i;

			}

		}
		return -1;
	}
	
	
	
	public int customerBookIndex(int libraryCard, int bookId) {

		for (int i = 0; i < retrieveCustomerObject(libraryCard).loanedBooks.size(); i++) {

			if (retrieveCustomerObject(libraryCard).loanedBooks.get(i).getId() == bookId) {
				return i;

			}

		}
		return -1;
	}
	
}
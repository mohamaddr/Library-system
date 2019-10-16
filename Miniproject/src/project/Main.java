package project;



import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main implements Serializable{
	  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	File librarybooksfile=new File("text.librarybooks");
    File customersfile=new File("text.customers");
	Libary Customer = new Libary();
	Libary Books = new Libary();
	Scanner input = new Scanner(System.in);

	int option;
	public void run() throws InputMismatchException,IOException, ClassNotFoundException,NotSerializableException ,EOFException{
		fileReaderbooks(); 
		fileReaderCustomers();
		
	
	

	
		do {	
			
			try {
			fileWriterbooks();
			fileWriterCustomers();
			menu();
			
			System.out.print(" Type the option number: ");
//			try {
//				 option = input.nextInt();
//		
//		
			   
			
			input.nextLine();
			
			 option = input.nextInt();
} catch(InputMismatchException e) {  
			
			System.out.print("wrong input");
		   
		}
			switch (option) {
			case 1:
				registerCustomer();
				break;

			case 2:
				addBook();
				break;

			case 3:
				borrowBook();
				
				break;

			case 4:
				returnBook();
				
				break;
				
			case 5:
				Books.retrieveBookList();
				break; 
			case 6: 
				
				Books.retrieveDelayedBookList();

				break;
			case 7: 
				System.out.println("What's the customers libary ID?");
				int libraryCard = input.nextInt();
				Customer.retrieveCustomerHistory(libraryCard);
				break;
				
			case 8: 
				Books.bookStatistics();
				break;
			case 9 :
				System.out.println("Write the author name :");
				String author =input.nextLine();
				SearchByauthor(author);
				break;
			case 10 : 
				for(int i = 0; i < Customer.customers.size(); i++) {
					System.out.println(Customer.customers.get(i).customerInformation());
				}
				break;
			case 11:
				allBorrowedbooks();
				break;
			case 12:
				System.out.println("Thank you for visiting our library . See you soon!");
				System.out.println();
				
				break;
			default:
				System.out.println("Option " + option + " is not valid");
				System.out.println();
				break;
			
				
			
			
			}
       

			
		}
		
			
			while (option != 12);


	}
	   

	public void fileWriterbooks() throws IOException { // writing the array list of books to the text.librarybooks
		FileOutputStream stream = new FileOutputStream(librarybooksfile);
	    ObjectOutputStream out= new ObjectOutputStream(stream);
		out.writeObject(Books.books);
 		out.close();
		stream.close();
	}
	public void fileReaderbooks() throws IOException, ClassNotFoundException,NotSerializableException ,EOFException{ // reading all the objects books to the library books array list when we start the program
		FileInputStream stream = new FileInputStream(librarybooksfile);
		  ObjectInputStream in =new ObjectInputStream(stream);
 	 	    Books.books=(ArrayList<Books>)in.readObject();
		   in.close();
		   stream.close();
	   } 
	 public void fileReaderCustomers() throws IOException, ClassNotFoundException,NotSerializableException ,EOFException{// reading all the objects customers to the customers array list when we start the program
			FileInputStream stream = new FileInputStream(customersfile);
			  ObjectInputStream in =new ObjectInputStream(stream);  
	 	 	    Customer.customers=(ArrayList<Customer>)in.readObject();
			   in.close();
			   stream.close();
		   } 
	 public void fileWriterCustomers() throws IOException { // writing array list of customers to the text.customer  
			FileOutputStream stream = new FileOutputStream(customersfile);
		    ObjectOutputStream out= new ObjectOutputStream(stream);
			out.writeObject(Customer.customers);
			out.close();
			stream.close();
		}
	 
	public void SearchByauthor(String author ) { // loop through books array list and return the book by the given author 
		int count = 0;
		for(int i= 0 ; i <Books.books.size(); ++i) {
			if(Books.books.get(i).getAuthor().equalsIgnoreCase(author)){
					System.out.println(Books.books.get(i).toString());
					count ++;
				}
			}
		if (count == 0) {
			System.out.println("There is no book with the given author");
		}
		} 
		
	
	public void allBorrowedbooks() {
		int count = 0;
		for (int i =0 ; i< Books.books.size(); i++) {
			if(Books.books.get(i).getStatus().equalsIgnoreCase("Borrowed")) {
				System.out.println(Books.books.get(i).toString());
				count++;
			}
		}
		if(count==0) {
			System.out.println("there is no borrowed books in the library.");
		}
	}
	

	public static void menu() {
		System.out.println(" ");
		System.out.println(" === Welcome to the Libary System === ");
		System.out.println(" Choose an option below: ");
		System.out.println(" ");
		System.out.println(" 1. Register a customer. ");
		System.out.println(" 2. Register a book. ");
		System.out.println(" 3. Borrow book. ");
		System.out.println(" 4. Return book. ");
		System.out.println(" 5. Retrieve book list. ");
		System.out.println(" 6. Retrieve delayed book list.");
		System.out.println(" 7. Retrieve customer book history. ");
		System.out.println(" 8. Most borrowed books. ");
		System.out.println(" 9. Search by author. ");
		System.out.println(" 10. Retrieve customer list. ");
		System.out.println(" 11. Retrieve borrowed books.");
		System.out.println(" 12. Quit this program. ");

	}

	public void registerCustomer() {
		
		Scanner input = new Scanner(System.in);

		System.out.println("Register customers's name?");
		String name = input.nextLine();

		System.out.println("Register Customer's phone Number?");
		String phoneNumber = input.nextLine();
		
		System.out.println("Customer's Street Name?");
		String streetName = input.nextLine();

		System.out.println("Customer's Street Number?");
		int streetNum = input.nextInt();
		input.nextLine();

		System.out.println("Customer's Zip Code?");
		int zipCode = input.nextInt();
		input.nextLine();

		System.out.println("Customer's City?");
		String city = input.nextLine();

		System.out.println("Customer's Country?");
		String country = input.nextLine();

		Customer.addCustomer(name, phoneNumber, streetName, streetNum, zipCode, city, country);
	}

	public void addBook() {
		System.out.println("Register book title?");
		String title = input.nextLine();

		System.out.println("What's the name of the author?");
		String author = input.nextLine();

		System.out.println("What genre is it?");
		String genre = input.nextLine();

		System.out.println("Name of the publisher?");
		String publisher = input.nextLine();

		System.out.println("Which book shelf is it in?");
		String shelf = input.nextLine();

		Books.addBook(title, author, genre, publisher, shelf);

	}
	
	public void borrowBook() {
		System.out.println("What's your libary card ID?");
		int libraryCard = input.nextInt();
		System.out.println("What's the ID of the book you want to borrow?");
		int bookId = input.nextInt();
		input.nextLine();
		if (Customer.checkCustomerId(libraryCard) == true) {// check if the customer with the given id  exist in the library
			if (Books.checkbookId(bookId) == true) {// check if the book with given id exist in the library
				if(Books.retrieveBookObject(bookId).getStatus().equalsIgnoreCase("Available")) {// check if the book is available to borrow 
				Books.addBorrowedBook(bookId); // change the status of the book to borrowed and count one to calculate how many time this books has been borrowed
				Customer.retrieveCustomerObject(libraryCard).addLoanedBooks(Books.retrieveBookObject(bookId)); // add the book to customer array list of loaned books
				System.out.println("(Book ID: " + bookId + ") " + Books.retrieveBookObject(bookId).getTitle() + " has been borrowed by the customer: " + Customer.retrieveCustomerObject(libraryCard).getName() + " (Customer ID: " + libraryCard +")");
				System.out.println("Book should be returned: " + Books.returnDate(bookId)); // 
				}
				else {
					System.out.println("Book is currently borrowed");
				}
			} 
			else {
				System.out.println("Book not found");
			}
		}  else {
			System.out.println("Customer not found");

		}
	}
	
	public void returnBook() {
        System.out.println("What's the customers libary ID?");
        int libraryCard = input.nextInt();
        System.out.println("What's the ID of the book you want to return?");
        int bookId = input.nextInt();
	input.nextLine();
       int lateDays = Books.returnBorrowedBook(bookId);
       if(Books.retrieveBookObject(bookId).getStatus().equalsIgnoreCase("Borrowed")){
        if (Customer.retrieveCustomerObject(libraryCard).retrieveBorrowedBook(bookId) != null) {
        if (lateDays > 0) { // If customer has this book borrowed, it checks if this book is late.
            Customer.retrieveCustomerObject(libraryCard).ChargeCustomer(Books.returnBorrowedBook(bookId)); // Charges the customer with the amount of days it was late.
            System.out.println("The book was returned " + lateDays + " days after due date.");
            System.out.println("The customer will have to pay: " + (lateDays * 2) + " SEK fee.");
           Books.retrieveBookObject(bookId).setStatus("Available");
        } else {
        // If book is not late, book is returned to library
        	Books.retrieveBookObject(bookId).setStatus("Available");
        Books bookInfo = Books.retrieveBookObject(bookId);
        System.out.println("(Book ID: " + bookId + ") " + bookInfo.getTitle() + ", by " + bookInfo.getAuthor() + " has been returned to the library.");
         Customer.retrieveCustomerObject(libraryCard).setBookStatus(bookId);
    }
        } else {
            // If another customer tries to return a book of another person or the book is not borrowed at all.
            System.out.println("This book is not borrowed by this user or borrowed at all.");
        }
        }else {System.out.println("this book is not borrwed");}
	}

	
	public static void main(String[] args) throws IOException, ClassNotFoundException,NotSerializableException ,EOFException{
		Main program = new Main();
		program.run();
	}

}
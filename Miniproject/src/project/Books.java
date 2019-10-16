package project;

import java.util.concurrent.atomic.AtomicInteger;
import java.io.Serializable;
import java.util.Date;


public class Books implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String author;
	private String genre;
	private String publisher;
	private String shelf;
	private int bookId;
	private int counter;
	private String status;
	private long borrowDate;
	public static AtomicInteger count = new AtomicInteger(0);
	private final int DAYS = 1000 * 60 * 60 * 24; 


	
	
	Books(String title, String author, String genre, String publisher, String shelf) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.shelf = shelf;
		this.bookId = count.incrementAndGet();
		this.counter = 0;
		this.status = "Available";
		this.borrowDate = 0;
		
	}
	
	public long getBorrowDate() {
		Date date = new Date();
		borrowDate = date.getTime() / DAYS; // Returns the amount of miliseconds since 1970 - when divided by "DAYS" it convers to amount of days.
		return borrowDate;
	}
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getCounter() {
		return counter;
	}

	public void addCounter() {
		this.counter = counter + 1;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public static AtomicInteger getCount() {
		return count;
	}

	public static void setCount(AtomicInteger count) {
		Books.count = count;
	}

	public int getId() {
		return bookId;
	}


	public void setId(int id) {
		this.bookId = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public void setBorrowDate(int borrowDate) {
		this.borrowDate = borrowDate;
	}

	@Override
	public String toString() {
		String info = "";
		info += "\n";
		info += "Book has been registered: " + "\n";
		info += "Title: " + title + "\n";
		info += "Book ID: " + bookId + "\n";
		info += "Author: " + author + "\n";
		info += "Genre: " + genre + "\n";
		info += "Book Shelf: " + shelf + "\n";
		info +="Status: " + this.status + "\n";
		return info;
	}
	
	public String bookInformation () {
		String info = "";
		info += "\n";
		info += "Title: " + title + "\n";
		info += "Book ID: " + bookId + "\n";
		info += "Author: " + author + "\n";
		info += "Genre: " + genre + "\n";
		info += "Book Shelf: " + shelf +"\n";
		info += "status: " + getStatus() +"\n";

		
		return info;
	}


}
package main.frontend.controller;

public class bookData {
    
    private final Integer bookId;
    private final String title;
    private final String author;
    private final String genre;
    private final String publisher;
    private final String status;
    public bookData(Integer bookId, String title, String author, String publisher, String genre, String status){
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.status = status;
    }
    public Integer getBookId(){
        return bookId;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getGenre(){
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }
    public String getStatus() { return status; }
}

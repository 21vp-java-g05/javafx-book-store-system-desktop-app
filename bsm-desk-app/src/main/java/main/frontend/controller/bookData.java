package main.frontend.controller;

public class bookData {
    
    private Integer bookId;
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private String image;
    public bookData(Integer bookId, String title, String author, String publisher, String genre, String image){
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.image = image;
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
    public String getImage(){
        return image;
    }

    public String getPublisher() {
        return publisher;
    }
}

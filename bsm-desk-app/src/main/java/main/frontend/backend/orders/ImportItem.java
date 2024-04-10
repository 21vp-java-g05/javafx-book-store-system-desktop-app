package main.frontend.backend.orders;

import main.frontend.backend.objects.Book;

public class ImportItem {
    private Book book;
    private int quantity;
    private float importPrice;

    public ImportItem(Book book, int quantity, float importPrice) {
        this.book = book;
        this.quantity = quantity;
        this.importPrice = importPrice;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }
}

package main.frontend.backend.orders;

public class ImportBook {

    private final Integer importID;
    private final Integer bookID;
    private final String title;
    private final Integer quantity;
    private final Double price;
    private final Integer remaining;

    public ImportBook(Integer importID, Integer bookID, String title, Integer quantity, Double price, Integer remaining) {
        this.importID = importID;
        this.bookID = bookID;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.remaining = remaining;
    }

    public Integer getImportID() {
        return importID;
    }

    public Integer getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getRemaining() {
        return remaining;
    }
}

package com.example.csd214_lab2_mahib;

public class BooksInventoryRecords {
private int bookid;
private String bookName;
private String author;
private String publisher;
private float price;
private int quantity;

private String message;

public BooksInventoryRecords(int bookid, String bookName, String author, String publisher, float price, int quantity){
this.bookid=bookid;
this.bookName=bookName;
this.author=author;
this.publisher=publisher;
this.price=price;
this.quantity=quantity;
}

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

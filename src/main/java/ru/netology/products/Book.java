package ru.netology.products;

public class Book extends Product {
    private String title;
    private String author;

    public Book(int id, String name, int price, String title, String author) {
        super(id, name, price);
        this.title = title;
        this.author = author;
    }
}

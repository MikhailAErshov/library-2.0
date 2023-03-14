package com.example.library20.book;

import java.util.ArrayList;

public class Books {
    private ArrayList<Book> books;

    public Books(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}

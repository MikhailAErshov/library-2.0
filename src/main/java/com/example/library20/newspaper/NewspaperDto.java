package com.example.library20.newspaper;

import com.example.library20.book.BookDto;

import java.time.Instant;

public class NewspaperDto extends BookDto {

    private Instant date;

    public Instant getDate() {
        return this.date;
    }

    public NewspaperDto(String name, Instant date) {
        super(name, null);
        this.date = date;
    }
}

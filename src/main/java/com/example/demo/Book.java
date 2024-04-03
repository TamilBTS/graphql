package com.example.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record Book(String id, String name, int pageCount, String authorId) {

    private static List<Book> books = Arrays.asList(
            new Book("book-1", "Effective Java", 416, "author-1"),
            new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
            new Book("book-3", "Down Under", 436, "author-3"),
            new Book("book-4", "Down Top", 436, "author-3"),
            new Book("book-6", "Happy Kidr", 498, "author-2")
    );

    public static Book getById(String id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static Iterable<Book> booksByAuthor(Author author) {
        return books.stream().filter(book -> book.authorId.equalsIgnoreCase(author.id()))
                .collect(Collectors.toList());
    }

    public static Iterable<Book> books() {
        return books;
    }

    public static Book addBook(String name, int pageCount, String authorId) {
        Book book = new Book(UUID.randomUUID().toString(), name, pageCount, authorId);
        books.add(book);
        return book;
    }
}
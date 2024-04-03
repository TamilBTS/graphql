package com.example.demo;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @QueryMapping(name = "books")
    Iterable<Book> allbooks() {
        return Book.books();
    }

    @QueryMapping
    Iterable<Author> authors() {
        return Author.authors();
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }

    @SchemaMapping
    public Iterable<Book> books(Author author) {
        return Book.booksByAuthor(author);
    }

    @MutationMapping
    public Book addBook(@Argument BookInput book) {
        return Book.addBook(book.name(), book.pageCount(), book.author_id());
    }

    private record BookInput(String name, int pageCount, String author_id) {}


}
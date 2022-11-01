package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.BookData;

import java.util.List;

public interface IBookService {
    BookData addBook(BookDTO bookDTO);

    List<BookData> findAll();

    BookData FindById(int bookId);

    String deleteById(int bookId);

    BookData updateBookData(int id, BookDTO bookDTO);
}
package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<BookData,Integer> {
}
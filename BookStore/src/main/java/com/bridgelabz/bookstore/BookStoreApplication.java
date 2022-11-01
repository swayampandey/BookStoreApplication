package com.bridgelabz.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookStoreApplication {

    public static void main(String[] args) {
        System.out.println("Book Store App is Connected");
        SpringApplication.run(BookStoreApplication.class, args);
    }

}

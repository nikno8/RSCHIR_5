package com.mirea.rschirpractice5.repository;

import com.mirea.rschirpractice5.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}

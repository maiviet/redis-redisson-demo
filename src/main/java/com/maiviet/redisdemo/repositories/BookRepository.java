package com.maiviet.redisdemo.repositories;

import com.maiviet.redisdemo.entities.BookEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    BookEntity findById (long id);
}

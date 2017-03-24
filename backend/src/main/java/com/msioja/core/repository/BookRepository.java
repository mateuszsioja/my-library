package com.msioja.core.repository;

import com.msioja.core.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();

    Book findByBookId(Long id);

    @Query("select b from Book b join b.user u where u.userId = :userId")
    List<Book> findBooksForUserById(@Param("userId") Long userId);
}

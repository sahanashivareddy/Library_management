package org.paycorp.librarymanagmentsystem.repository;

import java.util.List;

import org.apache.catalina.User;
import org.paycorp.librarymanagmentsystem.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Book, Integer> {
	@Query("select x from Book x where bookname=?1")
	List<Book> fetchByBookName(String bookname);

	@Query("select x from Book x where authorname=?1")
	List<Book> fetchAuthor(String authorname);
	
    @Modifying
	@Query("delete from Book x where x.authorname=?1")
	void deleteByName(String authorname);
}

package org.paycorp.librarymanagmentsystem.repository;

import java.util.List;
import java.util.Optional;

import org.paycorp.librarymanagmentsystem.dto.Book;
import org.paycorp.librarymanagmentsystem.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MyRepository extends JpaRepository<User, Integer> {
	@Query("Select x from Book x where x.bookname=?1")
	List<Book> findBybookname(String bookname);

	@Query("select x from Book x where x.bookid=?1")
	Book findByBookId(int bookid);
	
	@Query("select x from User x where x.id=?1")
	User findByUserId(int id);

	void save(Optional<User> uid);

	void save(Book book);
}

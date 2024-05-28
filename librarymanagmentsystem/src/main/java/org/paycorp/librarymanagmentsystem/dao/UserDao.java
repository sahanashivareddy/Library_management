package org.paycorp.librarymanagmentsystem.dao;

import java.util.List;

import org.paycorp.librarymanagmentsystem.dto.Book;
import org.paycorp.librarymanagmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	UserRepository repository;

	public void addbook(Book book) {
		repository.save(book);
	}

	public List<Book> fetchall() {
		return repository.findAll();
	}

	public List<Book> fetchname(String bookname) {
		return repository.fetchByBookName(bookname);
	}

	public void update(String name) {
	    repository.fetchByBookName(name);
	}

	public List<Book> fetchauthor(String authorname) {
		return repository.fetchAuthor(authorname);
	}

	public void delete(String authorname) {
		 repository.deleteByName(authorname);
		
	}
}

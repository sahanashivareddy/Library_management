package org.paycorp.librarymanagmentsystem.service;

import java.util.List;

import org.hibernate.validator.constraints.ISBN;
import org.paycorp.librarymanagmentsystem.dao.UserDao;
import org.paycorp.librarymanagmentsystem.dto.Book;
import org.paycorp.librarymanagmentsystem.dto.ResponseStructure;
import org.paycorp.librarymanagmentsystem.exception.DataNotFoundException;
import org.paycorp.librarymanagmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GeneralService {

	@Autowired
	UserDao dao;

	@Autowired
	UserRepository repository;

	@Autowired
	ResponseStructure structure;

	public ResponseStructure<Book> addBook(Book book) {
		dao.addbook(book);
		structure.setData(book);
		structure.setMessage("Data saved success");
		structure.setStatus(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseStructure fetchall() {
		List<Book> list = dao.fetchall();
		if (list.isEmpty()) {
			throw new DataNotFoundException();
		} else {
			structure.setData(list);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("data found");
		}
		return structure;
	}

	public ResponseStructure fetchname(String bookname) {
		List<Book> name = dao.fetchname(bookname);
		if (!name.isEmpty()) {
			structure.setData(name);
			structure.setMessage("data found");
			structure.setStatus(HttpStatus.FOUND.value());
		} else {
			throw new DataNotFoundException();
		}
		return structure;
	}

	public ResponseStructure<Book> update(String bookname, Book book) {
		List<Book> name = dao.fetchname(bookname);
		if (!name.isEmpty()) {
			repository.save(book);
			structure.setData(book);
			structure.setMessage("data updated");
			structure.setStatus(HttpStatus.FOUND.value());
			
		} else {
			throw new DataNotFoundException();
		}
		return structure;
	}

	public ResponseStructure delete(String authorname) {
		List<Book> author = dao.fetchauthor(authorname);
		if (!author.isEmpty()) 
		{
			dao.delete(authorname);
			structure.setMessage("data deleted");
			structure.setStatus(HttpStatus.FOUND.value());
		}else {
			throw new DataNotFoundException();
		}
		return structure;
	}

	public ResponseStructure fetchauthor(String authorname) {
		List<Book> author = dao.fetchauthor(authorname);
		if (!author.isEmpty()) {
			structure.setData(author);
			structure.setMessage("data found");
			structure.setStatus(HttpStatus.FOUND.value());
		} else {
			throw new DataNotFoundException();
		}
		return structure;
	}
}

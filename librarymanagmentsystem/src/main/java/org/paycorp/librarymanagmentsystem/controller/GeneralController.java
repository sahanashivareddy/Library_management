package org.paycorp.librarymanagmentsystem.controller;

import org.paycorp.librarymanagmentsystem.dao.UserDao;
import org.paycorp.librarymanagmentsystem.dto.Book;
import org.paycorp.librarymanagmentsystem.dto.ResponseStructure;
import org.paycorp.librarymanagmentsystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;

@RestController
public class GeneralController {
	@Autowired
	GeneralService service;

	@Autowired
	UserDao dao;

	@PostMapping("/addbooks")
	public ResponseStructure<Book> addBooks(@RequestBody Book book) {
		return service.addBook(book);
	}

	@GetMapping("/fetchbooks")
	public ResponseStructure<Book> fetchallbooks() {
		return (ResponseStructure<Book>) (service.fetchall());
	}

	@GetMapping("/fetchname/{bookname}")
	public ResponseStructure fetchname(@PathVariable String bookname) {
		return (ResponseStructure<Book>) (service.fetchname(bookname));
	}

	@GetMapping("/fetchauthor/{authorname}")
	public ResponseStructure<Book> fetchauthorname(@PathVariable String authorname) {
		return (ResponseStructure<Book>) (service.fetchauthor(authorname));
	}

	@PutMapping("/update/{bookname}")
	public ResponseStructure<Book> update(@PathVariable String bookname, @RequestBody Book book) {
		return (ResponseStructure<Book>) (service.update(bookname, book));
	}

	@DeleteMapping("/delete/{authorname}")
	@Transactional
	public ResponseStructure<Book> delete(@PathVariable String authorname) {
		return (ResponseStructure<Book>) service.delete(authorname);
	}
}

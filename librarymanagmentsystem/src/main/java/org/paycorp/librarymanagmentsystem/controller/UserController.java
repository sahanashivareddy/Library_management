package org.paycorp.librarymanagmentsystem.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.paycorp.librarymanagmentsystem.dto.Book;
import org.paycorp.librarymanagmentsystem.dto.ResponseStructure;
import org.paycorp.librarymanagmentsystem.dto.User;
import org.paycorp.librarymanagmentsystem.repository.MyRepository;
import org.paycorp.librarymanagmentsystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	ResponseStructure structure;
	
	@Autowired
	MyRepository myRepository;
	
	@Autowired
	GeneralService generalService;

	@PostMapping("/saveuser")
	public ResponseStructure<User> saveUser(@RequestBody User user) {
		myRepository.save(user);
		structure.setData(user);
		structure.setMessage("User added");
		structure.setStatus(HttpStatus.FOUND.value());
		return structure;
	}
//	@GetMapping("/fetchuser/{id}")
//    public ResponseStructure<User> fetchUser(@PathVariable int id)
//    {
//		Optional<User> userid = myRepository.findById(id);
//		if(userid.isPresent())
//			
//    }

	@PostMapping("/borrow/{id}/{bookid}")
	public ResponseStructure<User> borrow(@PathVariable int id, @PathVariable int bookid) {
		Book bid = myRepository.findByBookId(bookid);
		User cid = myRepository.findByUserId(id);
		if (cid != null) {
			Book book = bid;
			cid.getBooks().add(book);
			book.setUser(cid);
			myRepository.save(cid);
			structure.setData(cid);
			structure.setMessage("borrowed book");
			structure.setStatus(HttpStatus.FOUND.value());
		} else {
			structure.setData(null);
			structure.setMessage("book not found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
		}
		return structure;
	}
	
	@PutMapping("/returnbook/{id}/{bookid}")
	public ResponseStructure<User> returnBook(@PathVariable int id, @PathVariable String bookid)
	{
		User user=myRepository.findByUserId(id);
		Book book=new Book();
		if(user!=null)
		{
			Book bid = user.getBooks().stream()
					.filter(b->b.getBookid()==bookid)
					.findFirst().orElse(null);
			if(bid!=null)
			{
				user.getBooks().remove(bid);
				book.setUser(null);
				myRepository.save(user);
				structure.setData(user);
				structure.setMessage("Book returned");
				structure.setStatus(HttpStatus.FOUND.value());
			}else {
				structure.setData(null);
				structure.setMessage("Book is not issued");
				structure.setStatus(HttpStatus.NOT_FOUND.value());
			}
		}else {
			structure.setData(null);
			structure.setMessage("user not found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
		}
		return structure;
	}
}

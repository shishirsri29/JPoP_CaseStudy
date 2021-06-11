package com.library.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.library.demo.model.Book;
import com.library.demo.model.Library;
import com.library.demo.model.User;
import com.library.demo.service.LibraryService;

@RestController
@CrossOrigin
public class LibraryController {

	@Autowired
	private LibraryService libService;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger log = LoggerFactory.getLogger(LibraryController.class);

	@RequestMapping("/libDetails")
	public ResponseEntity<List<Library>> getLibDetails() {

		try {
			List<Library> libList = new ArrayList<Library>();

			libService.getAllLibDetails().forEach(libList::add);
			log.debug(" getLibDetails list size : " + libList.size());
			return new ResponseEntity<>(libList, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception occured while getLibDetails : " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/add/libsDetails")
	public ResponseEntity<Library> addBook(@RequestBody Library lib) {
		try {
			Library libAdded = libService.save(lib);
			return new ResponseEntity<>(libAdded, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/library/users/{userId}") // @RequestParam(required = true)Integer userId
	public ResponseEntity<List<Book>> getUserBooks(@PathVariable("userId") int userId) {
		try {
			List<Library> libList = new ArrayList<Library>();
			List<Book> bookList = new ArrayList<Book>();

			libList = libService.findByUserId(userId);
			for(Library lib : libList) {
				ResponseEntity<Book> response = restTemplate.getForEntity("http://localhost:8080/books" + "/" + lib.getBookId(),
						Book.class);
				bookList.add(response.getBody());
			}
			return new ResponseEntity<>(bookList, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("exception: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("releaseBook/{userId}/{bookId}")
	public ResponseEntity<Object> deleteByUserIdAndBookId(@PathVariable("userId") int userId,
			@PathVariable("bookId") int bookId) {
		try {
			libService.deleteByUserIdBookId(userId, bookId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/library/users")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			ResponseEntity<User[]> response = restTemplate.getForEntity("http://localhost:8081/users", User[].class);
			return new ResponseEntity<>(Arrays.asList(response.getBody()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/library/books")
	public ResponseEntity<List<Book>> getAllBooks() {
		try {
			ResponseEntity<Book[]> response = restTemplate.getForEntity("http://localhost:8080/books", Book[].class);
			return new ResponseEntity<>(Arrays.asList(response.getBody()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/library/userById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) {
		try {
			ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:8081/users" + "/" + userId,
					User.class);
			return response;
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/library/bookById/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable("bookId") int bookId) {
		try {
			ResponseEntity<Book> response = restTemplate.getForEntity("http://localhost:8080/books" + "/" + bookId,
					Book.class);
			return response;
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/library/deleteUserById/{userId}")
	public ResponseEntity<Object> deleteUserById(@PathVariable("userId") int userId) {
		try {
			restTemplate.delete("http://localhost:8081/users" + "/" + userId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/library/deleteUserById/{bookId}")
	public ResponseEntity<Object> deleteBookById(@PathVariable("bookId") int bookId) {
		try {
			restTemplate.delete("http://localhost:8081/books" + "/" + bookId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/library/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		try {
			ResponseEntity<User> response = restTemplate.postForEntity("http://localhost:8081/users", user, User.class);
			return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/library/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		try {
			ResponseEntity<Book> response = restTemplate.postForEntity("http://localhost:8080/books", book, Book.class);
			return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

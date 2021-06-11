package com.book.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.demo.model.Book;
import com.book.demo.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping("")
	public String bookSave(@RequestBody Book book) {
		return bookService.saveBook(book);
	}
	
	@GetMapping("")
	public List<Book> getBooks(){
		return bookService.getBooks();
	}
	
	@GetMapping("/{book_id}")
	public ResponseEntity<Book> getTutorialById(@PathVariable("book_id") Integer id) {
		Optional<Book> bookData = bookService.getBook(id);

		if (bookData.isPresent()) {
			return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/{book_id}")
	public ResponseEntity<Book> updateBook(@PathVariable("book_id") int id, @RequestBody Book book) {
		Book bookData = bookService.updateBook(id, book);

		if (null != bookData) {
			return new ResponseEntity<>(bookData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{book_id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("book_id") int id) {
		try {
			bookService.deleteBook(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}

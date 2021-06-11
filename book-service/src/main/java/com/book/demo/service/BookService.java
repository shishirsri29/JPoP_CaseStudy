package com.book.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.demo.model.Book;
import com.book.demo.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getBooks(){
		return (List<Book>) bookRepository.findAll();
	}
	
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "Book has ben saved!";
	}
	
	public Book updateBook(int id, Book book) {
		Optional<Book> bookData = bookRepository.findById(id);
		Book bookUpdated = null;
		if (bookData.isPresent()) {
			Book bookDetail = bookData.get(); 
			bookDetail.setBook_id(book.getBook_id());
			bookDetail.setBook_description(book.getBook_description());
			bookDetail.setBook_author(book.getBook_author()); bookUpdated =
			bookRepository.save(bookDetail);
		}
		return bookUpdated;
	}

	public void deleteBook(int id) {
		bookRepository.deleteById(id);
	}
	
	public Optional<Book> getBook(Integer id) {
		Optional<Book> bookData = bookRepository.findById(id);
		return bookData;
	}

}

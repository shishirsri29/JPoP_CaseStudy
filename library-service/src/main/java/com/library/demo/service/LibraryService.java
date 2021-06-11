package com.library.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.library.demo.model.Library;
import com.library.demo.repo.LibraryRepository;

@Service
public class LibraryService {

	@Autowired
	LibraryRepository libraryRepo;
	
	public List<Library> getAllLibDetails() {	
			List<Library> libList = new ArrayList<Library>();
			libraryRepo.findAll().forEach(libList::add);
			return libList;	
	}

	public Library save(Library lib) {
		Library libAdded = libraryRepo.save(lib);
		return libAdded;
	}

	public List<Library> findByUserId(int userId) {
		List<Library> libList = new ArrayList<Library>();
		libList = libraryRepo.findByUserId(userId);
		return libList;
	}

	public void deleteByUserIdBookId(int userId, int bookId) {
		
		libraryRepo.deleteByUserIdAndBookId(userId,bookId);
	}
	
}

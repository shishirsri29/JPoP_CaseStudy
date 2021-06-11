package com.library.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.library.demo.model.Library;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Integer> {
    
	List<Library> findByUserId(int userId);

	void deleteByUserIdAndBookId(int userId, int bookId);

}

package rk.lms.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rk.lms.models.Book;

@Repository
public interface LmsRepository extends CrudRepository<Book, Long> {
	
	

}

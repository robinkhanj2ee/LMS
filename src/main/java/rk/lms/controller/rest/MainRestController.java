package rk.lms.controller.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rk.lms.models.Book;
import rk.lms.services.LmsService;

@RestController
public class MainRestController {
	
	@Autowired
	private LmsService lmsService;
	

	
	@GetMapping("/findAllBooks")
	public Collection<Book> getAllBooks(){
		
		return lmsService.getAllbooks();
	}
	
	
/*	@GetMapping("/deleteBook")
	public void deleteBook(@RequestParam long id){
		
		lmsService.delete(id);
		
	}*/
	
/*	@GetMapping("/deleteBook/{id}")
	public void deleteBooktest(@PathVariable long id){
		
		lmsService.delete(id);
		
	}*/

}

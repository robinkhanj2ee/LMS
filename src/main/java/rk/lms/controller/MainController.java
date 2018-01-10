package rk.lms.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.fabric.xmlrpc.base.Data;

import rk.lms.models.Book;
import rk.lms.services.LmsService;

@Controller
public class MainController {

	@Autowired
	private LmsService lmsService;

	@GetMapping(value = "/")
	public String hello(HttpServletRequest req) {

		req.setAttribute("books", lmsService.getAllbooks());
		req.setAttribute("mode", "BOOK_VIEW");

		return "index";
	}

	@GetMapping(value = "/updateBook")
	public String updateBook(@RequestParam long id, HttpServletRequest req) {

		req.setAttribute("book", lmsService.findOne(id));
		req.setAttribute("mode", "BOOK_EDIT");

		return "index";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder){
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), false));
		
	}
	
	
	
	@PostMapping("/save")
	public void saveBook(@ModelAttribute Book book, BindingResult bindingResult ,HttpServletRequest req, HttpServletResponse resp) throws IOException {

		lmsService.saveBook(book);
		
		req.setAttribute("books", lmsService.getAllbooks());
		req.setAttribute("mode", "BOOK_VIEW");

		resp.sendRedirect("/");

	}
	
	@GetMapping(value = "/newBook")
	public String newBook(HttpServletRequest req){
		req.setAttribute("mode", "BOOK_NEW");
		return "index";
	}

	@GetMapping(value = "/deleteBook")
	public void deleteBook(@RequestParam long id, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		lmsService.delete(id);
		
		resp.sendRedirect("/");
	}
}

package br.com.conectasol.sco.controller;

import br.com.conectasol.sco.domain.Book;
import br.com.conectasol.sco.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookWeb {

    @Autowired
    private BookService bookService;

    @GetMapping("/salvar")
    public String salvar() {
        List<Book> bookList = new ArrayList<>();

        bookList.add(new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
        bookList.add(new Book("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
        bookList.add(new Book("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
        bookList.add(new Book("1007", "Spring Data + ElasticSearch", "Rambabu Posa", "01-APR-2017"));
        bookList.add(new Book("1008", "Spring Boot + MongoDB", "Mkyong", "25-FEB-2017"));

        for (Book book : bookList) {
            bookService.save(book);
        }

        return "Done!!!";
    }

    @GetMapping
    public List<Book> listar() {
        return  bookService.findByTitle("Elasticsearch Basics");
    }

    @GetMapping("/get")
    public Book get() {
        return  bookService.findOne("1001");
    }

}

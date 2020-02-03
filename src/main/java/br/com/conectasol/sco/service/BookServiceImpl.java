package br.com.conectasol.sco.service;

import br.com.conectasol.sco.domain.Book;
import br.com.conectasol.sco.domain.BooksElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BooksElasticRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public Book findOne(String id) {
        return bookRepository.findById(id).get();
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findByAuthor(String author, PageRequest pageRequest) {
        return bookRepository.findByAuthor(author, pageRequest);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}

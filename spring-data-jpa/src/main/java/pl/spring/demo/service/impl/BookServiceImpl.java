package pl.spring.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.mapper.MyMapper;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
    private BookDao bookDao;
	
	@Autowired
	private MyMapper myMapper;

    @Override
    @Cacheable("booksCache")
    public List<BookTo> findAllBooks() {
        return myMapper.mapperBookEntityToBookTo(bookDao.findAll());
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
        return myMapper.mapperBookEntityToBookTo(bookDao.findBookByTitle(title));
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
        return myMapper.mapperBookEntityToBookTo(bookDao.findBooksByAuthor(author));
    }

    @Override
    public BookTo saveBook(BookTo book) {
    	BookTo makeSave = myMapper.mapper(bookDao.save(myMapper.mapper(book)));
    	return makeSave;
    }
    
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}

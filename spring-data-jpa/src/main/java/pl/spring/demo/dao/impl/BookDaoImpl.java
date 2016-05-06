package pl.spring.demo.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookEntity;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {

    private final Set<BookEntity> ALL_BOOKS = new HashSet<>();
    
    private Sequence sequence;
    
    public BookDaoImpl() {
        addTestBooks();
    }

    @Override
    public List<BookEntity> findAll() {
        return new ArrayList<>(ALL_BOOKS);
    }

    @Override
    public List<BookEntity> findBookByTitle(String title) {
    	List<BookEntity> matchByTitle = new ArrayList<BookEntity>();
		for(BookEntity bookEntity : ALL_BOOKS){
			if(bookEntity.getTitle().toLowerCase().startsWith(title.toLowerCase())){
				matchByTitle.add(bookEntity);
			}
		}
		return matchByTitle;
    }

    @Override
    public List<BookEntity> findBooksByAuthor(String author) {
    	List<BookEntity> matchByAuthors = new ArrayList<BookEntity>();
		for(BookEntity bookEntity : ALL_BOOKS){
			if(bookEntity.matchingAuthors(author)){
				matchByAuthors.add(bookEntity);
			}
		}
		return matchByAuthors;
    }

    @Override
    @NullableId
    public BookEntity save(BookEntity book) {
/* TASK 2       
 * if (book.getId() == null) {
            book.setId(sequence.nextValue(ALL_BOOKS));
        }*/
        ALL_BOOKS.add(book);
        return book;
    }
    
    @Autowired
    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }
    
    private void addTestBooks() {
        ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", "Wiliam Szekspir"));
        ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", "Hanna Ożogowska"));
        ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", "Jan Parandowski"));
        ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", "Edmund Niziurski"));
        ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki"));
        ALL_BOOKS.add(new BookEntity(6L, "Zemsta", "Aleksander Fredro"));
    }
}

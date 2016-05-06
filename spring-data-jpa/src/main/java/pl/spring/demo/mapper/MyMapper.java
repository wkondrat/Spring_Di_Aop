package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

@Service
public class MyMapper {

	public List<BookTo> mapperBookEntityToBookTo(List<BookEntity> bookEntities){
		List<BookTo> mapperEntityToList = new ArrayList<BookTo>(); 
		for(BookEntity bookEntity : bookEntities){
			mapperEntityToList.add(mapper(bookEntity));
		}
		return mapperEntityToList;
	}
	
	public List<BookEntity> mapperBookToToBookEntity(List<BookTo> bookTos){
		List<BookEntity> mapperToEntityList = new ArrayList<BookEntity>(); 
		for(BookTo bookTo : bookTos){
			mapperToEntityList.add(mapper(bookTo));
		}
		return mapperToEntityList;
	}

	public BookTo mapper(BookEntity bookEntity) {
		return new BookTo(bookEntity);
	}
	
	public BookEntity mapper(BookTo bookTo) {
		return new BookEntity(bookTo);
	}
}
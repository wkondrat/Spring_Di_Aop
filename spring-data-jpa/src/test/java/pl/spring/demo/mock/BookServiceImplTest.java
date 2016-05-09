package pl.spring.demo.mock;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookEntity;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookDao bookDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShouldSaveBook() {
        // given
        BookEntity book = new BookEntity(null, "title", "author");
        Mockito.when(bookDao.save(book)).thenReturn(new BookEntity(1L, "title", "author"));
        // when
        Mockito.verify(bookDao).save(book);
        assertEquals(1L, bookDao.save(book).getId().longValue());
    }
}

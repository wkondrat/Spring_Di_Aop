package pl.spring.demo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.IdAware;

@Aspect
public class BookDaoAdvisor {
	@Autowired
	private Sequence sequence;

	@Before("@annotation(nullableId)")
	public void before(JoinPoint joinPoint, NullableId nullableId) throws Throwable {	
		checkNotNullId(joinPoint.getArgs()[0]);	
	/*	BookTo bookTo = (BookTo)joinPoint.getArgs()[0];
		BookDao book = (BookDao)joinPoint.getThis();
		if (((IdAware) bookTo).getId() == null) {
			bookTo.setId(sequence.nextValue(book.findAll()));
		}*/
		
		BookEntity bookEntity = (BookEntity)joinPoint.getArgs()[0];
		BookDao book = (BookDao)joinPoint.getThis();
		if (((IdAware) bookEntity).getId() == null) {
			bookEntity.setId(sequence.nextValue(book.findAll()));
		}
		
	}

	private void checkNotNullId(Object o) {
		if (o instanceof IdAware && ((IdAware) o).getId() != null) {
			throw new BookNotNullIdException();
		}
	}
	
	private boolean hasAnnotation(Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
		boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;

		if (!hasAnnotation && o != null) {
			hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes())
					.getAnnotation(annotationClazz) != null;
		}
		return hasAnnotation;
	}
}

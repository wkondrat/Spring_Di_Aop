package pl.spring.demo.to;

import org.springframework.beans.factory.annotation.Autowired;

public class BookTo implements IdAware {
    private Long id;
    private String title;
    private String authors;

    public BookTo() {
    }

    public BookTo(Long id, String title, String authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }
    
    public BookTo(BookEntity bookEntity) {
		this.id = bookEntity.getId();
		this.title = bookEntity.getTitle();
		this.authors = bookEntity.getAuthor();
	}

	@Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }
    
    @Autowired
    public void setAuthors(String authors) {
        this.authors = authors;
    }
}

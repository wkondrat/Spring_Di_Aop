package pl.spring.demo.to;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookEntity implements IdAware {
	private Long id;
	private String title;
	private List<AuthorTo> authors = new ArrayList<AuthorTo>();

	public BookEntity(Long id, String title, String... authorsList) {
		this.id = id;
		this.title = title;
		addAuthors(Arrays.asList((authorsList)));
	}

	private void addAuthors(List<String> authorsList) {
		for (String author : authorsList) {
			authors.add(new AuthorTo(author));
		}
	}

	public BookEntity(BookTo bookTo) {
		this.id = bookTo.getId();
		this.title = bookTo.getTitle();
		this.authors.add(new AuthorTo(bookTo.getAuthors()));
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		String authors = "";
		for (AuthorTo authorTo : this.authors) {
			authors = authors + authorTo.toString();
		}
		return authors;
	}

	public void setId(Long nextBookId) {
		this.id = nextBookId;
	}
	
	public String toString(){
		return this.id + " " + this.title + " " + this.getAuthor();
	}
	
	public boolean matchingAuthors(String data){
		boolean isMatched = false;
		for(AuthorTo authorTo : authors){
			if(authorTo.matchingAuthors(data)){
				isMatched = true;
			}
		}
		return isMatched;
	}

}
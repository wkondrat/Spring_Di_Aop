package pl.spring.demo.to;

public class AuthorTo {
	private String firstName;
	private String lastName;

	public AuthorTo(Long id, String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public AuthorTo(String data) {
		if (data != null && data.contains(" ")) {
			this.firstName = data.split(" ")[0];
			this.lastName = data.split(" ")[1];
			return;
		}
		this.firstName = null;
		this.lastName = null;
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}

	public boolean matchingAuthors(String data) {
		boolean isMatched = false;
		if ((this.firstName.toLowerCase() + " " + this.lastName.toLowerCase()).startsWith(data.toLowerCase())) {
			isMatched = true;
		}
		return isMatched;
	}

}
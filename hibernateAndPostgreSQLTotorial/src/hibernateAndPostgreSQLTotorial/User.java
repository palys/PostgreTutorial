package hibernateAndPostgreSQLTotorial;

import java.sql.Date;

public class User {
	
	private int id;
	private String login;
	private String city;
	private Date birthDate;
	
	public User() {
		
	}

	public User(String login, String city, Date birthDate) {
		super();
		this.login = login;
		this.city = city;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
	
	
}

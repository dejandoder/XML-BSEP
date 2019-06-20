package xml_bsep.agent_app.dto;

public class UserDTO {

	private long id;
	private String name;
	private String surname;
	private String username;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public UserDTO(long id, String name, String surname, String username) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}



	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(((UserDTO)obj).getId()==id) return true;
		
		return false;
	}
	
}

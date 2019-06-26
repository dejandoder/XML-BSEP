package xml_bsep.agent_app.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import xml_bsep.agent_app.model.Message;


public class MessageDTO {
	@Size(min=1,max=40)
	private String username1;
	@NotBlank(message="id must not be empty")
	@Min(1)
	private long userId1;
	@Size(min=1,max=40)
	private String username2;
	@NotBlank(message="id must not be empty")
	@Min(1)
	private long userId2;
	@Size(min=0,max=200)
	private String content;
	private Date date;
	
	public MessageDTO() {
		// TODO Auto-generated constructor stub
	}

	public MessageDTO(Message message) {
		this.username1 = message.getFromUser().getUsername();
		this.userId1 = message.getFromUser().getId();
		this.username2 = message.getToUser().getUsername();
		this.userId2 = message.getToUser().getId();
		this.content = message.getContent();
		this.date = message.getDate();
	}

	public String getUsername1() {
		return username1;
	}

	public void setUsername1(String username1) {
		this.username1 = username1;
	}

	public long getUserId1() {
		return userId1;
	}

	public void setUserId1(long userId1) {
		this.userId1 = userId1;
	}

	public String getUsername2() {
		return username2;
	}

	public void setUsername2(String username2) {
		this.username2 = username2;
	}

	public long getUserId2() {
		return userId2;
	}

	public void setUserId2(long userId2) {
		this.userId2 = userId2;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

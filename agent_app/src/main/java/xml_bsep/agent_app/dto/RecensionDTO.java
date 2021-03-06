package xml_bsep.agent_app.dto;

import xml_bsep.agent_app.model.Recension;
import xml_bsep.agent_app.model.RecensionStatus;

public class RecensionDTO {
	
	protected String comment;
    protected int rating;
    protected long id;
    protected RecensionStatus status;
    protected String username;
    protected long userId;
    protected String accUnitName;
    protected long accUnitId;

	public RecensionDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public RecensionDTO(Recension recension) {
		this.comment = recension.getComment();
		this.rating = recension.getRating();
		this.id = recension.getId();
		this.status = recension.getStatus();
		this.username = recension.getUser().getUsername();
		this.userId = recension.getUser().getId();
		this.accUnitName = recension.getAccomodationUnit().getName();
		this.accUnitId = recension.getAccomodationUnit().getId();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RecensionStatus getStatus() {
		return status;
	}

	public void setStatus(RecensionStatus status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getAccUnitName() {
		return accUnitName;
	}

	public void setAccUnitName(String accUnitName) {
		this.accUnitName = accUnitName;
	}

	public long getAccUnitId() {
		return accUnitId;
	}

	public void setAccUnitId(long accUnitId) {
		this.accUnitId = accUnitId;
	}
}


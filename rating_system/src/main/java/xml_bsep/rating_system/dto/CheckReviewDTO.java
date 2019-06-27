package xml_bsep.rating_system.dto;

import javax.validation.constraints.Min;

public class CheckReviewDTO {
	
	@Min(1)
	private long userId;
	
	@Min(1)
	private long accUintId;
	
	public CheckReviewDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getAccUintId() {
		return accUintId;
	}

	public void setAccUintId(long accUintId) {
		this.accUintId = accUintId;
	}
	
}

package xml_bsep.reservation_service.dto;

public class CheckReviewDTO {
	
	private long userId;
	private long accUintId;
	
	public CheckReviewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CheckReviewDTO(long userId, long accUintId) {
		super();
		this.userId = userId;
		this.accUintId = accUintId;
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

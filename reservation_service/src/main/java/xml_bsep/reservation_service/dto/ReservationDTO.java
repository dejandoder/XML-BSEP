package xml_bsep.reservation_service.dto;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.eureka.common.model.Reservation;
import com.eureka.common.model.ReservationStatus;

public class ReservationDTO {
	
	private long id;
	private Date fromDate;
	private Date toDate;
	private String accName;
	private long accId;
	private int rating;
	private int comment;
	private ReservationStatus status;
	private boolean cancelable;
	private boolean review;
	
	public ReservationDTO(Reservation reservation) {
		this.id = reservation.getId();
		this.fromDate = reservation.getFromDate();
		this.toDate = reservation.getToDate();
		this.accName = reservation.getAccUnit().getName();
		this.accId = reservation.getAccUnit().getId();
		this.status = reservation.getStatus();
		
		Date currentDate = new Date();
		
		if(fromDate.after(DateUtils.addDays(currentDate, reservation.getAccUnit().getCancelingPeriod()))) cancelable = true;
		else cancelable = false;
		
	}
	
	public ReservationDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public long getAccId() {
		return accId;
	}

	public void setAccId(long accId) {
		this.accId = accId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public boolean isCancelable() {
		return cancelable;
	}

	public void setCancelable(boolean isCancelable) {
		this.cancelable = isCancelable;
	}

	public boolean isReview() {
		return review;
	}

	public void setReview(boolean review) {
		this.review = review;
	}
		
}

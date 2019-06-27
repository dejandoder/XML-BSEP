package xml_bsep.agent_app.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import xml_bsep.agent_app.model.Reservation;
import xml_bsep.agent_app.model.ReservationStatus;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

public class ReservationDTO {

	private long id;
	
	@DateTimeFormat
	private Date fromDate;
	
	@DateTimeFormat
	private Date toDate;
	

	private String accName;
	
	@Min(1)
	private long accId;
		
	private int rating;
	
	private int comment;
	
	private ReservationStatus status;
	
	private boolean cancelable;
	
	@Size(min=1,max=40)
	private String username;
	
	private boolean agentReserved;
	
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
		this.username = reservation.getUser().getUsername();
		this.agentReserved = reservation.isAgentReserved();
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public boolean isAgentReserved() {
		return agentReserved;
	}

	public void setAgentReserved(boolean agentReserved) {
		this.agentReserved = agentReserved;
	}

}

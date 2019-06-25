package xml_bsep.reservation_service.dto;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CheckReaservationDTO {
	
	private ArrayList<Date> dates;
	
	@Min(1)
	private long accID;
	
	public CheckReaservationDTO() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Date> getDates() {
		return dates;
	}

	public void setDates(ArrayList<Date> dates) {
		this.dates = dates;
	}

	public long getAccID() {
		return accID;
	}

	public void setAccID(long accID) {
		this.accID = accID;
	}
	
	
}

package xml_bsep.acc_service.dto;

import java.util.ArrayList;
import java.util.Date;

public class CheckReaservationDTO {
	
	private ArrayList<Date> dates;
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

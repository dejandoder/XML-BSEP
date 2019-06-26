package xml_bsep.acc_service.utils;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class LoggingUtils {

	private static Marker seMarker = MarkerFactory.getMarker("SE");
	private static Marker npMarker = MarkerFactory.getMarker("NP");
	
	//marker za security related dogadjaje
	public static Marker getSeMarker() {
		return seMarker;
	}
	
	//marker za neporecivost
	public static Marker getNpMarker() {
		return npMarker;
	}
	
	
}

package xml_bsep.agent_app;

public class UrlUtils {

	public static String ratingSystemUrl = "https://localhost:11600";

	public static String getRatingSystemUrl() {
		return ratingSystemUrl;
	}

	public static void setRatingSystemUrl(String ratingSystemUrl) {
		UrlUtils.ratingSystemUrl = ratingSystemUrl;
	}
}

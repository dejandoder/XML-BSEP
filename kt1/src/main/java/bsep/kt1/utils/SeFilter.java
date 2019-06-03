package bsep.kt1.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class SeFilter extends Filter<ILoggingEvent> {

	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (event.getMessage().contains("SE_EVENT")) {
		      return FilterReply.ACCEPT;
		} else {
		      return FilterReply.DENY;
		}
	}

}

package com.chorus.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DatetimeUtil {

	public static String timeAgo(Calendar datetime) {
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT0"));
		datetime.setTimeZone(TimeZone.getTimeZone("GMT0"));
		
		long timeDiff = c.getTimeInMillis() - datetime.getTimeInMillis();
		
		// vamos converter para segundos primeiro, e arredondando um pouco para evitar alguns problemas esquisitos
		long s  = Math.round (timeDiff / 1000);
		// Agora vamos calcular horas, minutos e segundos
		long m = Math.round(timeDiff / (1000*60));
		long h = Math.round(timeDiff / (1000*60*60));
		
		if (h > 24)
			return new SimpleDateFormat("dd/MM").format(datetime.getTime());
		else if (h <= 24 && h > 1)
			return h+"h ago";
		else if (m > 0)
			return m+"m ago";
		else
			return s+"s ago";
		
	}
	
}

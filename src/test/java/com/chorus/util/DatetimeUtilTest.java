package com.chorus.util;

import java.util.Calendar;

import static junit.framework.Assert.*;

import org.junit.Test;

public class DatetimeUtilTest {

	@Test
	public void testDataEmHoras() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, -2);
		
		assertEquals("2h", DatetimeUtil.timeAgo(c));
	}
	
	@Test
	public void testDataEmMinutos() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, -10);
		
		assertEquals("10m", DatetimeUtil.timeAgo(c));
	}
	
	@Test
	public void testDataEmSegundos() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, -3);
		
		assertEquals("3s", DatetimeUtil.timeAgo(c));
	}
	
}

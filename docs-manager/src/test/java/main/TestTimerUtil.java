package main;

import java.text.ParseException;

import com.yhaitao.manager.util.TimerUtil;

public class TestTimerUtil {
	public static void main(String[] args) throws ParseException {
		System.err.println(TimerUtil.getDateFormat(TimerUtil.getSystemDate()));
	}
}

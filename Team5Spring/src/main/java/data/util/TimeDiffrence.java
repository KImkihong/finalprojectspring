package data.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDiffrence {

	private static class TIME_MAXIMUM {
		public static final int SEC = 60;
		public static final int MIN = 60;
		public static final int HOUR = 24;
		public static final int DAY = 30;
		public static final int MONTH = 12;
	}



	public String formatTimeString(String date) {

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date tempDate;
		long curTime = System.currentTimeMillis();
		long regTime;
		long diffTime;
		String msg = null;
		try {
			tempDate = transFormat.parse(date);
			regTime = tempDate.getTime();
			diffTime = (curTime - regTime) / 1000;
			
			if (diffTime < TIME_MAXIMUM.SEC) {
				// sec
				msg = "방금 전";
			} else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
				// min
				msg = diffTime + "분 전";
			} else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
				// hour
				msg = (diffTime) + "시간 전";
			} else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY ||(diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH){
				msg = date.substring(5, 7)+"월 "+date.substring(8, 10)+"일";
			} else {
				msg = date.substring(0, 4)+"년 "+date.substring(5, 7)+"월 "+date.substring(8, 10)+"일";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
}

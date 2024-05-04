package guiutil;

public class Time 
{
	private static final long hour = 3600000;
	private static final long min = 60000;
	private static final long sec = 1000;

	
	public static String formatTime(final long time)
	{
		if(time > sec)
		{
			if(time > min)
			{
				if(time > hour)
				{
					return formatHour(time);
				}
				return formatMinute(time);
			}
			return formatSecond(time);
		}
		return formatMilliSecond(time);
	}
	
	private static String formatHour(final long time)
	{
		long temp = time;
		
		final long h = temp / hour;		temp %= hour;
		
		final long m = temp / min;		temp %= min;
		
		final long s = temp / sec;		temp %= sec;
		
		final long ms = temp;
		
		return String.format("%d:%02d:%02d.%03d", h, m, s, ms);
	}

	private static String formatMinute(final long time)
	{
		long temp = time;

		final long m = temp / min;		temp %= min;
		
		final long s = temp / sec;		temp %= sec;
		
		final long ms = temp;
		
		return String.format("%d:%02d.%03d", m, s, ms);
	}
	
	private static String formatSecond(final long time)
	{
		long temp = time;

		final long s = temp / sec;		temp %= sec;
		
		final long ms = temp;
		
		return String.format("%d.%03d", s, ms);
	}
	
	private static String formatMilliSecond(final long time)
	{
		if(time < 100)
		{
			return String.format("0.0%02d", time);
		}
		return String.format("0.%03d", time);
	}
}

package guiutil;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;
import javax.swing.Timer;

import components.JInitializer;

import javax.swing.JLabel;

/**
 * 
 */
public class Stopwatch extends JInitializer<JLabel, Stopwatch>
{
	
	// ********************************************************
	// ********************************************************
	// ***									             	***
	// ***	Static Fields	       				 			***
	// ***											        ***	
	// ********************************************************
	// ********************************************************
	
	/**
	 * 
	 */
	private static final int STOPWATCH_CLOCK   = 25;
	
	/**
	 * 
	 */
	private static final int COUNTDOWN_CLOCK = 1000;
	
	/**
	 * 
	 */
	private static final long hour = 3600000;
	
	/**
	 * 
	 */
	private static final long min = 60000;
	
	/**
	 * 
	 */
	private static final long sec = 1000;

	
	
	// ********************************************************
	// ********************************************************
	// ***									             	***
	// ***	Instance Fields       				 			***
	// ***											        ***	
	// ********************************************************
	// ********************************************************
	
	/**
	 * 
	 */
	private Instant start;
	
	/**
	 * 
	 */
	private Instant stop;
	
	/**
	 * 
	 */
	private final Timer stopwatch;
	
	/**
	 * 
	 */
	private final Timer countdown;

	/**
	 * 
	 */
	private long currentTime;
	
	/**
	 * 
	 */
	private int countdownTime;
	
	
	
	// ********************************************************
	// ********************************************************
	// ***									             	***
	// ***	Constructor		       				 			***
	// ***											        ***	
	// ********************************************************
	// ********************************************************
	
	/**
	 * 
	 */
	public Stopwatch()
	{
		stopwatch = new Timer(STOPWATCH_CLOCK, e -> countup());
		
		countdown = new Timer(COUNTDOWN_CLOCK, e -> countdown());
		countdown.setInitialDelay(0);
		
		currentTime = 0;
		countdownTime = 0;
		
		this.get().setHorizontalAlignment(JLabel.CENTER);
		this.get().setVerticalAlignment(JLabel.CENTER);
		this.get().setText("0.0");
	}
	
	
	
	// ********************************************************
	// ********************************************************
	// ***									             	***
	// ***	Methods			       				 			***
	// ***											        ***	
	// ********************************************************
	// ********************************************************
	
	public Stopwatch initFontSize(final int size)
	{
		final Font font = get().getFont();
		
		this.get().setFont(new Font(font.getName(), font.getStyle(), size));
		
		return this;
	}
	
	public void start()
	{
		start = Instant.now();
		stopwatch.start();
	}
	
	public void stop()
	{
		stop = Instant.now();
		stopwatch.stop();
		
		currentTime = Duration.between(start, stop).toMillis();
		this.get().setText(getElapsedTime(start, stop));
	}
	
	private void countup()
	{
		this.get().setText(getElapsedTime(start, Instant.now()));
	}
	
	private void countdown()
	{
		if(countdownTime == 0)
		{
			countdown.stop();
		}
		
		this.get().setText(countdownTime + "");
		
		--countdownTime;
	}

	public void startCountdown(final int start)
	{
		countdownTime = start;
		countdown.start();
	}
	
	public void stopCountdown()
	{
		countdown.stop();
	}
	
	public String getElapsedTime(final Instant start, final Instant stop)
	{
		final Duration time = Duration.between(start, stop);
	
		return Time.formatTime(time.toMillis());
	}
	
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
	
	public long getTime()
	{
		return currentTime;
	}



	@Override
	public JLabel create() 
	{
		return new JLabel();
	}



	@Override
	public Stopwatch self() 
	{
		return this;
	}
}

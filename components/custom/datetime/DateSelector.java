package components.custom.datetime;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.text.JTextComponent;

import components.ComboBox;
import components.JInitializer;
import components.Label;
import components.buttons.Button;
import components.panels.BorderPanel;
import components.panels.FlowPanel;
import components.panels.GridPanel;
import guiutil.Function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class DateSelector extends JInitializer<JPanel, DateSelector>
{
	
	
	// ********************************************************
	// ********************************************************
	// ***									             	***
	// ***	Static Fields         				 			***
	// ***											        ***	
	// ********************************************************
	// ********************************************************
	
	private static final int[] daysInAMonth = new int[] 
			{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	private static final String[] monthAbbreviations = new String[]
			{ "Jan.", "Feb.", "Mar.", "Apr.", "May.", "Jun.", "Jul.", "Aug.", "Sep.", "Oct.", "Nov.", "Dec." };
	
	private static final String[] months = new String[]
			{ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	
	private static final String[] days = new String[]
			{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public static final int MIN_YEAR = 1900;
	
	public static final int MAX_YEAR = 2100;
	
	
	private static Map<Integer, Integer> yearToDays = new HashMap<>();
	
	private static Map<Integer, Integer> monthToDays = new HashMap<>(); 
	
	static
	{
		yearToDays.put(MIN_YEAR, 0);
		
		for(Integer i = MIN_YEAR + 1; i <= MAX_YEAR; ++i)
		{
			final int tot = yearToDays.get(i - 1);
			
			if(isLeapYear(i - 1))
			{
				yearToDays.put(i, tot + 366);
			}
			else
			{
				yearToDays.put(i, tot + 365);
			}
		}
		
		monthToDays.put(1, 0);
		
		for(Integer i = 2; i < 13; ++i)
		{
			final int tot = monthToDays.get(i - 1);
			
			monthToDays.put(i, tot + daysInAMonth[i - 2]);
		}
	}
	

	public static final int YYYY_MM_DD  = 0;
	public static final int MM_DD_YYYY  = 1;
	public static final int MM_DD_YY    = 2;
	public static final int ABR_DD_YYYY = 3;
	public static final int FULL_DD_YYYY = 4;
	
	
	// ********************************************************
	// ********************************************************
	// ***									             	***
	// ***	Data Fields          				 			***
	// ***											        ***	
	// ********************************************************
	// ********************************************************

	
	private int selectedYear  = LocalDate.now().getYear();
	private int selectedMonth = LocalDate.now().getMonthValue();
	private int selectedDay   = LocalDate.now().getDayOfMonth();

	private int format = ABR_DD_YYYY;
	private char seperator = ' ';
	
	private final List<Function> functionsList = new ArrayList<>();

	
	
	// ********************************************************
	// ********************************************************
	// ***									             	***
	// ***	Getters			           						***
	// ***											        ***	
	// ********************************************************
	// ********************************************************


	public int    getYear() { return selectedYear; }
	public int   getMonth() { return selectedMonth; }
	public int     getDay() { return selectedDay; }
	public String getDate() { return getDate(format, seperator); }
	
	public String getDate(final int format, final char seperator)
	{
		switch(format)
		{
		case YYYY_MM_DD: return String.format("%d%s%d%s%d", getYear(), seperator, getMonth(), seperator, getDay());
			
		case MM_DD_YYYY: return String.format("%d%s%d%s%d", getMonth(), seperator, getDay(), seperator, getYear());
			
		case MM_DD_YY: return String.format("%d%s%d%s%d", getMonth(), seperator, getDay(), seperator, getYear() % 100);
		
		case ABR_DD_YYYY: return monthAbbreviations[getMonth() - 1] + " " + getDay() + ", " + getYear();
		
		case FULL_DD_YYYY: return months[getMonth() - 1] + " " + getDay() + ", " + getYear();
		}
		
		return "No Format Selected";
	}
	
	public long getUnixTime()
	{
		long yearDays = yearToDays.get(selectedYear) - yearToDays.get(1970);
		
		
		long days = yearDays + monthToDays.get(selectedMonth) + selectedDay - 1;
		
		if(isLeapYear(selectedYear) && selectedMonth > 2)
		{
			days += 1;
		}
		
		return days * 24 * 60 * 60;
	}
	
	
	// ********************************************************
	// ********************************************************
	// ***									             	***
	// ***	Components		           						***
	// ***											        ***	
	// ********************************************************
	// ********************************************************

	private final GridPanel daysPanel = new GridPanel(0, 7, 0, 0);

	private final YearBox yearBox;

	private final Label monthLabel = new Label(months[selectedMonth - 1]).initHorizontalAlignment(JLabel.CENTER);

	private final Button leftButton  = new Button("<").initActionListener(e -> leftButtonClicked());
	private final Button rightButton = new Button(">").initActionListener(e -> rightButtonClicked());
	

	private final DateButton[] dates = new DateButton[] 
	{ 
		new DateButton( "1",  1), new DateButton( "2",  2), new DateButton( "3",  3), new DateButton( "4",  4), new DateButton( "5",  5),
		new DateButton( "6",  6), new DateButton( "7",  7), new DateButton( "8",  8), new DateButton( "9",  9), new DateButton("10", 10), 
		new DateButton("11", 11), new DateButton("12", 12), new DateButton("13", 13), new DateButton("14", 14), new DateButton("15", 15), 
		new DateButton("16", 16), new DateButton("17", 17), new DateButton("18", 18), new DateButton("19", 19), new DateButton("20", 20),
		new DateButton("21", 21), new DateButton("22", 22), new DateButton("23", 23), new DateButton("24", 24), new DateButton("25", 25),
		new DateButton("26", 26), new DateButton("27", 27), new DateButton("28", 28), new DateButton("29", 29), new DateButton("30", 30),
		new DateButton("31", 31)
	};


	private void draw()
	{
		self().initLayout(new BorderLayout())
		.get()
		.add
		(
			new BorderPanel().emptyBorder(25)
			.initPreferredSize(240, 298)
			.north
			(
				new GridPanel(3, 1, 5, 5)
				.grid
				(
					new FlowPanel(FlowLayout.CENTER, 2, 2)
					.flow(this.yearBox),
					
					new BorderPanel()
					.west(this.leftButton)
					.center(this.monthLabel)
					.east(this.rightButton),
					
					new GridPanel(1, 7, 0, 0)
					.grid
					(
						new Label("S").initHorizontalAlignment(JLabel.CENTER), 
						new Label("M").initHorizontalAlignment(JLabel.CENTER), 
						new Label("T").initHorizontalAlignment(JLabel.CENTER),
						new Label("W").initHorizontalAlignment(JLabel.CENTER),
						new Label("T").initHorizontalAlignment(JLabel.CENTER),
						new Label("F").initHorizontalAlignment(JLabel.CENTER),
						new Label("S").initHorizontalAlignment(JLabel.CENTER)
					)				
				)
			)
			.center(this.daysPanel)
			.get()
			, BorderLayout.CENTER
		);
	}

	// ********************************************************
	// ********************************************************
	// ***									             	***
	// ***	Constructor		       				 			***
	// ***											        ***	
	// ********************************************************
	// ********************************************************

	public DateSelector(final Function... functions)
	{	
		this(MIN_YEAR, MAX_YEAR, functions);
	}
	
	public DateSelector(final int minYear, final int maxYear, final Function... functions)
	{
		for(final Function function: functions){functionsList.add(function);}
		yearBox = new YearBox(minYear, maxYear);
		dates[selectedDay - 1].setSelected(true);
		setCalendar();
		this.draw();
		refresh();
	}
	

	// ********************************************************
	// ********************************************************
	// ***									             	***
	// ***	Methods			       				 			***
	// ***											        ***	
	// ********************************************************
	// ********************************************************
	
	public void addFunction(final Function function)
	{
		this.functionsList.add(function);
	}
	
 	private void setCalendar()
	{
 		final JPanel panel = this.daysPanel.get();
 		
 		panel.removeAll();
 		
		final int firstDay = getFirstDayOfWeek();
		
		for(int i = 0; i < firstDay; ++i)
		{
			panel.add(new JLabel());
		}
		
		final int totalDays = (selectedYear % 4 == 0 && selectedMonth == 2) ? daysInAMonth[selectedMonth - 1] + 1 : daysInAMonth[selectedMonth - 1]; 
		
		for(int i = 0; i < totalDays; ++i)
		{
			panel.add(dates[i]);
		}
		
		refresh();
	}
 	
	
 	private void refresh()
 	{
 		get().revalidate();
 		get().repaint();
 	}

	private int getFirstDayOfWeek()
	{
		int totalDays = yearToDays.get(selectedYear) + monthToDays.get(selectedMonth);
		
		if(isLeapYear(selectedYear) && selectedMonth > 2) ++totalDays;
		
		return (totalDays + 1) % 7;
		
//		final int totalYears = selectedYear - 1900;		
//		
//		final int leapYears = (selectedYear % 4 == 0) ? totalYears / 4 - 1 : totalYears / 4;
//		
//		final int normalYears = totalYears - leapYears;
//		
//		int totalDays = leapYears * 366 + normalYears * 365 + 1;
//
//		for(int i = 1; i < selectedMonth; ++i)
//		{
//			totalDays += daysInAMonth[i - 1];
//		}
//
//		if(isLeapYear(selectedYear) && selectedMonth > 2) ++totalDays;
//		
//		final int day = totalDays % 7;
//		
//		return day;
	}


	private static boolean isLeapYear(final int year)
	{
		if(year % 100 == 0)
		{
			if(year % 400 == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if (year % 4 == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void executeFunctions()
	{
		for(final Function function: functionsList)
		{
			function.run();
		}
	}
	
	private void leftButtonClicked()
	{
		if(yearBox.get().getSelectedIndex() + 1 == yearBox.get().getItemCount() && selectedMonth == 1) // 1900
		{
			// Do Nothing
		}
		else
		{
			--selectedMonth;
			
			if(selectedMonth == 0) 
			{
				selectedMonth = 12;
				--selectedYear;
				yearBox.get().setSelectedIndex(yearBox.get().getSelectedIndex() + 1);
			}
			
			monthLabel.get().setText(months[selectedMonth - 1]);

			if(selectedDay > daysInAMonth[selectedMonth - 1])
			{
				dates[selectedDay - 1].setSelected(false);
				selectedDay = 1;
				dates[selectedDay - 1].setSelected(true);
			}
			
			setCalendar();
			executeFunctions();
			
			get().revalidate();
			get().repaint();
		}
	}
	
	private void rightButtonClicked()
	{
		if(yearBox.get().getSelectedIndex() == 0 && selectedMonth == 12) // 2024
		{
			// Do Nothing
		}
		else
		{
			++selectedMonth;
			
			if(selectedMonth == 13) 
			{
				selectedMonth = 1;
				++selectedYear;
				yearBox.get().setSelectedIndex(yearBox.get().getSelectedIndex() - 1);
			}
			monthLabel.get().setText(months[selectedMonth - 1]);
			
			if(selectedDay > daysInAMonth[selectedMonth - 1])
			{
				dates[selectedDay - 1].setSelected(false);
				selectedDay = 1;
				dates[selectedDay - 1].setSelected(true);
			}
			
			setCalendar();
			executeFunctions();
			
			get().revalidate();
			get().repaint();
		}
	}
	
	// ********************************************************
	// ********************************************************
	// ***									             	***
	// ***	Internal Classes       				 			***
	// ***											        ***	
	// ********************************************************
	// ********************************************************
	

	private class DateButton extends JToggleButton
	{
		private final int id;
		
		public DateButton(final String text, final int id)
		{
			this(text, id, true);
		}
		
		public DateButton(final String text, final int id, final boolean enabled)
		{
			super(text);
			this.id = id;
			final int size = 7;
			this.setBorder(BorderFactory.createEmptyBorder(size, size, size, size));
			this.setEnabled(enabled);
			
			this.addActionListener(e ->
			{
				dates[selectedDay - 1].setSelected(false);
				
				selectedDay = this.id;

				dates[selectedDay - 1].setSelected(true);
				
				executeFunctions();
				
				refresh();
			});
		}
	}
	

	private class YearBox extends JInitializer<JComboBox<Integer>, YearBox>
	{
		public YearBox(final int minYear, final int maxYear)
		{
			for(Integer i = maxYear; i >= minYear; --i)
			{
				get().addItem(i);
			}
			
			get().setMaximumRowCount(10);
			
			get().addActionListener(e ->
			{
				final int year = (Integer) get().getSelectedItem();
				selectedYear = year;
				setCalendar();
				executeFunctions();
				
				get().revalidate();
				get().repaint();
				 
				refresh();
			});
		}

		@Override
		public JComboBox<Integer> create() 
		{
			return new JComboBox<>();
		}

		@Override
		public YearBox self() 
		{
			return this;
		}
	}


	@Override
	public JPanel create() 
	{
		return new JPanel();
	}
	@Override
	public DateSelector self() 
	{
		return this;
	}

}

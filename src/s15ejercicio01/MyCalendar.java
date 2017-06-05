package s15ejercicio01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * MyCalendar prints a Calender system
 * @author Kun Su 
 *
 */
public class MyCalendar{

	int STime;
	int ETime;

	private String[] arrayOfMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};;
	private String[] arrayOfDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	String Title;
	int month;
	int day;
	int year;
	int ID;
	int DayOfWeek;
	
	private TreeMap<Integer, MyCalendar> HashM;
	private ArrayList<ChangeListener> listener;
	private GregorianCalendar calendar;
	
	/**
	 * MyCalendar class construct 
	 */
	public MyCalendar()
	{
		
		Title = "";
		month = 0;
		day = 0;;
		year = 0;;
		STime = 0;
		ETime = 0;
		ID = 1;

		HashM = new TreeMap<Integer, MyCalendar>();
		listener = new ArrayList<ChangeListener>();
		calendar = new GregorianCalendar();
	}
	
	/**
	 * add ChangeListener into the ArrayList to store it
	 * @param observer input the observer for adding changeListener
	 */
	public void addChangeListener(ChangeListener observer)
	{
		listener.add(observer);
	}
	
	/**
	 * notice all the views to update
	 * @param c input GregorianCalendar to get the data
	 */
	public void update(GregorianCalendar c)
	{
		calendar = c;
		for(ChangeListener observer : listener)
		{
			observer.stateChanged(new ChangeEvent(this));
		}
	}
	
	/**
	 * get the title for the event
	 * @return the title for the event
	 */
	public String getTitle()
	{
		return Title;
	}
	
	/**
	 * get the calendar from model class
	 * @return the calendar
	 */
	public GregorianCalendar getcalendar()
	{
		return calendar;
	}
	
	/**
	 * get the TreeMap from model class
	 * @return the TreeMap
	 */
	public TreeMap<Integer, MyCalendar> getdata()
	{
		return HashM;
	}
	
	/**
	 * printCalendar prints the first Calendar
	 * @param c input a Calendar
	 * @return return a Calendar with String
	 */
	public String printCalendar(Calendar c)
	{   
	    
		int Daycount = 0;
		int i = 1;
		String output = "";
		
		output += String.format("%16s %6s", arrayOfMonths[c.get(Calendar.MONTH)], c.get(Calendar.YEAR) ); //get current month and year
		output += System.lineSeparator();
		output += String.format("%5s%5s%5s%5s%5s%5s%5s ", "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa");
		output += System.lineSeparator();
		
		GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1); // temp set the current day of the month
		int Daycount2 = temp.get(Calendar.DAY_OF_WEEK)-1 ; //get the current day of the week

		while (i <= c.getActualMaximum(Calendar.DAY_OF_MONTH))
		{
			
			if(!arrayOfDays[Daycount].equals(arrayOfDays[Daycount2] ))
			{
				output += String.format("%5s", "");
				Daycount++;
			}
			else
			{
				if(i == c.get(Calendar.DAY_OF_MONTH))
				{
					output += String.format("%3s%s%s", "[", i, "]");
				}
				else
				{
					output += String.format("%5s", i);
				}
				i++;
				Daycount++;
				Daycount2++;
			}
			
			if(Daycount == 7)
			{
				output += System.lineSeparator();
				Daycount = 0;
				Daycount2 = 0;
			}

		}

		return output;
	}
	
	/**
	 * Dayview prints the view of current day
	 * @param c input a Calendar
	 * @return a string with a Calendar of current day
	 */
	public String Dayview(Calendar c)
	{
		int index = 0;
		String output = "";
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH)+1;
		day = c.get(Calendar.DAY_OF_MONTH);
		
		for(int i=1; i<ID; i++)
		{
			if(HashM.get(i).year == year && HashM.get(i).month == month && HashM.get(i).day == day && !HashM.get(i).Title.equals("")) 
			{
				index = i;
				output += "" + arrayOfDays[ c.get(Calendar.DAY_OF_WEEK)-1 ] + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.DAY_OF_MONTH) + ", " + c.get(Calendar.YEAR);
				output += System.lineSeparator();
				output += String.format("%s %02d:%02d", HashM.get(index).Title, HashM.get(index).STime/100, HashM.get(index).STime%100);
				if(HashM.get(index).ETime != 0)
				{
					output += String.format(" - %02d:%02d", HashM.get(1).ETime/100, HashM.get(1).ETime%100);
				}
				output += System.lineSeparator();
				return output;
			}
		}
		output = "There is not event on this day. ";
		return output;
	}
	
	/**
	 * Monthview prints the view of the current month
	 * @param c input a Calendar
	 * @return the view of the current month
	 */
	public String Monthview(Calendar c)
	{
		int Daycount = 0;
		int i = 1;
		int PrintOnceOnly =0;
		String output = "";
		
		output += String.format("%16s %6s", arrayOfMonths[c.get(Calendar.MONTH)], c.get(Calendar.YEAR) ); //get current month and year
		output += System.lineSeparator();
		output += String.format("%5s%5s%5s%5s%5s%5s%5s ", "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa");
		output += System.lineSeparator();
		
		GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1); // temp set the current day of the month
		int Daycount2 = temp.get(Calendar.DAY_OF_WEEK)-1 ; //get the current day of the week

		while (i <= c.getActualMaximum(Calendar.DAY_OF_MONTH))
		{
			if(!arrayOfDays[Daycount].equals(arrayOfDays[Daycount2] ))
			{
				output += String.format("%5s", "");
				Daycount++;
			}
			else
			{
				for(int j=1; j<ID; j++)
				{
					if(i == HashM.get(j).day && c.get(Calendar.MONTH)+1 == HashM.get(j).month && c.get(Calendar.YEAR) == HashM.get(j).year && !HashM.get(j).Title.equals(""))
					{
						output += String.format("%3s%s%s", "[", i, "]");
						PrintOnceOnly++;
					}
					else
					{
						if(PrintOnceOnly == 0 && j== ID-1)
						{
							output += String.format("%5s", i);
							PrintOnceOnly++;
						}
					}
				}
				PrintOnceOnly = 0;
				i++;
				Daycount++;
				Daycount2++;
			}
			
			if(Daycount == 7)
			{
				output += System.lineSeparator();
				Daycount = 0;
				Daycount2 = 0;
			}
		}
		return output;
	}
	
	/**
	 * inputdata deal with all the input Calendar need and then put it into HashMap
	 * @param Title input the Title of the event
	 * @param month input the month of the event
	 * @param day input the day of the event
	 * @param year input the year of the event
	 * @param Stime input the Starting time of the event
	 * @param Etime input the Ending time of the event
	 */
	public void inputdata(String Title, int month, int day, int year, int Stime, int Etime)
	{
		MyCalendar x = new MyCalendar();
		
		x.Title = Title;
		x.month = month;
		x.day = day;
		x.year = year;
		x.STime = Stime;
		x.ETime = Etime;
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month-1);
		c.set(Calendar.DAY_OF_MONTH, day);
		
		x.DayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
		HashM.put(ID, x);
		ID++;
		System.out.println("Event has been created! ");
		
	}
	
	/**
	 * GoTo go to any data user wants and prints out the event
	 * @param c input a Calendar
	 * @return return print a day view of the giving day
	 */
	public String GoTo(Calendar c) 
	{
		int index = 0;
		String output = "";
		
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH)+1;
		day = c.get(Calendar.DAY_OF_MONTH);
		
		for(int i=1; i<ID; i++)
		{
			if(HashM.get(i).year == year && HashM.get(i).month == month && HashM.get(i).day == day && !HashM.get(i).Title.equals(""))
			{
				index = i;
				output += "" + arrayOfDays[ c.get(Calendar.DAY_OF_WEEK)-1 ] + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.DAY_OF_MONTH) + ", " + c.get(Calendar.YEAR);
				output += System.lineSeparator();
				output += String.format("%s %02d:%02d", HashM.get(index).Title, HashM.get(index).STime/100, HashM.get(index).STime%100);
				if(HashM.get(index).ETime != 0)
				{
					output += String.format(" - %02d:%02d", HashM.get(1).ETime/100, HashM.get(1).ETime%100);
				}
				output += System.lineSeparator();
				return output;
			}
		}
		
		output = "There is not event on this day. ";
		return output;
	}

	/**
	 * EventList prints the list of the event in order
	 * @param c input a Calendar
	 * @return return a string with a list of event
	 */
	public String EventList(Calendar c)
	{
		
		String output = "";
		for(int i=1; i<ID; i++)
		{
			if(i==1 && !HashM.get(i).Title.equals(""))
			{
				output += String.format("%d", HashM.get(i).year );
				output += System.lineSeparator();
			}
			else if(i>1 &&HashM.get(i).year != HashM.get(i-1).year && !HashM.get(i).Title.equals(""))
			{
				output += String.format("%d", HashM.get(i).year );
				output += System.lineSeparator();
			}
			
			if(!HashM.get(i).Title.equals(""))
			{
				output += String.format("  %s, %s %d", arrayOfDays[ HashM.get(i).DayOfWeek], arrayOfMonths[HashM.get(i).month-1 ], HashM.get(i).day );
				output += String.format(" %02d:%02d", HashM.get(i).STime/100, HashM.get(i).STime%100);
				if(HashM.get(i).ETime != 0)
				{
					output += String.format(" - %02d:%02d", HashM.get(i).ETime/100, HashM.get(i).ETime%100);
				}
				output += String.format(" %s", HashM.get(i).Title );
				output += System.lineSeparator();
			}
		}
		return output;
	}
	
	/**
	 * Delete the event from the Calendar
	 * @param c input the Calendar
	 */
	public String Delete(Calendar c)
	{
		int index = 0;
		String output;
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH)+1;
		day = c.get(Calendar.DAY_OF_MONTH);
		
		for(int i=1; i<ID; i++)
		{
			if(HashM.get(i).year == year && HashM.get(i).month == month && HashM.get(i).day == day  )
			{
				index = i;
				HashM.get(index).Title = "";
				return output = "Event has been deleted \n";
			}
		}
		return output = "No event found \n";
	}
	
	/**
	 * DeleteAll delete all the event from the Calendar
	 */
	public String DeleteAll()
	{
		String output ="";
		for(int i=1; i<ID; i++)
		{
			HashM.get(i).Title = "";
			output = "All Event have been deleted \n";
		}
		return output;
	}
	
	/**
	 * Load load the event list to the Calendar
	 * @param c input Calendar
	 * @throws FileNotFoundException throws file not found
	 */
	public void Load(Calendar c) throws FileNotFoundException
	{
		String output = "";
		File eventinput = new File("events.txt");
		if(eventinput.exists() == false)
		{
			System.out.println("This is the first run, No event from events.txt");
		}
		else
		{
			Scanner Ein = new Scanner(eventinput);
			String[] s;
			String input;
			
			String LSdayOfWeek;
			String LSmonth;
			int Lday =0;
			int LSTime;
			int LETime;
			String LTitle;
			int Lyear = 0;
			int Lmonth = 0;
			
			try
			{
				while(Ein.hasNextLine())
				{
					input = Ein.nextLine();
					s = input.trim().split(" ");

					if(s.length <= 1 )
					{
						Lyear = Integer.parseInt(s[0]);
					}
					else if(s.length > 1 )
					{
						LSdayOfWeek = s[0].substring(0, s[0].length()-1);
						LSmonth = s[1];
						Lday = Integer.parseInt(s[2]);
						LSTime = Integer.parseInt( s[3].substring(0, 2) ) * 100 + Integer.parseInt ( s[3].substring(3, 5) );
						LETime = Integer.parseInt( s[5].substring(0, 2) ) * 100 + Integer.parseInt ( s[5].substring(3, 5) );
						LTitle = "";
						for(int i=6; i<s.length; i++)
						{
							LTitle += s[i] + " ";
						}
						
						for(int i=0; i<12; i++)
						{
							if(arrayOfMonths[i].equals(LSmonth) )
							{
								Lmonth = i + 1;
							}
						}
						inputdata(LTitle, Lmonth, Lday, Lyear, LSTime, LETime);
					}
				}
				
			}
			catch(NoSuchElementException e)
			{
				System.out.println("#2");
				System.out.println("FIle does not found ") ;
			}
		}
	}
	
}

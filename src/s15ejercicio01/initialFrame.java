package s15ejercicio01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * this is the Main View of this program
 * @author kun
 *
 */
public class initialFrame extends JFrame{

	private MyCalendar calendar;
	private TreeMap<Integer, MyCalendar> HashM;
	private GregorianCalendar cal;	
	private int space;
	
	private String[] arrayOfMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};;
	private String[] arrayOfDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	/**
	 * initialFrame class construct 
	 * @param c input the Model class (Mycalendar)
	 */
	public initialFrame(MyCalendar c)
	{
		space = 70;
		
		calendar = c;
		HashM = calendar.getdata();
		cal = calendar.getcalendar();
		initial();
	}
	
	/**
	 * initialize the main Frame
	 */
	public void initial()
	{

		
		setLayout(null);
		setSize(900, 450);
		
		//Load the events.txt
		try 
		{
			
			calendar.Load(cal);
			HashM = calendar.getdata();
			cal = calendar.getcalendar();
			calendar.update(cal);
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		
		//BackWard Button
		JButton BackWard = new JButton("<");
		BackWard.setBounds(330 + space, 10, 50, 25);
		add(BackWard);
		BackWard.addActionListener(new //controller
		         ActionListener()
		         {
		            public void actionPerformed(ActionEvent event)
		            {
		            	cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-1);
		            	calendar.update(cal);
		            }
		         });
		
		//ForWard button
		JButton ForWard = new JButton(">");
		ForWard.setBounds(380 + space, 10, 50, 25);
		add(ForWard);
		ForWard.addActionListener(new //controller
		         ActionListener()
		         {
		            public void actionPerformed(ActionEvent event)
		            {
		            	cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
		            	calendar.update(cal);
		            }
		         });
		
		//Quit button
		JButton Quit = new JButton("Quit");
		Quit.setBounds(720 + space, 10, 60, 25);
		add(Quit);
		Quit.addActionListener(new //controller
		         ActionListener()
		         {
		            public void actionPerformed(ActionEvent event)
		            {
		            	
		            	//generalize the events.txt
						try 
						{
							HashM = calendar.getdata();
			            	cal = calendar.getcalendar();
							PrintWriter out = new PrintWriter("events.txt");
							String finaloutput = calendar.EventList(cal);
			        		out.print(finaloutput);
			        		out.close();
						} 
						catch (FileNotFoundException e) 
						{
							e.printStackTrace();
						}
						System.exit(0);
		            }
		         });
		
		//Create Buuton
		JButton Create = new JButton("Create");
		Create.setBounds(0, 40, 80, 25);
		Create.addActionListener(new ActionListener()
		         {
		            public void actionPerformed(ActionEvent event)
		            {
		            	//Create a new Frame when clicked the create button
		            	CreateButton createButton = new CreateButton(calendar);
		            }
		         });
		add(Create);
		
		//TestArea for Month & Year 
		final JTextArea Test1 = new JTextArea(arrayOfMonths[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.YEAR));
		Test1.setBounds(0, 70, 325 + space, 25);
		add(Test1);
		ChangeListener listenerA;
            listenerA = new ChangeListener()
            {
                public void stateChanged(ChangeEvent e)
                {
                    cal = calendar.getcalendar();
                    Test1.setText(arrayOfMonths[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.YEAR));
                    repaint();
                }
            };
		calendar.addChangeListener(listenerA);
		
		//TestArea for Day of week & Day & Month
		final JTextArea Test2 = new JTextArea(arrayOfDays[ cal.get(Calendar.DAY_OF_WEEK)-1 ] + " " + arrayOfMonths[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.DAY_OF_MONTH) );
		Test2.setBounds(330 + space, 40, 450, 25);
		add(Test2);
		ChangeListener listenerB = new //controller
				ChangeListener()
				{
						public void stateChanged(ChangeEvent e)
						{
							cal = calendar.getcalendar();
							Test2.setText(arrayOfDays[ cal.get(Calendar.DAY_OF_WEEK)-1 ] + " " + arrayOfMonths[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.DAY_OF_MONTH));
							repaint();
						}
				};
		calendar.addChangeListener(listenerB);
		
		//DayView
		DayViewPanel DayView = new DayViewPanel(calendar);
		JScrollPane ScrollBar = new JScrollPane(DayView);
		ScrollBar.setBounds(330 + space, 70, 450, 330);
		add(ScrollBar);
		
		//MonthView
		MonthViewJPanel MonthView = new MonthViewJPanel(calendar);
		MonthView.setBounds(0, 100, 330 + space, 300);
		add(MonthView);
		calendar.addChangeListener(MonthView);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}

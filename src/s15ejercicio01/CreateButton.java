package s15ejercicio01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The CREATE button pops up input box(es) through which the user can enter an event
 * @author kun
 *
 */
public class CreateButton extends JFrame{

	private MyCalendar calendar;
	private GregorianCalendar cal;
	private TreeMap<Integer, MyCalendar> HashM;
	
	String Title;
	int day;
	int month;
	int year;
	int STime;
	int ETime;
	int Time;
	
	/**
	 * CreateButton class construct 
	 * @param c input the Model class (Mycalendar)
	 */
	public CreateButton(MyCalendar c)
	{
		
		calendar = c;
		cal = calendar.getcalendar();
		HashM = calendar.getdata();
		initialize();
	}
	
	/**
	 * initialize the main Frame
	 */
	public void initialize()
	{
		setLayout(null);
		setSize(600, 200);

		//JTextArea for TopTitle
		JTextArea TopTitle = new JTextArea("Please click Enter after each time you enter your input");
		TopTitle.setBounds(30, 10, 300, 15);
		add(TopTitle);
		
		//JTextArea for NoticeTest
		final JTextArea NoticeTest = new JTextArea("System feedback: ");
		NoticeTest.setBounds(30, 130, 400, 15);
		add(NoticeTest);
		 
		//JTextField for inputEvent
		JTextField inputEvent = new JTextField("Please Enter Your Event Name ");
		inputEvent.setBounds(30, 30, 500, 35);
		add(inputEvent);
		inputEvent.addActionListener(new //controller
				ActionListener()
			      {
			         public void actionPerformed(ActionEvent e)
			         {
			            // Figure out which field generated the event
			             JTextField c = (JTextField) e.getSource();
			             Title = c.getText().trim();
			             if(Title.isEmpty())
			             {
			            	 remove(NoticeTest);
			            	 JTextArea NoticeTest = new JTextArea("System feedback: Please enter your correct event name");
			         		 NoticeTest.setBounds(30, 130, 400, 15);
			         		 add(NoticeTest);
			         		 repaint();
			             }
			          }   
			      });
		
		//JTextField for inputDate
		JTextField inputDate = new JTextField();
		day = cal.get(Calendar.DAY_OF_MONTH);
        month = Calendar.DAY_OF_MONTH;
		year = cal.get(Calendar.YEAR);
		String today = String.format("%02d/%02d/%04d", day, month, year);
		inputDate.setText(today);
		inputDate.setBounds(30, 100, 100, 25);
		add(inputDate);
		inputDate.addActionListener(new //controller
				ActionListener()
			      {
			         public void actionPerformed(ActionEvent e)
			         {

			             // Figure out which field generated the event
			             JTextField c = (JTextField) e.getSource();
			             String inputtext = c.getText();
			             
		            	 day = Integer.parseInt( inputtext.substring(0, 2) );
			             month = Integer.parseInt( inputtext.substring(3, 5) );
						 year = Integer.parseInt( inputtext.substring(6, 10) );
			            }
			         
			      });
		
		//JTextField for Stime
		JTextField Stime = new JTextField("0000 ");
		Stime.setBounds(140, 100, 100, 25);
		add(Stime);
		Stime.addActionListener(new //controller
				ActionListener()
			      {
			         public void actionPerformed(ActionEvent e)
			         {
			        	 
			             // Figure out which field generated the event
			             JTextField c = (JTextField) e.getSource();
			             String s = c.getText().trim();
			             STime = Integer.parseInt( s.substring(0, 2) ) * 100 + Integer.parseInt ( s.substring(2, 4) );
			             //System.out.println("STime: " + STime);
			         }
			         
			      });
		
		//JTextField for Etime
		JTextField Etime = new JTextField("0400 ");
		Etime.setBounds(250, 100, 100, 25);
		add(Etime);
		Etime.addActionListener(new //controller
				ActionListener()
			      {
			         public void actionPerformed(ActionEvent e)
			         {
			             // Figure out which field generated the event
			             JTextField c = (JTextField) e.getSource();
			             String s = c.getText().trim();
			             ETime = Integer.parseInt( s.substring(0, 2) ) * 100 + Integer.parseInt ( s.substring(2, 4) );
			         }
			         
			      });
		
		//JButton for Save
		JButton Save = new JButton("Save");
		Save.setBounds(360, 100, 80, 25);
		add(Save);
		Save.addActionListener(new //controller
				ActionListener()
			      {
			         public void actionPerformed(ActionEvent e)
			         {
			        	 
			        	 if(STime>ETime)
			        	 {
			        		 NoticeTest.setText("System feedback: PLZ input the correct Starting and ending time ");
			        		 return;
			        	 }
			             // Figure out which field generated the event
			        	 if(checkTImeConflict(Title, month, day, year, STime, ETime))
			        	 {
			        		 calendar.inputdata(Title, month, day, year, STime, ETime);
				        	 cal = calendar.getcalendar();
				        	 calendar.update(cal);
				        	 
				        	 NoticeTest.setText("System feedback: Event has been created! ");
			         		 repaint();
			        	 }
			        	 else
			        	 {
			        		 NoticeTest.setText("System feedback: PLZ entry a new time without time Conflict");
			         		 repaint();
			        	 }
			        	 Title = null;
			        }
			         
			      });
		
		//JTextArea for TimeTitle
		JTextArea TimeTitle = new JTextArea("DD/MM/YYYY:             String Time:0000     Ending Time:2300   ");
		TimeTitle.setBounds(30, 80, 330, 15);
		add(TimeTitle);
			
		setVisible(true);
	}
	
	/**
	 * check the TIme Conflict for the event
	 * @param Title check the Title Conflict for the event
	 * @param month check the month Conflict for the event
	 * @param day check the day Conflict for the event
	 * @param year check the year Conflict for the event
	 * @param STime check the STime Conflict for the event
	 * @param ETime check the ETime Conflict for the event
	 * @return
	 */
	public boolean checkTImeConflict(String Title, int month, int day, int year, int STime, int ETime )
	{
		HashM = calendar.getdata();
		for(int j=1; j<=HashM.size(); j++)
		{
			System.out.println("time conflict");
			if(HashM.get(j).year == year && HashM.get(j).month == month && HashM.get(j).day == day && !HashM.get(j).getTitle().equals("") ) 
			{
				for(int Time=STime; Time<ETime; Time+=100)
				{
					if(HashM.get(j).STime <= Time && HashM.get(j).ETime >= Time)
					{
						System.out.println("Time Conflict PLZ entry a new time without time Conflict");
						return false;
					}
				}
			}
		}
		return true;
	}
}

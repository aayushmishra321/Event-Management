import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class festivalgathering
{

        JFrame festi=new JFrame();

        festivalgathering()
        {
            Font f=new Font("Arial",Font.BOLD,22);
            Font f1=new Font("Arial",Font.PLAIN,20);
            Font f2=new Font("Arial",Font.BOLD,26);

            JLabel event = new JLabel("FESTIVAL GATHERING EVENT");
            event.setBounds(320,80,400,40);
            event.setFont(f2);
            festi.add(event);

            JLabel oname = new JLabel("ORGANIZER NAME :");
            oname.setBounds(150,200,250,40);
            oname.setFont(f);
            festi.add(oname);

            JTextField nametext = new JTextField("");
            nametext.setBounds(150,230,500,50);
            nametext.setFont(f1);
            festi.add(nametext);

            JLabel ocontact = new JLabel("CONTACT NUMBER :");
            ocontact.setBounds(150,290,350,40);
            ocontact.setFont(f);
            festi.add(ocontact);

            JTextField contactText = new JTextField("");
            contactText.setBounds(150,320,500,50);
            contactText.setFont(f1);
            festi.add(contactText);


            JLabel odate = new JLabel("DATE :");
            odate.setBounds(150,380,200,60);
            odate.setFont(f);
            festi.add(odate);

            String[] Date = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                    "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

            String[] Month = { "January", "february", "March", "April", "May", "Jun", "July", "August",
                    "September", "October", "November", "December" };

            String[] Year = { "2023", "2024", "2025" };

            JComboBox edate = new JComboBox(Date);
            edate.setFont(f1);
            edate.setBounds(250,380,80,60);
            festi.add(edate);

            JComboBox month = new JComboBox(Month);
            month.setFont(f1);
            month.setBounds(330,380,150,60);
            festi.add(month);

            JComboBox year = new JComboBox(Year);
            year.setFont(f1);
            year.setBounds(480,380,100,60);
            festi.add(year);


            JLabel ovenue = new JLabel("VENUE :");
            ovenue.setBounds(150,440,200,60);
            ovenue.setFont(f);
            festi.add(ovenue);

            String[] Venues={"Powai Fest Grounds","Trupp & Fest Event Management","Tantraa Events,Parel east","Nesco Pvt Ltd"};


            JComboBox venueText = new JComboBox(Venues);
            venueText.setBounds(250,440,450,60);
            venueText.setFont(f1);
            festi.add(venueText);





            JLabel eguests = new JLabel("NUMBER OF GUESTS :");
            eguests.setBounds(150,500,350,40);
            eguests.setFont(f);
            festi.add(eguests);

            JTextField guestsText = new JTextField("");
            guestsText.setBounds(150,530,500,50);
            guestsText.setFont(f1);
            festi.add(guestsText);



            JButton submit = new JButton("SUBMIT");
            submit.setBounds(700,750,150,50);
            submit.setFont(f);
            festi.add(submit);

            JButton cancel = new JButton("CANCEL");
            cancel.setBounds(100,750,150,50);
            cancel.setFont(f);
            festi.add(cancel);

            Connection con1;
            PreparedStatement insert;

            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name= nametext.getText();
                    String contact= contactText.getText();
                    String date= edate.getSelectedItem()+"/"+month.getSelectedItem()+"/"+year.getSelectedItem();
                    String venue= (String)venueText.getSelectedItem();
                    String guest= guestsText.getText();
                    String time="00:00";
                    Connection con1;
                    PreparedStatement insert;
                    boolean t=true;
                    if(name.isEmpty()|| contact.isEmpty() || guest.isEmpty())
                    {
                        JOptionPane.showMessageDialog(festi,"Please enter all field");
                        return;
                    }


                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con1 = DriverManager.getConnection("jdbc:mysql://localhost/eventmanagement","root","7499373180");
                        insert =con1.prepareStatement("insert into festival_gathering(date,venue,name,guest_count,ph_no)values(?,?,?,?,?)");
                        insert.setString(1,date);
                        insert.setString(2,venue);
                        insert.setString(3,name);
                        insert.setString(4,guest);
                        insert.setString(5,contact);
                        insert.executeUpdate();

                        insert =con1.prepareStatement("insert into eventdetail(date,venue,time,name,phone_no,guestno,event)values(?,?,?,?,?,?,?)");
                        insert.setString(1,date);
                        insert.setString(2,venue);
                        insert.setString(3,time);
                        insert.setString(4,name);
                        insert.setString(5,contact);
                        insert.setString(6,guest);
                        insert.setString(7,"Festivalgathering");
                        insert.executeUpdate();



                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(festivalgathering.class.getName()).log(Level.SEVERE,null,ex);
                        t=false;
                    }

                    if(t==true)
                    {
                        JOptionPane.showMessageDialog(festi,"Data Store\nEvent created");
                        homepage hp4=new homepage();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(festi,"Venue is already reserved\nPlease select another date or venue");
                    }



                }
            });

            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int a=JOptionPane.showConfirmDialog(festi, "Do you really want to Cancel", "Select", JOptionPane.YES_NO_OPTION);
                    if(a==0)
                    {
                        homepage hp4=new homepage();
                    }
                }
            });


            festi.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int a = JOptionPane.showConfirmDialog(festi, "Do you really want to Close Application");
                    if (a == JOptionPane.YES_OPTION) {
                        festi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                    else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION)
                    {
                        festi.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
                }
            });




            festi.setTitle("Event Management");
            festi.setLayout(null);
            festi.setSize(900,900);
            festi.setVisible(true);


        }

}

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

public class productlaunch
{
    JFrame launch=new JFrame();

    productlaunch()
    {
        Font f=new Font("Arial",Font.BOLD,22);
        Font f1=new Font("Arial",Font.PLAIN,20);
        Font f2=new Font("Arial",Font.BOLD,26);

        JLabel event = new JLabel("PRODUCT LAUNCH EVENT");
        event.setBounds(320,80,400,40);
        event.setFont(f2);
        launch.add(event);

        JLabel name = new JLabel("ORGANIZER NAME :");
        name.setBounds(150,200,250,40);
        name.setFont(f);
        launch.add(name);

        JTextField nametext = new JTextField("");
        nametext.setBounds(150,230,500,40);
        nametext.setFont(f1);
        launch.add(nametext);

        JLabel contact = new JLabel("CONTACT NUMBER :");
        contact.setBounds(150,280,350,40);
        contact.setFont(f);
        launch.add(contact);

        JTextField contactText = new JTextField("");
        contactText.setBounds(150,310,500,40);
        contactText.setFont(f1);
        launch.add(contactText);


        JLabel date = new JLabel("DATE :");
        date.setBounds(150,360,200,60);
        date.setFont(f);
        launch.add(date);

        String[] Date = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

        String[] Month = { "January", "february", "March", "April", "May", "Jun", "July", "August",
                "September", "October", "November", "December" };

        String[] Year = { "2023", "2024", "2025" };

        JComboBox edate = new JComboBox(Date);
        edate.setFont(f1);
        edate.setBounds(250,360,80,60);
        launch.add(edate);

        JComboBox month = new JComboBox(Month);
        month.setFont(f1);
        month.setBounds(330,360,150,60);
        launch.add(month);

        JComboBox year = new JComboBox(Year);
        year.setFont(f1);
        year.setBounds(480,360,100,60);
        launch.add(year);

        JLabel cname = new JLabel("COMPANY NAME :");
        cname.setBounds(150,410,200,40);
        cname.setFont(f);
        launch.add(cname);

        JTextField cnameText = new JTextField("");
        cnameText.setBounds(150,440,500,40);
        cnameText.setFont(f1);
        launch.add(cnameText);

        JLabel time = new JLabel("TIME :");
        time.setBounds(150,490,200,60);
        time.setFont(f);
        launch.add(time);


        String[] Time = { "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00"};

        String[] ti={"AM","PM"};

        JComboBox times = new JComboBox(Time);
        times.setFont(f1);
        times.setBounds(250,490,100,60);
        launch.add(times);

        JComboBox tis = new JComboBox(ti);
        tis.setBounds(350,490,80,60);
        tis.setFont(f1);
        launch.add(tis);

        JLabel venue = new JLabel("VENUE :");
        venue.setBounds(150,540,350,60);
        venue.setFont(f);
        launch.add(venue);

        String[] Venues={"Jio World Convention Centre,Bandra east","The Westin Mumbai Powai Lake,Powai","Kora Kendra Grounds,Borivali west",
                "JW Marriott Sahar,Andheri west"};


        JComboBox venueText = new JComboBox(Venues);
        venueText.setBounds(250,540,450,60);
        venueText.setFont(f1);
        launch.add(venueText);




        JLabel guests = new JLabel("NUMBER OF GUESTS :");
        guests.setBounds(150,590,350,40);
        guests.setFont(f);
        launch.add(guests);

        JTextField guestsText = new JTextField("");
        guestsText.setBounds(150,620,500,40);
        guestsText.setFont(f1);
        launch.add(guestsText);



        JButton submit = new JButton("SUBMIT");
        submit.setBounds(700,790,150,50);
        submit.setFont(f);
        launch.add(submit);

        JButton cancel = new JButton("CANCEL");
        cancel.setBounds(100,790,150,50);
        cancel.setFont(f);
        launch.add(cancel);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name= nametext.getText();
                String contact= contactText.getText();
                String date= edate.getSelectedItem()+"/"+month.getSelectedItem()+"/"+year.getSelectedItem();
                String venue= (String)venueText.getSelectedItem();
                String guest= guestsText.getText();
                String time=(String) times.getSelectedItem() + tis.getSelectedItem();
                String compname=cnameText.getText();
                Connection con1;
                PreparedStatement insert;
                boolean t=true;
                if(name.isEmpty()|| contact.isEmpty() || guest.isEmpty())
                {
                    JOptionPane.showMessageDialog(launch,"Please enter all field");
                    return;
                }


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost/eventmanagement","root","7499373180");
                    insert =con1.prepareStatement("insert into launch_event(date,venue,name,company_name,guest_count,ph_no,time)values(?,?,?,?,?,?,?)");
                    insert.setString(1,date);
                    insert.setString(2,venue);
                    insert.setString(3,name);
                    insert.setString(4,compname);
                    insert.setString(5,guest);
                    insert.setString(6,contact);
                    insert.setString(7,time);

                    insert.executeUpdate();

                    insert =con1.prepareStatement("insert into eventdetail(date,venue,time,name,phone_no,guestno,event)values(?,?,?,?,?,?,?)");
                    insert.setString(1,date);
                    insert.setString(2,venue);
                    insert.setString(3,time);
                    insert.setString(4,name);
                    insert.setString(5,contact);
                    insert.setString(6,guest);
                    insert.setString(7,"Product Launch");
                    insert.executeUpdate();

                    insert.executeUpdate();



                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(festivalgathering.class.getName()).log(Level.SEVERE,null,ex);
                    t=false;
                }
                if(t==true)
                {
                    JOptionPane.showMessageDialog(launch,"Data Store\nEvent created");
                    homepage hp5=new homepage();
                }
                else
                {
                    JOptionPane.showMessageDialog(launch,"Venue is already reserved\nPlease select another date or venue");
                }

            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(launch, "Do you really want to Cancel", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0)
                {
                    homepage hp5=new homepage();
                }
            }
        });


        launch.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(launch, "Do you really want to Close Application");
                if (a == JOptionPane.YES_OPTION) {
                    launch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION)
                {
                    launch.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });


        launch.setTitle("Event Management");
        launch.setLayout(null);
        launch.setSize(900,900);
        launch.setVisible(true);


    }

}

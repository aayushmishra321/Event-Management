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

public class charity
{
    JFrame charit=new JFrame();

    charity()
    {
        Font f=new Font("Arial",Font.BOLD,22);
        Font f1=new Font("Arial",Font.PLAIN,20);
        Font f2=new Font("Arial",Font.BOLD,26);

        JLabel event = new JLabel("CHARITY EVENT");
        event.setBounds(320,80,400,40);
        event.setFont(f2);
        charit.add(event);

        JLabel name = new JLabel("ORGANIZER NAME :");
        name.setBounds(150,200,250,40);
        name.setFont(f);
        charit.add(name);

        JTextField nametext = new JTextField("");
        nametext.setBounds(150,230,500,50);
        nametext.setFont(f1);
        charit.add(nametext);

        JLabel contact = new JLabel("CONTACT NUMBER :");
        contact.setBounds(150,290,350,40);
        contact.setFont(f);
        charit.add(contact);

        JTextField contactText = new JTextField("");
        contactText.setBounds(150,320,500,50);
        contactText.setFont(f1);
        charit.add(contactText);


        JLabel date = new JLabel("DATE :");
        date.setBounds(150,380,200,60);
        date.setFont(f);
        charit.add(date);


        String[] Date = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

        String[] Month = { "January", "february", "March", "April", "May", "Jun", "July", "August",
                "September", "October", "November", "December" };

        String[] Year = { "2023", "2024", "2025" };

        JComboBox edate = new JComboBox(Date);
        edate.setFont(f1);
        edate.setBounds(250,380,80,60);
        charit.add(edate);

        JComboBox month = new JComboBox(Month);
        month.setFont(f1);
        month.setBounds(330,380,150,60);
        charit.add(month);

        JComboBox year = new JComboBox(Year);
        year.setFont(f1);
        year.setBounds(480,380,100,60);
        charit.add(year);





        JLabel venue = new JLabel("VENUE :");
        venue.setBounds(150,440,200,60);
        venue.setFont(f);
        charit.add(venue);

        String[] Venues={"Nadar Sakthi,Andheri","LOBEAD: Morello Halls,Bandra","MMRDA Grounds,Bandra east"};


        JComboBox venueText = new JComboBox(Venues);
        venueText.setBounds(250,440,350,60);
        venueText.setFont(f1);
        charit.add(venueText);



        JLabel guests = new JLabel("NUMBER OF GUESTS :");
        guests.setBounds(150,500,350,40);
        guests.setFont(f);
        charit.add(guests);

        JTextField guestsText = new JTextField("");
        guestsText.setBounds(150,530,500,50);
        guestsText.setFont(f1);
        charit.add(guestsText);



        JButton submit = new JButton("SUBMIT");
        submit.setBounds(700,750,150,50);
        submit.setFont(f);
        charit.add(submit);

        JButton cancel = new JButton("CANCEL");
        cancel.setBounds(100,750,150,50);
        cancel.setFont(f);
        charit.add(cancel);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name= nametext.getText();
                String contact= contactText.getText();
                String date= edate.getSelectedItem()+"/"+month.getSelectedItem()+"/"+year.getSelectedItem();
                String venue= (String)venueText.getSelectedItem();
                String guest= guestsText.getText();
                Connection con1;
                PreparedStatement insert;
                boolean t=true;
                String time="00:00";

                if(name.isEmpty()|| contact.isEmpty() || guest.isEmpty())
                {
                    JOptionPane.showMessageDialog(charit,"Please enter all field");
                    return;
                }


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost/eventmanagement","root","7499373180");
                    insert =con1.prepareStatement("insert into charity(date,venue,name,guest_count,ph_no)values(?,?,?,?,?)");
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
                    insert.setString(7,"Charity");
                    insert.executeUpdate();




                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(festivalgathering.class.getName()).log(Level.SEVERE,null,ex);
                    t=false;
                }

                if(t==true)
                {
                    JOptionPane.showMessageDialog(charit,"Data Store\nEvent created");
                    homepage hp2=new homepage();
                }
                else
                {
                    JOptionPane.showMessageDialog(charit,"Venue is already reserved\nPlease select another date or venue");
                }


            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(charit, "Do you really want to Cancel", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0)
                {
                    homepage hp2=new homepage();
                }
            }
        });


        charit.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(charit, "Do you really want to Close Application");
                if (a == JOptionPane.YES_OPTION) {
                    charit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION)
                {
                    charit.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });


        charit.setTitle("Event Management");
        charit.setLayout(null);
        charit.setSize(900,900);
        charit.setVisible(true);


    }

}
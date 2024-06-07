import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class birthdayparty
{
    JFrame party=new JFrame();

    birthdayparty()
    {
        Font f=new Font("Arial",Font.BOLD,22);
        Font f1=new Font("Arial",Font.PLAIN,20);
        Font f2=new Font("Arial",Font.BOLD,26);


        JLabel event = new JLabel("BIRRTHDAY PARTY");
        event.setBounds(320,80,400,40);
        event.setFont(f2);
        party.add(event);

        JLabel name = new JLabel("ORGANIZER NAME :");
        name.setBounds(150,200,250,40);
        name.setFont(f);
        party.add(name);

        JTextField nametext = new JTextField("");
        nametext.setBounds(150,230,500,50);
        nametext.setFont(f1);
        party.add(nametext);
        nametext.requestFocus();

        JLabel contact = new JLabel("CONTACT NUMBER :");
        contact.setBounds(150,290,350,40);
        contact.setFont(f);
        party.add(contact);

        JTextField contactText = new JTextField("");
        contactText.setBounds(150,320,500,50);
        contactText.setFont(f1);
        party.add(contactText);


        JLabel date = new JLabel("DATE :");
        date.setBounds(150,380,200,60);
        date.setFont(f);
        party.add(date);

        String[] Date = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

        String[] Month = { "January", "february", "March", "April", "May", "Jun", "July", "August",
                "September", "October", "November", "December" };

        String[] Year = { "2023", "2024", "2025" };

        JComboBox edate = new JComboBox(Date);
        edate.setFont(f1);
        edate.setBounds(250,380,80,60);
        party.add(edate);

        JComboBox month = new JComboBox(Month);
        month.setFont(f1);
        month.setBounds(330,380,150,60);
        party.add(month);

        JComboBox year = new JComboBox(Year);
        year.setFont(f1);
        year.setBounds(480,380,100,60);
        party.add(year);

        JLabel otime = new JLabel("TIME :");
        otime.setBounds(150,440,200,60);
        otime.setFont(f);
        party.add(otime);

        String[] Time = { "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00"};

        String[] ti={"AM","PM"};

        JComboBox times = new JComboBox(Time);
        times.setFont(f1);
        times.setBounds(250,440,100,60);
        party.add(times);

        JComboBox tis = new JComboBox(ti);
        tis.setBounds(350,440,80,60);
        tis.setFont(f1);
        party.add(tis);

        JLabel venue = new JLabel("VENUE :");
        venue.setBounds(150,500,250,60);
        venue.setFont(f);
        party.add(venue);

        String[] Venues={"Taj Hotel","Oberoi Hotel","Glocal Junction,Andheri west","Cafe General,Chowpatty","Baroke,Grant Road"};


        JComboBox venueText = new JComboBox(Venues);
        venueText.setBounds(250,500,300,50);
        venueText.setFont(f1);
        party.add(venueText);

        JLabel guests = new JLabel("NUMBER OF GUESTS :");
        guests.setBounds(150,560,350,40);
        guests.setFont(f);
        party.add(guests);

        JTextField guestsText = new JTextField("");
        guestsText.setBounds(150,590,500,50);
        guestsText.setFont(f1);
        party.add(guestsText);



        JButton submit = new JButton("SUBMIT");
        submit.setBounds(700,750,150,50);
        submit.setFont(f);
        party.add(submit);

        JButton cancel = new JButton("CANCEL");
        cancel.setBounds(100,750,150,50);
        cancel.setFont(f);
        party.add(cancel);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name= nametext.getText();
                String contact= contactText.getText();
                String date= edate.getSelectedItem()+"/"+month.getSelectedItem()+"/"+year.getSelectedItem();
                String venue= (String)venueText.getSelectedItem();
                String guest= guestsText.getText();
                String time=(String) times.getSelectedItem()+tis.getSelectedItem();
                Connection con1;
                PreparedStatement insert;
                boolean t=true;

                if(name.isEmpty()|| contact.isEmpty() || guest.isEmpty())
                {
                    JOptionPane.showMessageDialog(party,"Please enter all field");
                    return;
                }


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost/eventmanagement","root","7499373180");
                    insert =con1.prepareStatement("insert into bday_party(date,venue,name,guest_count,ph_no,time)values(?,?,?,?,?,?)");
                    insert.setString(1,date);
                    insert.setString(2,venue);
                    insert.setString(3,name);
                    insert.setString(4,guest);
                    insert.setString(5,contact);
                    insert.setString(6,time);
                    insert.executeUpdate();

                    insert =con1.prepareStatement("insert into eventdetail(date,venue,time,name,phone_no,guestno,event)values(?,?,?,?,?,?,?)");
                    insert.setString(1,date);
                    insert.setString(2,venue);
                    insert.setString(3,time);
                    insert.setString(4,name);
                    insert.setString(5,contact);
                    insert.setString(6,guest);
                    insert.setString(7,"Birthday Party");
                    insert.executeUpdate();



                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(festivalgathering.class.getName()).log(Level.SEVERE,null,ex);
                    t=false;
                }
                if(t==true)
                {
                    JOptionPane.showMessageDialog(party,"Data Store\nEvent created");
                    homepage hp1=new homepage();
                }
                else
                {
                    JOptionPane.showMessageDialog(party,"Venue is already reserved\nPlease select another date or venue");
                }





            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(party, "Do you really want to Cancel", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0)
                {
                    homepage hp1=new homepage();
                }
            }
        });


        party.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(party, "Do you really want to Close Application");
                if (a == JOptionPane.YES_OPTION) {
                    party.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION)
                {
                    party.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });




        party.setTitle("Event Management");
        party.setLayout(null);
        party.setSize(900,900);
        party.setVisible(true);


    }

}

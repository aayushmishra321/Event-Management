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

public class seminar
{
    JFrame semi=new JFrame();

    seminar()
    {
        Font f=new Font("Arial",Font.BOLD,22);
        Font f1=new Font("Arial",Font.PLAIN,20);
        Font f2=new Font("Arial",Font.BOLD,26);

        JLabel event = new JLabel("SEMINAR EVENT");
        event.setBounds(320,80,400,40);
        event.setFont(f2);
        semi.add(event);

        JLabel name = new JLabel("ORGANIZER NAME :");
        name.setBounds(150,200,250,40);
        name.setFont(f);
        semi.add(name);

        JTextField nametext = new JTextField("");
        nametext.setBounds(150,230,500,50);
        nametext.setFont(f1);
        semi.add(nametext);

        JLabel contact = new JLabel("CONTACT NUMBER :");
        contact.setBounds(150,290,350,40);
        contact.setFont(f);
        semi.add(contact);

        JTextField contactText = new JTextField("");
        contactText.setBounds(150,320,500,50);
        contactText.setFont(f1);
        semi.add(contactText);


        JLabel date = new JLabel("DATE :");
        date.setBounds(150,380,200,60);
        date.setFont(f);
        semi.add(date);

        String[] Date = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

        String[] Month = { "January", "february", "March", "April", "May", "Jun", "July", "August",
                "September", "October", "November", "December" };

        String[] Year = { "2023", "2024", "2025" };

        JComboBox edate = new JComboBox(Date);
        edate.setFont(f1);
        edate.setBounds(250,380,80,60);
        semi.add(edate);

        JComboBox month = new JComboBox(Month);
        month.setFont(f1);
        month.setBounds(330,380,150,60);
        semi.add(month);

        JComboBox year = new JComboBox(Year);
        year.setFont(f1);
        year.setBounds(480,380,100,60);
        semi.add(year);


        JLabel cname = new JLabel("COMPANY NAME :");
        cname.setBounds(150,440,200,40);
        cname.setFont(f);
        semi.add(cname);

        JTextField cnameText = new JTextField("");
        cnameText.setBounds(150,470,500,50);
        cnameText.setFont(f1);
        semi.add(cnameText);


        JLabel venue = new JLabel("VENUE :");
        venue.setBounds(150,530,350,60);
        venue.setFont(f);
        semi.add(venue);

        String[] Venues={"Cidco Convention center Banquet hall,Navi mumbai","BMPA HALL City Center 4th Floor 411,Bhiwandi","Walchand Hirachand Hall,churchgate"};


        JComboBox venueText = new JComboBox(Venues);
        venueText.setBounds(250,530,450,60);
        venueText.setFont(f1);
        semi.add(venueText);

        JLabel guests = new JLabel("NUMBER OF GUESTS :");
        guests.setBounds(150,590,350,40);
        guests.setFont(f);
        semi.add(guests);

        JTextField guestsText = new JTextField("");
        guestsText.setBounds(150,620,500,50);
        guestsText.setFont(f1);
        semi.add(guestsText);






        JButton submit = new JButton("SUBMIT");
        submit.setBounds(700,750,150,50);
        submit.setFont(f);
        semi.add(submit);

        JButton cancel = new JButton("CANCEL");
        cancel.setBounds(100,750,150,50);
        cancel.setFont(f);
        semi.add(cancel);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name= nametext.getText();
                String contact= contactText.getText();
                String date= edate.getSelectedItem()+"/"+month.getSelectedItem()+"/"+year.getSelectedItem();
                String venue= (String)venueText.getSelectedItem();
                String guest= guestsText.getText();
                String compname=cnameText.getText();
                String time="00:00";
                Connection con1;
                PreparedStatement insert;

                if(name.isEmpty()|| contact.isEmpty() || guest.isEmpty())
                {
                    JOptionPane.showMessageDialog(semi,"Please enter all field");
                    return;
                }


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost/eventmanagement","root","7499373180");
                    insert =con1.prepareStatement("insert into seminar(date,venue,name,company_name,guest_count,ph_no)values(?,?,?,?,?,?)");
                    insert.setString(1,date);
                    insert.setString(2,venue);
                    insert.setString(3,name);
                    insert.setString(4,compname);
                    insert.setString(5,guest);
                    insert.setString(6,contact);
                    insert.executeUpdate();

                    insert =con1.prepareStatement("insert into eventdetail(date,venue,time,name,phone_no,guestno,event)values(?,?,?,?,?,?,?)");
                    insert.setString(1,date);
                    insert.setString(2,venue);
                    insert.setString(3,time);
                    insert.setString(4,name);
                    insert.setString(5,contact);
                    insert.setString(6,guest);
                    insert.setString(7,"Seminar");
                    insert.executeUpdate();

                    JOptionPane.showMessageDialog(semi,"Data Store\nEvent created");
                    homepage hp6=new homepage();



                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(festivalgathering.class.getName()).log(Level.SEVERE,null,ex);
                    JOptionPane.showMessageDialog(semi,"Venue is already reserved\nPlease select another date or venue");

                }



            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(semi, "Do you really want to Cancel", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0)
                {
                    homepage hp6=new homepage();
                }
            }
        });


        semi.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(semi, "Do you really want to Close Application");
                if (a == JOptionPane.YES_OPTION) {
                    semi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION)
                {
                    semi.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });


        semi.setTitle("Event Management");
        semi.setLayout(null);
        semi.setSize(900,900);
        semi.setVisible(true);


    }

}

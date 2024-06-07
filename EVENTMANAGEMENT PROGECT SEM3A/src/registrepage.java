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

public class registrepage
{

    JFrame rp=new JFrame();

    public registrepage()
    {
        Font f=new Font("Arial",Font.BOLD,22);
        Font f1=new Font("Arial",Font.PLAIN,20);

        JLabel rpage=new JLabel("Registration Page");
        rpage.setBounds(20,20,300,50);
        rpage.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        rp.add(rpage);

        JLabel page=new JLabel("Registration Form");
        page.setBounds(330,100,300,80);
        page.setFont(f);
        rp.add(page);

        JLabel name = new JLabel("NAME :");
        name.setFont(f1);
        name.setBounds(230, 240,100,50);
        rp.add(name);

        JTextField tname = new JTextField();
        tname.setFont(f);
        tname.setBounds(380, 240,350,50);
        rp.add(tname);

        JLabel mobile = new JLabel("MOBILE NO :");
        mobile.setFont(f1);
        mobile.setBounds(230, 300,200,50);
        rp.add(mobile);

        JTextField tmob = new JTextField();
        tmob.setFont(f);
        tmob.setBounds(380, 300,350,50);
        rp.add(tmob);

        JLabel gender = new JLabel("GENDER :");
        gender.setFont(f1);
        gender.setBounds(230, 360,200,50);
        rp.add(gender);

        JRadioButton male = new JRadioButton("MALE");
        male.setFont(f);
        male.setSelected(true);
        male.setBounds(380,360,100,50);
        rp.add(male);

        JRadioButton female = new JRadioButton("FEMALE");
        female.setFont(f);
        female.setSelected(true);
        female.setBounds(480,360,200,50);
        rp.add(female);

        ButtonGroup gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        JLabel email = new JLabel("EMAIL ID :");
        email.setFont(f1);
        email.setBounds(230, 420,250,50);
        rp.add(email);

        JTextField tem = new JTextField();
        tem.setFont(f);
        tem.setBounds(380, 420,350,50);
        rp.add(tem);

        String[] Date = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

        String[] Month = { "January", "february", "March", "April", "May", "Jun", "July", "August",
                "September", "October", "November", "December" };

        String[] Year = { "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006"};

        JLabel dob = new JLabel("DATE OF BIRTH :");
        dob.setFont(f1);
        dob.setBounds(230, 480,250,60);
        rp.add(dob);

        JComboBox edate = new JComboBox(Date);
        edate.setFont(f1);
        edate.setBounds(400,480,80,60);
        rp.add(edate);

        JComboBox month = new JComboBox(Month);
        month.setFont(f1);
        month.setBounds(480,480,150,60);
        rp.add(month);

        JComboBox year = new JComboBox(Year);
        year.setFont(f1);
        year.setBounds(630,480,100,60);
        rp.add(year);

        JLabel passw=new JLabel("PASSWORD :");
        passw.setBounds(230,540,300,40);
        passw.setFont(f1);
        rp.add(passw);

        JPasswordField pass=new JPasswordField();
        pass.setBounds(380,540,350,40);
        pass.setFont(f1);
        rp.add(pass);

        JLabel cpassw=new JLabel("CONFIRM PASSWORD :");
        cpassw.setBounds(230,600,350,40);
        cpassw.setFont(f1);
        rp.add(cpassw);

        JPasswordField cpass=new JPasswordField();
        cpass.setBounds(470,600,300,40);
        cpass.setFont(f1);
        rp.add(cpass);



        JButton submit = new JButton("SUBMIT");
        submit.setBounds(700,790,150,50);
        submit.setFont(f);
        rp.add(submit);

        JButton cancel = new JButton("CANCEL");
        cancel.setBounds(550,790,150,50);
        cancel.setFont(f);
        rp.add(cancel);

        JButton reset = new JButton("RESET");
        reset.setBounds(80,790,150,50);
        reset.setFont(f);
        rp.add(reset);






        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String oname= tname.getText();
                String ocontact= tmob.getText();
                String ogender;
                String pw=String.valueOf(pass.getPassword());
                String cpw=String.valueOf(cpass.getPassword());
                if(male.isSelected())
                {
                    ogender="Male";
                }
                else
                {
                    ogender="Female";
                }
                String oemail=tem.getText();
                String odob=edate.getSelectedItem()+"/"+month.getSelectedItem()+"/"+year.getSelectedItem();


                Connection con1;
                PreparedStatement insert;
                if(oname.isEmpty() || ocontact.isEmpty() || oemail.isEmpty() ||  pw.isEmpty() || cpw.isEmpty() || odob.isEmpty() || ogender.isEmpty() ){
                    JOptionPane.showMessageDialog(rp,"Please enter all field");
                    return;
                }

                if(!pw.equals(cpw)){
                    JOptionPane.showMessageDialog(rp,"Confirm password does not match");
                    return;
                }

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost/eventmanagement","root","7499373180");
                    insert =con1.prepareStatement("insert into userinfo(phone_no,name,gender,email_id,dob,password)values(?,?,?,?,?,?)");
                    insert.setString(1,ocontact);
                    insert.setString(2,oname);
                    insert.setString(3,ogender);
                    insert.setString(4,oemail);
                    insert.setString(5,odob);
                    insert.setString(6,pw);
                    insert.executeUpdate();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(festivalgathering.class.getName()).log(Level.SEVERE,null,ex);
                }



                JOptionPane.showMessageDialog(rp,"Registration Completed");
                homepage pg=new homepage();

            }
        });


        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(rp, "Do you really want to Cancel", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0)
                {
                    firstpage back=new firstpage();
                }
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tem.setText("");
                tname.setText("");
                tmob.setText("");
                pass.setText("");
                cpass.setText("");


            }
        });

        rp.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(rp, "Do you really want to Close Application");
                if (a == JOptionPane.YES_OPTION) {
                    rp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION)
                {
                    rp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });




        rp.setTitle("Event Management");
        rp.setLayout(null);
        rp.setSize(900,900);
        rp.setVisible(true);


    }
}

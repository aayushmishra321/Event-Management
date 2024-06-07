import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.attribute.UserPrincipal;
import java.sql.*;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class loginpage
{
    JFrame loginp=new JFrame();

    public  loginpage()
    {

        Font f=new Font("Arial",Font.BOLD,22);
        Font f1=new Font("Arial",Font.PLAIN,20);

        JLabel lp=new JLabel("Login Page");
        lp.setBounds(20,20,300,50);
        lp.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        loginp.add(lp);

        JLabel id=new JLabel("Enter Mobile No :");
        id.setBounds(250,200,350,40);
        id.setFont(f);
        loginp.add(id);

        JTextField idno=new JTextField("");
        idno.setBounds(430,200,300,40);
        idno.setFont(f1);
        loginp.add(idno);

        JLabel passw=new JLabel("PASSWORD :");
        passw.setBounds(250,250,300,40);
        passw.setFont(f);
        loginp.add(passw);

        JPasswordField pass=new JPasswordField("");
        pass.setBounds(430,250,300,40);
        idno.setFont(f1);
        loginp.add(pass);




        JButton key=new JButton("LOGIN");
        key.setBounds(490,400,150,50);
        key.setFont(f1);
        loginp.add(key);



        JButton key2=new JButton("CANCEL");
        key2.setBounds(290,400,150,50);
        key2.setFont(f1);
        loginp.add(key2);


        key.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cont=idno.getText();
                String epassw=String.valueOf(pass.getPassword());
                Connection con1;
                PreparedStatement insert ;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost/eventmanagement","root","7499373180");
                    Statement stmt=con1.createStatement () ;


                   String sql = "select * from userinfo where phone_no='"+cont+"' and password='"+epassw+"'";
                    ResultSet rs= stmt.executeQuery(sql);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(loginp,"Login Successful");
                        homepage pg=new homepage();
                    }
                    else {
                        JOptionPane.showMessageDialog(loginp, "Invalid Phone no. Or Password ");
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(festivalgathering.class.getName()).log(Level.SEVERE,null,ex);


                }

            }
        });



        key2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(loginp, "Do you really want to Cancel", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0)
                {
                    firstpage back=new firstpage();
                }
            }
        });

        loginp.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(loginp, "Do you really want to Close Application");
                if (a == JOptionPane.YES_OPTION) {
                    loginp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION)
                {
                    loginp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        loginp.setTitle("Event Management");
        loginp.setLayout(null);
        loginp.setSize(900,900);
        loginp.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


class firstpage
{

        JFrame login=new JFrame();
        public firstpage()
        {

            Font f=new Font("Arial",Font.BOLD,22);
            Font f1=new Font("Arial",Font.PLAIN,20);
            Font f2=new Font("Arial",Font.BOLD,35);


            JMenuBar m1 = new JMenuBar();
            JMenu menu = new JMenu("User options");
            JMenuItem a1 = new JMenuItem("Log In");
            JMenuItem a2 = new JMenuItem("Register");
            menu.add(a1);
            menu.add(a2);
            m1.add(menu);
            login.setJMenuBar(m1);

            JLabel page=new JLabel("WELCOME");
            page.setBounds(370,250,300,80);
            page.setFont(f2);
            login.add(page);

            JLabel page1=new JLabel("TO");
            page1.setBounds(425,300,150,80);
            page1.setFont(f2);
            login.add(page1);

            JLabel page2=new JLabel("EVENT MANAGEMENT");
            page2.setBounds(265,350,450,80);
            page2.setFont(f2);
            login.add(page2);

            JLabel page3=new JLabel("PAGE");
            page3.setBounds(410,400,150,80);
            page3.setFont(f2);
            login.add(page3);

            JButton key=new JButton("SIGN IN");
            key.setBounds(290,700,150,50);
            key.setFont(f1);
            login.add(key);

            JLabel page4=new JLabel("OR");
            page4.setBounds(450,700,40,50);
            page4.setFont(f);
            login.add(page4);

            JButton key2=new JButton("REGISTER");
            key2.setBounds(490,700,150,50);
            key2.setFont(f1);
            login.add(key2);

            a1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loginpage et=new loginpage();
                }
            });
            a2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    registrepage rp=new registrepage();
                }
            });


            key.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    loginpage et=new loginpage();

                }
            });
            key2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registrepage rp=new registrepage();

                }
            });

            login.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int a = JOptionPane.showConfirmDialog(login, "Do you really want to Close Application");
                    if (a == JOptionPane.YES_OPTION) {
                        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                    else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION)
                    {
                        login.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
                }
            });

            login.setTitle("Event Management");
            login.setLayout(null);
            login.setSize(900,900);
            login.setVisible(true);


        }

}
public class eventmanagement
{

    public static void main(String[] args)
    {

        firstpage li=new firstpage();

    }

}

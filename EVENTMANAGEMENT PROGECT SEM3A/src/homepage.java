import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class homepage extends registrepage
{
    JFrame f=new JFrame("Event Management");


    homepage(){
        Font f1=new Font("Arial",Font.BOLD,22);
        JLabel homepage=new JLabel("Home Page");
        homepage.setBounds(20,20,300,50);
        homepage.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        f.add(homepage);




        JLabel l1 = new JLabel("WELCOME !! ");
        l1.setBounds(300, 140, 300, 80);
        l1.setFont(new Font("Times New Roman",Font.PLAIN, 30));
        f.add(l1);

        JButton b1 = new JButton(new ImageIcon("/Users/pawantiwari/Desktop/EVENTMANAGEMENT PROGECT SEM3A/IMG-20231014-WA0029.jpg"));
        b1.setBounds(260, 280, 55, 55);
        b1.setBackground(Color.GRAY);
        f.add(b1);

        JLabel l2 = new JLabel("Register an new event");
        l2.setBounds(320, 280,300, 55);
        l2.setFont(new Font("Arial",Font.PLAIN, 23));
        f.add(l2);


        JButton b2 = new JButton(new ImageIcon("/Users/pawantiwari/Desktop/EVENTMANAGEMENT PROGECT SEM3A/IMG-20231014-WA0030.jpg"));
        b2.setBounds(260, 360, 55, 55);
        b2.setBackground(Color.GRAY);

        f.add(b2);

        JButton cancel = new JButton("Logout");
        cancel.setBounds(100,750,150,50);
        cancel.setFont(f1);
        f.add(cancel);


        JLabel l3 = new JLabel("View your events");
        l3.setBounds(320, 360,300, 55);
        l3.setFont(new Font("Arial",Font.PLAIN, 23));
        f.add(l3);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typesofevent et=new typesofevent();

            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            userdetail ud1=new userdetail();
            }
        });
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(f, "Do you really want to Close Application");
                if (a == JOptionPane.YES_OPTION) {
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION)
                {
                    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(f, "Do you really want to LogOut", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0)
                {
                    firstpage fp=new firstpage();
                }
            }
        });



        f.setSize(900, 900);
        f.setLayout(null);
        f.setVisible(true);


    }

}

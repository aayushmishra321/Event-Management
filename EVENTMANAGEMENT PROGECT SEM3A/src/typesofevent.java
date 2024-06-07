import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class typesofevent
{
    JFrame event=new JFrame();
    public typesofevent()
    {
        JRadioButton r1,r2,r3,r4,r5,r6,r7,r8;

        Font f=new Font("Arial",Font.BOLD,22);
        Font f1=new Font("Arial",Font.PLAIN,20);
        Font f2=new Font("Arial",Font.BOLD,26);

        JLabel eventty=new JLabel("Select Event");
        eventty.setBounds(350,80,200,50);
        eventty.setFont(f2);
        event.add(eventty);

        JLabel noncorpo=new JLabel("Noncorporate Event");
        noncorpo.setBounds(25,220,300,50);
        noncorpo.setFont(f);
        event.add(noncorpo);

        r1=new JRadioButton("EXHIBITION");
        r1.setBounds(50,270,200,50);
        r1.setFont(f1);
        r1.setSelected(true);
        event.add(r1);

        r2=new JRadioButton("FESTIVAL GATHERING");
        r2.setBounds(300,270,250,50);
        r2.setFont(f1);
        r2.setSelected(true);
        event.add(r2);

        r3=new JRadioButton("CHARITY");
        r3.setBounds(600,270,150,50);
        r3.setFont(f1);
        r3.setSelected(true);
        event.add(r3);

        r4=new JRadioButton("WEDDING");
        r4.setBounds(50,370,150,50);
        r4.setFont(f1);
        r4.setSelected(true);
        event.add(r4);

        r5=new JRadioButton("BIRTHDAY PARTY");
        r5.setBounds(300,370,250,50);
        r5.setFont(f1);
        r5.setSelected(true);
        event.add(r5);

        JLabel corpo=new JLabel("Corporate Event");
        corpo.setBounds(25,520,300,50);
        corpo.setFont(f);

        event.add(corpo);

        r6=new JRadioButton("PRODUCT LAUNCH");
        r6.setBounds(50,570,250,50);
        r6.setFont(f1);
        r6.setSelected(true);
        event.add(r6);

        r7=new JRadioButton("SEMINAR");
        r7.setBounds(300,570,150,50);
        r7.setFont(f1);
        r7.setSelected(true);
        event.add(r7);

        r8=new JRadioButton("COMPANY PARTY");
        r8.setBounds(500,570,350,50);
        r8.setFont(f1);
        r8.setSelected(true);
        event.add(r8);

        ButtonGroup geng = new ButtonGroup();
        geng.add(r1);
        geng.add(r2);
        geng.add(r3);
        geng.add(r4);
        geng.add(r5);
        geng.add(r6);
        geng.add(r7);
        geng.add(r8);

        JButton conti=new JButton("CONTINUE");
        conti.setBounds(680,750,200,50);
        conti.setFont(f);
        event.add(conti);

        JButton cancel = new JButton("CANCEL");
        cancel.setBounds(100,750,150,50);
        cancel.setFont(f);
        event.add(cancel);

        conti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(r1.isSelected())
                {
                    JOptionPane.showMessageDialog(event,"YOU SELECT EXHIBITION EVENT");
                    exhibition exh=new exhibition();

                }
                else if(r2.isSelected())
                {
                    JOptionPane.showMessageDialog(event,"YOU SELECT FESTIVAL GATHERING EVENT");
                    festivalgathering fest=new festivalgathering();

                }
                else if(r3.isSelected())
                {
                    JOptionPane.showMessageDialog(event,"YOU SELECT CHARITY EVENT");
                    charity ch=new charity();

                }
                else if(r4.isSelected())
                {
                    JOptionPane.showMessageDialog(event,"YOU SELECT WEDDING EVENT");
                    wedding wed=new wedding();

                }
                else if(r5.isSelected())
                {
                    JOptionPane.showMessageDialog(event,"YOU SELECT BIRTHDAY PARTY EVENT");
                    birthdayparty bip=new birthdayparty();

                }
                else if(r6.isSelected())
                {
                    JOptionPane.showMessageDialog(event,"YOU SELECT PRODUCT LAUNCH EVENT");
                    productlaunch prl=new productlaunch();

                }
                else if(r7.isSelected())
                {
                    JOptionPane.showMessageDialog(event,"YOU SELECT SEMINAR EVENT");
                    seminar sem=new seminar();

                }
                else if(r8.isSelected())
                {
                    JOptionPane.showMessageDialog(event,"YOU SELECT COMPANY PARTY EVENT");
                    companyparty cop=new companyparty();

                }

            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(event, "Do you really want to go back", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0)
                {
                    homepage hp7=new homepage();
                }
            }
        });

        event.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(event, "Do you really want to Close Application");
                if (a == JOptionPane.YES_OPTION) {
                    event.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION)
                {
                    event.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        event.setTitle("Event Management");
        event.setLayout(null);
        event.setSize(900,900);
        event.setVisible(true);

    }


}

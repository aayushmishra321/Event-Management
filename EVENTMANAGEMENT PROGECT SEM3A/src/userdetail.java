import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class userdetail<my_mysql>
{
    JFrame frame=new JFrame("Event Management");

    public userdetail()
    {

        Font f=new Font("Arial",Font.BOLD,22);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField phoneNumberField = new JTextField(15);
        JButton searchButton = new JButton("Show Data");


        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Phone Number: "));
        topPanel.add(phoneNumberField);
        topPanel.add(searchButton);
        DefaultTableModel model = new DefaultTableModel();

        JTable table = new JTable(model);


        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton cancel = new JButton("CANCEL");
        cancel.setBounds(100,750,150,50);
        cancel.setFont(f);
        frame.add(cancel);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String conta=phoneNumberField.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/eventmanagement", "root", "7499373180");


                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM eventdetail where phone_no='"+conta+"'");


                    int columnCount = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        model.addColumn(rs.getMetaData().getColumnName(i));
                    }


                    while (rs.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = rs.getObject(i);
                        }
                        model.addRow(row);
                    }




                    conn.close();
                } catch(SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(festivalgathering.class.getName()).log(Level.SEVERE,null,ex);
                    JOptionPane.showConfirmDialog(frame, "Invalid Phone No.");
                }

            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(frame, "Do you really want to go back", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0)
                {
                    homepage hp7=new homepage();
                }
            }
        });



        JScrollPane jsp=new JScrollPane(table);

        frame.add(jsp);

        frame.pack();


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(frame, "Do you really want to Close Application");
                if (a == JOptionPane.YES_OPTION) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION)
                {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        frame.setSize(900,900);
        frame.setVisible(true);

    }
}

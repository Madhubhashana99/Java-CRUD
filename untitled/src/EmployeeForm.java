import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeForm {
    private JTextField txtname;
    private JTextField txtsalary;
    private JTextField txtmobile;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton saveButton;
    private JTable table1;
    private JPanel main;
    private JTextField textField1;


    public static void main(String[] args) {
        JFrame frame = new JFrame("EmployeeForm");
        frame.setContentPane(new EmployeeForm().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;
    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/company","root","");
            System.out.println("Success..");
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public EmployeeForm() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}

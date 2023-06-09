import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
    private JTextField empid;
    private JScrollPane table_1;


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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/company","root","");
            System.out.println("Success..");
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    void table_load(){
        try{
            pst = con.prepareStatement("select * from company");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public EmployeeForm() {
        connect();
        table_load();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empname,salary,mobile;

                empname = txtname.getText();
                salary = txtsalary.getText();
                mobile = txtmobile.getText();

                try{
                    pst = con.prepareStatement("insert into company(empname,salary,mobile) value(?,?,?)");
                    pst.setString(1,empname);
                    pst.setString(2,salary);
                    pst.setString(3,mobile);
                    pst.executeUpdate();
                    table_load();
                    JOptionPane.showMessageDialog(null,"Data Added..");
                    txtname.setText("");
                    txtsalary.setText("");
                    txtmobile.setText("");
                    txtname.requestFocus();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empname,salary,mobile,id;

                empname = txtname.getText();
                salary = txtsalary.getText();
                mobile = txtmobile.getText();
                id = empid.getText();

                try{
                    pst = con.prepareStatement("update company set empname=?,salary=?,mobile=? where id=?");
                    pst.setString(1,empname);
                    pst.setString(2,salary);
                    pst.setString(3,mobile);
                    pst.setString(4,id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Updated..");
                    table_load();
                    txtname.setText("");
                    txtsalary.setText("");
                    txtmobile.setText("");
                    txtname.requestFocus();


                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    String id = empid.getText();
                    pst = con.prepareStatement("select empname,salary,mobile from company where id=?");
                    pst.setString(1,id);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()==true){
                        String empname = rs.getString(1);
                        String salary = rs.getString(2);
                        String mobile = rs.getString(3);

                        txtname.setText(empname);
                        txtsalary.setText(salary);
                        txtmobile.setText(mobile);
                    }else{
                        txtname.setText("");
                        txtsalary.setText("");
                        txtmobile.setText("");
                        JOptionPane.showMessageDialog(null, "Invalid employee number");

                    }
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id;
                id = empid.getText();

                try{
                    pst = con.prepareStatement("delete from company where id=?");
                    pst.setString(1,id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Deleted..");
                    table_load();

                    txtname.setText("");
                    txtsalary.setText("");
                    txtmobile.setText("");
                    txtname.requestFocus();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}

package StudentManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class NewUser extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JPasswordField txtConfirm;
	
	Connection con;
	PreparedStatement pst;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewUser() {
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(45, 66, 83, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(45, 139, 83, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirm Password");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(45, 206, 115, 35);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("User Type");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(45, 269, 115, 35);
		contentPane.add(lblNewLabel_3);
		
		txtUser = new JTextField();
		txtUser.setBounds(209, 81, 267, 26);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(209, 145, 267, 26);
		contentPane.add(txtPass);
		
		txtConfirm = new JPasswordField();
		txtConfirm.setBounds(209, 212, 267, 26);
		contentPane.add(txtConfirm);
		
		JComboBox txtUtype = new JComboBox();
		txtUtype.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtUtype.setModel(new DefaultComboBoxModel(new String[] {"User", "Admin"}));
		txtUtype.setBounds(209, 277, 267, 27);
		contentPane.add(txtUtype);
		
		JButton btn1 = new JButton("Add");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUser.getText().length()==0) {
					JOptionPane.showMessageDialog(btn1,"Please type the username" );
				}
				else if(txtPass.getText().length()==0) {
					JOptionPane.showMessageDialog(btn1,"Please type the password" );
				}
				else if(txtPass.getText().equals(txtConfirm.getText())==false) {
					JOptionPane.showMessageDialog(btn1,"Password not match" );
				}
				
				else {
					try {
						
						String username = txtUser.getText();
						String confirmpass = txtConfirm.getText();
						String usertype = txtUtype.getSelectedItem().toString();
						
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement", "root", "");
						pst = con.prepareStatement("insert into user(username,password,utype)values(?,?,?)");
						pst.setString(1, username);
						pst.setString(2, confirmpass);
						pst.setString(3, usertype);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "User Created.............");
						txtUser.setText("");
						txtPass.setText("");
						txtConfirm.setText("");
						txtUtype.setSelectedIndex(-1);
						txtUser.requestFocus();
						
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		btn1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn1.setBounds(236, 367, 95, 35);
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("Cancel");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btn2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn2.setBounds(391, 367, 85, 35);
		contentPane.add(btn2);
	}
}

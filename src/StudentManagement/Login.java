package StudentManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 714, 82);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(63, 156, 89, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(63, 206, 89, 23);
		contentPane.add(lblNewLabel_2);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtUser.setBounds(185, 150, 464, 29);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtPass.setBounds(185, 200, 464, 29);
		contentPane.add(txtPass);
		
		JButton btn2 = new JButton("Login");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if(txtUser.getText().isEmpty() || txtPass.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(btn2, "Username or password blank");
					}
					else {
						
						String username = txtUser.getText();
						String password =  txtPass.getText();
						
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement", "root", "");
						pst = con.prepareStatement("select * from user where username = ? and  password = ?");
						pst.setString(1, username);
						pst.setString(2, password);
						rs = pst.executeQuery();
						
						if(rs.next())
						{
							Main m = new Main();
							btn2.hide();
							m.setVisible(true);
							
						}
						
						else {
							JOptionPane.showMessageDialog(btn2, "username or password do not matched......");
							txtUser.setText("");
							txtPass.setText("");
							txtUser.requestFocus();
						}
						
						
					}
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		

			}
		});
		btn2.setBackground(Color.BLUE);
		btn2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn2.setBounds(383, 289, 99, 41);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("Cancel");
		btn3.setBackground(Color.RED);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn3.setBounds(560, 289, 89, 41);
		contentPane.add(btn3);
		
		JButton btn1 = new JButton("New User");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NewUser n = new NewUser();
				n.setVisible(true);
				
			}
		});
		btn1.setBackground(Color.PINK);
		btn1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn1.setBounds(199, 289, 99, 41);
		contentPane.add(btn1);
	}
}

package StudentManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField txtfirst;
	private JTextField txtlast;
	private JTextField txtnic;
	private JTextField txttelephone;
	
	Connection con;
	PreparedStatement pst;
	PreparedStatement pst1;
	PreparedStatement pst2;
	ResultSet rs;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
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
	public Registration() {
		
		courses();
		batch();
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.info);
		panel.setBounds(0, 0, 640, 79);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setBackground(Color.CYAN);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 93, 93, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(20, 128, 77, 25);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nic");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(20, 163, 66, 30);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Gender");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(20, 203, 77, 30);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Course");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(20, 250, 66, 28);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Batch");
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_5.setBounds(20, 288, 66, 25);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Telephone");
		lblNewLabel_1_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_6.setBounds(20, 331, 66, 25);
		contentPane.add(lblNewLabel_1_6);
		
		txtfirst = new JTextField();
		txtfirst.setBounds(142, 97, 293, 19);
		contentPane.add(txtfirst);
		txtfirst.setColumns(10);
		
		txtlast = new JTextField();
		txtlast.setColumns(10);
		txtlast.setBounds(142, 132, 293, 19);
		contentPane.add(txtlast);
		
		txtnic = new JTextField();
		txtnic.setColumns(10);
		txtnic.setBounds(142, 170, 293, 19);
		contentPane.add(txtnic);
		
		txttelephone = new JTextField();
		txttelephone.setColumns(10);
		txttelephone.setBounds(142, 335, 293, 19);
		contentPane.add(txttelephone);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
		rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rdbtnNewRadioButton.setBounds(142, 209, 118, 24);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rdbtnFemale.setBounds(267, 209, 118, 24);
		contentPane.add(rdbtnFemale);
		
		JComboBox txtcourse = new JComboBox();
		txtcourse.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtcourse.setBounds(147, 255, 171, 21);
		contentPane.add(txtcourse);
		
		JComboBox txtbatch = new JComboBox();
		txtbatch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtbatch.setBounds(147, 291, 171, 21);
		contentPane.add(txtbatch);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String firstname=txtfirst.getSelectedText();
					String lastname=txtlast.getSelectedText();
					String nic=txtnic.getSelectedText();
					String Gender;
					
					if(rdbtnNewRadioButton.isSelected())
					{
						Gender = "Male";
					}
					
					else {
						Gender = "Female";
						
					}
					
					String course = txtcourse.getSelectedItem().toString();
					String batch = txtbatch.getSelectedItem().toString();
					String telephone=txttelephone.getText();
					
					
					
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement", "root", "");
					
					pst = con.prepareStatement("insert into registration(firstname,lastname,nic,gender,course,batch,telephone)values(?,?,?,?,?,?,?)");
					pst.setString(1, firstname);
					pst.setString(2, lastname);
					pst.setString(3, nic);
					pst.setString(4, Gender);
					pst.setString(5, course);
					pst.setString(6, batch);
					pst.setString(7, telephone);
				
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registration Created.............");
					txtfirst.setText("");
					txtlast.setText("");
					txtnic.setText("");
					txtcourse.setSelectedIndex(-1);
					txtbatch.setSelectedIndex(-1);
					txttelephone.setText("");
					txtfirst.requestFocus();
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(423, 238, 93, 40);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel.hide();
				
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCancel.setBounds(547, 238, 93, 40);
		contentPane.add(btnCancel);
	}
	
	public void courses() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement", "root", "");
			pst1= con.prepareStatement("select * from course");
			rs=pst1.executeQuery();
			
			txtcourse.removeAllItems();
			
			while(rs.next()) {
				txtcourse.addItem(rs.getString(1));
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
				
	}
	
	public void batch() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement", "root", "");
			pst2= con.prepareStatement("select * from batch");
			rs=pst2.executeQuery();
			
			txtbatch.removeAllItems();
			
			while(rs.next()) {
				txtbatch.addItem(rs.getString(2));
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
				
	}
	
		
}

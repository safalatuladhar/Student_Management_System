package StudentManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Course extends JFrame {

	private JPanel contentPane;
	private JTextField txtBatch;
	
	Connection con;
	PreparedStatement pst;
	private JTextField txtCourse;
	private JTextArea txtDuration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Course frame = new Course();
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
	public Course() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(54, 58, 91, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Duration");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(54, 115, 91, 35);
		contentPane.add(lblNewLabel_1);
		
		txtCourse = new JTextField();
		txtCourse.setBounds(189, 67, 222, 26);
		contentPane.add(txtCourse);
		txtCourse.setColumns(10);
		
		txtDuration = new JTextArea();
		txtDuration.setBounds(189, 121, 222, 29);
		contentPane.add(txtDuration);
		
		JComboBox txtOption = new JComboBox();
		txtOption.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtOption.setModel(new DefaultComboBoxModel(new String[] {"Weeks", "Month", "Year"}));
		txtOption.setBounds(209, 277, 267, 27);
		contentPane.add(txtOption);
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String coursename = txtCourse.getText();
				String duration = txtDuration.getText();
				String durationoption = txtOption.getSelectedItem().toString();
				

				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement", "root", "");
					pst = con.prepareStatement("insert into course(coursename,duration,optionn)values(?,?,?)");
					pst.setString(1, coursename);
					pst.setString(2, duration);
					pst.setString(3, durationoption);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Course Created.............");
					txtCourse.setText("");
					txtDuration.setText("");
					txtOption.setSelectedIndex(-1);
					txtCourse.requestFocus();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(189, 233, 91, 46);
		contentPane.add(btnNewButton);
		
		JButton btn2 = new JButton("Cancel");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn2.hide();
				
				
			}
		});
		btn2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn2.setBounds(318, 233, 103, 46);
		contentPane.add(btn2);
	}
}

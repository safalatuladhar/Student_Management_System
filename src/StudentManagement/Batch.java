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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Batch extends JFrame {

	private JPanel contentPane;
	private JTextField txtBatch;
	
	Connection con;
	PreparedStatement pst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Batch frame = new Batch();
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
	public Batch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Batch Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(54, 58, 91, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Year");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(54, 115, 91, 35);
		contentPane.add(lblNewLabel_1);
		
		txtBatch = new JTextField();
		txtBatch.setBounds(189, 67, 222, 26);
		contentPane.add(txtBatch);
		txtBatch.setColumns(10);
		
		JTextArea txtYear = new JTextArea();
		txtYear.setBounds(189, 121, 222, 29);
		contentPane.add(txtYear);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String batchname = txtBatch.getText();
				String year = txtYear.getText();
				

				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement", "root", "");
					pst = con.prepareStatement("insert into batch(batchname,year)values(?,?)");
					pst.setString(1, batchname);
					pst.setString(2, year);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Batch Created.............");
					txtBatch.setText("");
					txtYear.setText("");
					txtBatch.requestFocus();
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

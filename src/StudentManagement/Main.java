package StudentManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn1 = new JButton("Course");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Course c = new Course();
				c.setVisible(true);
			}
		});
		btn1.setBackground(Color.PINK);
		btn1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn1.setBounds(57, 159, 121, 92);
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("Batch");
		btn2.setBackground(Color.PINK);
		btn2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Batch b = new Batch();
				b.setVisible(true);
			}
				
			});
		
		btn2.setBounds(231, 159, 121, 92);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("Registration");
		btn3.setBackground(Color.PINK);
		btn3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Registration r = new Registration();
				r.setVisible(true);
				
			}
		});
		btn3.setBounds(421, 159, 114, 92);
		contentPane.add(btn3);
	}
}

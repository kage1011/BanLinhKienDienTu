package ui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class JpanelTimKiem extends JPanel {

	/**
	 * Create the panel.
	 */
	public JpanelTimKiem() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 0));
		panel.setBounds(0, 0, 1200, 59);
		add(panel);
		panel.setLayout(null);
		JLabel lblTimKiem = new JLabel("QUẢN LÝ TÌM KIẾM");
		lblTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimKiem.setBounds(0, 0, 1200, 59);
		panel.add(lblTimKiem);
		lblTimKiem.setForeground(Color.WHITE);
		lblTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(29, 205, 1, 1);
		add(layeredPane);
		
		JPanel pnlTKLK = new JPanel();
		pnlTKLK.setBackground(Color.CYAN);
		pnlTKLK.setBounds(131, 117, 168, 127);
		add(pnlTKLK);
		
		JLabel lblTKLK = new JLabel("");
		lblTKLK.setIcon(new ImageIcon(JpanelTimKiem.class.getResource("/images/icons8_computer_120px_1.png")));
		pnlTKLK.add(lblTKLK);
		
		JPanel pnlTKKH = new JPanel();
		pnlTKKH.setBackground(Color.CYAN);
		pnlTKKH.setBounds(382, 117, 168, 127);
		add(pnlTKKH);
		
		JLabel lblTKKH = new JLabel("");
		pnlTKKH.add(lblTKKH);
		lblTKKH.setIcon(new ImageIcon(JpanelTimKiem.class.getResource("/images/icons8_customer_120px.png")));
		
		JPanel pnlTKNV = new JPanel();
		pnlTKNV.setBackground(Color.CYAN);
		pnlTKNV.setBounds(610, 117, 197, 127);
		add(pnlTKNV);
		
		JLabel lblTKNV = new JLabel("New label");
		pnlTKNV.add(lblTKNV);
		lblTKNV.setIcon(new ImageIcon(JpanelTimKiem.class.getResource("/images/icons8_online_support_120px.png")));
		
		JPanel pnlTKNCC = new JPanel();
		pnlTKNCC.setBackground(Color.CYAN);
		pnlTKNCC.setBounds(234, 274, 197, 127);
		
		add(pnlTKNCC);
		
		JLabel lblTKNCC = new JLabel("New label");
		pnlTKNCC.add(lblTKNCC);
		lblTKNCC.setIcon(new ImageIcon(JpanelTimKiem.class.getResource("/images/icons8_management_120px.png")));
		
		JPanel pnlTKHD = new JPanel();
		pnlTKHD.setBackground(Color.CYAN);
		pnlTKHD.setBounds(489, 274, 208, 127);
		add(pnlTKHD);
		
		JLabel lblTKHD = new JLabel("New label");
		lblTKHD.setIcon(new ImageIcon(JpanelTimKiem.class.getResource("/images/icons8_search_in_list_120px.png")));
		pnlTKHD.add(lblTKHD);

	}
}

package ui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

public class JpanelTrangChu extends JPanel {
	private JTextField txtHAnhEm;

	/**
	 * Create the panel.
	 */
	public JpanelTrangChu() {
		setLayout(null);
		
		
		JLabel lblIMG = new JLabel();
		lblIMG.setBounds(0, 0, 1210, 728);
		lblIMG.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/637390.jpg")).getImage().getScaledInstance(1200,728, Image.SCALE_SMOOTH)));
		
		add(lblIMG);
		
		txtHAnhEm = new JTextField();
		txtHAnhEm.setFont(new Font("Times New Roman", Font.PLAIN, 69));
		txtHAnhEm.setText("NHÓM 8 XIN CHÀO AE");
		txtHAnhEm.setBounds(26, 464, 730, 86);
		//add(txtHAnhEm);
		txtHAnhEm.setColumns(10);
		

	}

}

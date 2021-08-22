package main;

import java.sql.SQLException;

import conection.Conection;
import ui.Form_DangNhap;
import ui.Frm_GioiThieu;

public class Main extends Thread{

	public static void main(String[] args) throws Exception {
		int count = 6;
		try {
			Conection.getKetNoi();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Frm_GioiThieu countDown = new Frm_GioiThieu();
		countDown.setVisible(true);
		countDown.setLocationRelativeTo(null);
		for (int i = 0; i < count; i++) {
			try {
				Thread.sleep(350);
				countDown.progressBar.setValue(20);
				if(i==1)
					countDown.progressBar.setValue(40);
				if(i==2)
					countDown.progressBar.setValue(60);
				if(i==3)
					countDown.progressBar.setValue(80);
				if(i==4)
					countDown.progressBar.setValue(99);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
			countDown.setVisible(false);
			Form_DangNhap login = new Form_DangNhap();
			login.setVisible(true);
			login.setLocationRelativeTo(null);
	}

}

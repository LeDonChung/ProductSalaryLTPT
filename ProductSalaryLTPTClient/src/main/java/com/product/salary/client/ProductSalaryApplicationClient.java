package com.product.salary.client;

/**
 * @author Lê Đôn Chủng: Code giao diện
 * @author Lê Đôn Chủng: Code ....
 */

import com.product.salary.client.view.app.Application;
import com.product.salary.client.view.other.DangNhapForm;

import javax.swing.*;

public class ProductSalaryApplicationClient {

	public static void main(String args[]) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException ex) {
			java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
        // </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
            new DangNhapForm().setVisible(true);
        });
	}
}

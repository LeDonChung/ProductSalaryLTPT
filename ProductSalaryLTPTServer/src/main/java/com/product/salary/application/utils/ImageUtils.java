package com.product.salary.application.utils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageUtils {
	public static ImageIcon chooseImage(int w, int h) {
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(null);
			File f = chooser.getSelectedFile();
			String fileName = f.getAbsolutePath();
			ImageIcon imageIcon = new ImageIcon(
					new ImageIcon(fileName).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
			return imageIcon;
		} catch (Exception e) {
			return null;
		}
	}

	public static ImageIcon convertToImageIcon(byte[] image, int w, int h) {
		try {
			ImageIcon imageIcon = new ImageIcon(
					new ImageIcon(image).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
			return imageIcon;
		} catch (Exception e) {
			return null;
		}
	}

	public static byte[] convertToByteArray(Icon icon) {
		BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		icon.paintIcon(null, g2d, 0, 0);
		g2d.dispose();

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
			try {
				ImageIO.write(img, "png", ios);
				// Set a flag to indicate that the write was successful
			} finally {
				ios.close();
			}
			return baos.toByteArray();
		} catch (IOException ex) {
		}
		return null;
	}
}

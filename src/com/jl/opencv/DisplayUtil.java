package com.jl.opencv;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;

public class DisplayUtil {
	public static void imshow(String title, Mat img) {
		// Image
		BufferedImage image = imageFrom(img);

		// Icon
		ImageIcon imageIcon = new ImageIcon();
		if (image != null) {
			imageIcon.setImage(image);
		}

		// Label
		JLabel label = new JLabel();
		label.setIcon(imageIcon);

		// Frame
		JFrame frame = new JFrame();

		frame.setTitle(title);
		frame.add(label);
		frame.pack();

		// Show
		frame.setVisible(true);
	}

	private static BufferedImage imageFrom(Mat mat) {
		if (mat.empty()) {
			return null;
		}

		int type = BufferedImage.TYPE_BYTE_GRAY;

		int n = mat.channels();
		if (n == 3) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}

		int w = mat.cols();
		int h = mat.rows();

		int m = n * w * h;
		byte[] matBytes = new byte[m];

		mat.get(0, 0, matBytes); // get all the pixels

		BufferedImage image = new BufferedImage(w, h, type);
		final byte[] imageBytes = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

		System.arraycopy(matBytes, 0, imageBytes, 0, matBytes.length);

		return image;
	}
}

package com.jl.opencv;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;

public class DisplayUtil {
	public static void imshow(String title, Mat img) {

		// Icon
		ImageIcon imageIcon = new ImageIcon();
		imageIcon.setImage(imageFrom(img));

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
		int type = BufferedImage.TYPE_BYTE_GRAY;

		if (mat.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}

		int w = mat.cols();
		int h = mat.rows();

		int n = mat.channels() * w * h;
		byte[] src = new byte[n];

		mat.get(0, 0, src); // get all the pixels

		BufferedImage image = new BufferedImage(w, h, type);
		final byte[] dst = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

		System.arraycopy(src, 0, dst, 0, src.length);

		return image;
	}
}

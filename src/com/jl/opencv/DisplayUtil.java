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

	private static BufferedImage imageFrom(Mat srcImg) {
		int type = BufferedImage.TYPE_BYTE_GRAY;

		if (srcImg.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}

		int w = srcImg.cols();
		int h = srcImg.rows();

		int n = srcImg.channels() * w * h;
		byte[] src = new byte[n];

		srcImg.get(0, 0, src); // get all the pixels

		BufferedImage dstImg = new BufferedImage(w, h, type);
		final byte[] dst = ((DataBufferByte) dstImg.getRaster().getDataBuffer()).getData();

		System.arraycopy(src, 0, dst, 0, src.length);

		return dstImg;
	}
}

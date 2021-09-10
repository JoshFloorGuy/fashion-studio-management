package image;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class ImageViewer {

	ImageComponent ic;

	public ImageViewer(String path) {

	}

	public ImageViewer() {

	}

	public boolean displayImage(String image) {
		this.ic = new ImageComponent(image);

		if (ic.getImage() != null) {
			EventQueue.invokeLater(() -> {
				ImageFrame frame = new ImageFrame(ic);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			});
			return true;
		} else
			return false;
	}

	public boolean displayImage(Image image) {
		this.ic = new ImageComponent(image);
		if (image == null)
			return false;

		EventQueue.invokeLater(() -> {
			ImageFrame frame = new ImageFrame(ic);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		});
		return true;
	}
}

class ImageFrame extends JFrame {

	public static final int DEFAULT_WIDTH = 640;
	public static final int DEFAULT_HEIGHT = 480;
	private static final long serialVersionUID = 1L;
	private final JComponent COMPONENT;

	public ImageFrame() {
		this(new ImageComponent());

	}

	public ImageFrame(ImageComponent component) {
		COMPONENT = component;
		setTitle("ImageTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		add(component);
		this.setSize(component.getImage().getWidth(component), component.getImage().getHeight(component));
	}

	public JComponent getComponent() {
		return COMPONENT;
	}
}

class ImageComponent extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;

	public Image getImage() {
		return this.image;
	}

	public ImageComponent() {
		this("U:\\workspace\\boss_test.jpg");
	}

	public ImageComponent(String path) {
		try {
			File image2 = new File(path);
			image = ImageIO.read(image2);
			// this.setPreferredSize(new Dimension(image.getWidth(this),
			// image.getHeight(this)));
		} catch (IOException e) {
			System.out.println("File not found or not of image type");
		}
	}

	public ImageComponent(Image image) {
		this.image = image;
	}

	public void paintComponent(Graphics g) {
		if (image == null)
			return;

		g.drawImage(image, 0, 0, this);

		// for (int i = 0; i*imageWidth <= getWidth(); i++)
		// for(int j = 0; j*imageHeight <= getHeight();j++)
		// if(i+j>0) g.copyArea(0, 0, imageWidth, imageHeight, i*imageWidth,
		// j*imageHeight);
	}

}
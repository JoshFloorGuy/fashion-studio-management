package image;

import java.awt.Image;

public interface ImageViewerInterface {
	public Image uploadImage(String address);
	public boolean displayImage(String image);
	public boolean displayImage(Image image);
}

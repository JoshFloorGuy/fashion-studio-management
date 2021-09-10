package image;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageViewerFacade implements ImageViewerInterface{

	ImageViewer iv;
	String defaultPath;
	
	public ImageViewerFacade(String defaultPath){
		iv = new ImageViewer();
		this.defaultPath = defaultPath;
	}
	
	public ImageViewerFacade(){
		iv = new ImageViewer();
		this.defaultPath = null;
	}
	
	@Override
	public Image uploadImage(String address) {
		Image output = null;
		if(displayImage(address)){
			File temp = new File(address);
			try {
				output = ImageIO.read(temp);
			} catch (IOException e) {
				System.out.println("this should never happen");
				e.printStackTrace();
			}
		}
		
		return output;
	}

	@Override
	public boolean displayImage(String image) {
		iv.displayImage(image);
		return false;
	}

	@Override
	public boolean displayImage(Image image) {
		if(image == null)return false;
		iv.displayImage(image);
		return true;
	}

}

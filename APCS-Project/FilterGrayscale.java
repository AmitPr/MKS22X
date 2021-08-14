import java.awt.image.BufferedImage;

public class FilterGrayscale{

	public static BufferedImage apply(BufferedImage image) {
		//create an image of the same size, with color mode grayscale
		BufferedImage gray = new BufferedImage (image.getWidth(),image.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
		//draw our input onto this image, erasing the colors since this is in grayscale
		//TODO: Find out what imageobserver is, using null seems to work...
		gray.getGraphics().drawImage(image, 0, 0, null);
		return gray;
	}

}

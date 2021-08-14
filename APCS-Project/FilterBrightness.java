import java.awt.Color;
import java.awt.image.BufferedImage;

public class FilterBrightness {
	public static BufferedImage apply(BufferedImage image, float brightness) {
		BufferedImage work = image;
		final int h = work.getHeight();
		final int w = work.getWidth();
		// Iterate over all pixels
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int rgb[] = ImageEditor.rgbFromInt(image.getRGB(x, y));
				float[] hsb = Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], null);
				//keep hue and saturation, change brightness.
				work.setRGB(x, y, Color.HSBtoRGB(hsb[0], hsb[1], brightness));
			}
		}
		return work;
	}
}

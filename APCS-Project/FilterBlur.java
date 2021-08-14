import java.awt.image.BufferedImage;

public class FilterBlur {
	public static BufferedImage apply(BufferedImage image, int radius) {
		// create an image that is the blurred version
		BufferedImage work = image;
		final int h = work.getHeight();
		final int w = work.getWidth();
		// Iterate over all pixels
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				// Determine bounding box for blur
				int startY = (y - radius < 0) ? 0 : (y - radius);
				int startX = (x - radius < 0) ? 0 : (x - radius);
				int endY = (y + radius > h) ? h - startY : radius * 2;
				int endX = (x + radius > w) ? w - startX : radius * 2;
				int avgR = 0;
				int avgB = 0;
				int avgG = 0;
				int len = 0;
				for (int i = startY; i < startY + endY; i++) {
					for (int j = startX; j < startX + endX; j++) {
						// getRGB startX startY doesn't work, tried fiddling
						// with it first but it wouldn't work at all.
						int[] colors = ImageEditor.rgbFromInt(image.getRGB(j, i));
						avgR += colors[0];
						avgG += colors[1];
						avgB += colors[2];
						len++;
					}
				}
				work.setRGB(x, y, ImageEditor.intFromRGB(avgR/len, avgG/len, avgB/len));
			}
		}
		return work;
	}
}

import static java.lang.System.out;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class ImageEditor {
	public static void main(String[] args) {
		new ImageEditor(args);
	}

	public ImageEditor(String[] args) {
		// Scanner object
		Scanner s = new Scanner(System.in);
		// used for exit command
		boolean run = true;
		// Stores open images
		HashMap<String, BufferedImage> openImages = new HashMap<String, BufferedImage>();
		out.println("Welcome to the Image Editor, for help use the command \"help\" and see the readme.md");
		while (run) {
			// Read in the command
			out.print("> ");
			String inp = "";
			inp = s.nextLine().trim();
			// Check if input is blank
			if (inp != "" || inp != null) {
				// splitSpace special split function to preserve spaces in
				// quotes
				List<String> command = splitSpace(inp);
				int size = command.size();
				if (size > 0) {
					// which command?
					switch (command.get(0).toLowerCase()) {
					// open file based on input path
					case "open":
						if (size > 1) {
							String path = command.get(1);
							try {
								File f = new File(path);
								// If the file isn't an image, or doesn't exist
								if (!f.exists() || f.isDirectory()) {
									throw new FileNotFoundException();
								}
								// Will throw IOException if file is readable, null if not image.
								BufferedImage image = ImageIO.read(f);
								if (image == null){
									throw new IOException();
								}
								// inserts into the hashmap as <"filename",
								// BufferedImage>
								out.println("open: Opened file: " + f.getName() + " as " + fileName(f) + ".");
								openImages.put(fileName(f), image);
							} catch (IOException e) {
								// Tries to help user debug why the command
								// didn't work.
								if (e instanceof FileNotFoundException) {
									out.println("open: Error reading file at path: " + path
											+ " - Not Found. (Is it a directory? Did you place quotes around paths with spaces?)");
								} else {
									out.println("open: Error reading file at path: " + path
											+ " - Exception reading. (Is it an image? Do you have permission to access it?)");
								}
							}
						} else
							out.println("open:\n\tUsage: open path-to-file");
						break;
					case "save":
						if (size > 2) {
							// Check if specified image is open
							String imName = command.get(1);
							if (!openImages.containsKey(imName)) {
								out.println("save: Specified image not found, try \"list\" to show the open images");
								break;
							}
							String path = command.get(2);
							try {
								// Check if user wants to overwrite an existing
								// file with the same path
								File f = new File(path);
								if (f.exists()) {
									out.print("save: The file already exists, would you like to overwrite? (y/n) ");
									boolean done = false;
									boolean cont = false;
									while (!done) {
										String confirm = s.nextLine().toLowerCase();
										if (confirm.length() > 0) {
											char c = confirm.charAt(0);
											if (c == 'y') {
												out.println("save: Overwriting...");
												done = cont = true;
											} else if (c == 'n') {
												out.println("save: Cancelled.");
												done = true;
											} else {
												out.println("save: Unrecognized Character, please re-enter.");
											}
										}
									}
									if (!cont) {
										break;
									} else {
										f.delete();
									}
								}
								// Writes the file
								ImageIO.write(openImages.get(imName), "png", f);
								out.println("save: Saved image to file: " + path);
							} catch (IOException e) {
								out.println("save: Error saving at path: " + path + " - (Do you have permission?)");

							}
						} else
							out.println("save:\n\tUsage: save image-name path-to-file");
						break;
					case "filter":
						if (size > 1) {
							// Is the image specified open
							String imName = command.get(1);
							if (!openImages.containsKey(imName)) {
								if (imName.equalsIgnoreCase("help")){
									out.println("filter: List of Filters:"
											+ "\n\tBrightness: Increase and decrease the brightness of your image\n\t\tArguments: \"filter <image> brightness <brightness:float (0-100)>\""
											+ "\n\tSaturation: Increase and decrease the saturation of the colros in your image\n\t\tArguments: \"filter <image> saturation <saturation:float (0-100)>\""
											+ "\n\tBlur: Applies blur to your image by having each pixel take the average color of pixels in radius specified.\n\t\tArguments: \"filter <image> blur <radius:integer (Default: 2) Larger numbers are slower>\""
											+ "\n\tGrayscale: Converts Image to Grayscale");
								}else{
									out.println("filter: Specified image not found, try \"list\" to show the open images");
								}
								break;
							}
							// Which filter is being used.
							String filter = command.get(2);
							switch (filter.toLowerCase()) {
							case "grayscale":
								openImages.replace(imName, FilterGrayscale.apply(openImages.get(imName)));
								out.println("filter: Grayscale Applied.");
								break;
							case "blur":
								int radius = 2;
								if (size > 3) {
									try {
										radius = Integer.parseInt(command.get(3));
									} catch (NumberFormatException e) {
										out.println("filter: Blur - Error, radius must be an integer.");
										break;
									}
								}
								openImages.replace(imName, FilterBlur.apply(openImages.get(imName), radius));
								out.println("filter: Blur Applied.");
								break;
							case "brightness":
								if (size > 3) {
									try {
										float brightness = Float.parseFloat(command.get(3));
										if (brightness < 0 || brightness > 100) {
											out.println(
													"filter: Brightness - Error, \"brightness:float\" must be between 0 and 100.");
										} else {
											openImages.replace(imName,
													FilterBrightness.apply(openImages.get(imName), brightness / 100));
											out.println("filter: Brightness Applied.");
										}
									} catch (NumberFormatException e) {
										out.println(
												"filter: Brightness - Error, brightness must be a float between 0 and 100.");
										break;
									}
								} else {
									out.println(
											"filter: Brightness - Error, Requires argument: \"brightness:float (0-100)\".");
								}
								break;
							case "saturation":
								if (size > 3) {
									try {
										float sat = Float.parseFloat(command.get(3));
										if (sat < 0 || sat > 100) {
											out.println(
													"filter: Saturation - Error, \"saturation:float\" must be between 0 and 100.");
										} else {
											openImages.replace(imName,
													FilterSaturation.apply(openImages.get(imName), sat / 100));
											out.println("filter: Saturation Applied.");
										}
									} catch (NumberFormatException e) {
										out.println(
												"filter: Saturation - Error, saturation must be a float between 0 and 100.");
										break;
									}
								} else {
									out.println(
											"filter: Saturation - Error, Requires argument: \"saturation:float (0-100)\".");
								}
								break;
							default:
								out.println("filter: Specified filter not found. For a list of filters use \"filter help\"");
								break;
							}
						} else
							out.println(
									"filter:\n\tUsage: filter image-name filter-name filter-arguments\n\tFor a list of filters use \"filter help\"");
						break;
					case "list":
						// Lists all the open images nicely
						out.println("list: Currently open images (" + openImages.size() + ")");
						int i = 1;
						for (String en : openImages.keySet()) {
							out.println("\t" + i + ": " + en);
						}
						break;
					case "help":
						// Lists all the commands
						out.println("help: Image Editor's Commands:\n"
								+ "\topen <path-to-file>: Open an image to edit."
								+ "\n\tsave <image> <path-to-destination>: Saves an open file"
								+ "\n\tfilter <image> <filter> <filter-arguments: Filter an Image with the specified filter. See \"filter help\" for more information."
								+ "\n\tlist: Shows the currently open images."
								+ "\n\texit: Exits program.");
						break;
					case "exit":
						// Exits the program
						out.print("exit: Exiting....");
						run = false;
						break;
					default:
						out.println("Error: unknown command, try \"help\" for help on commands.");
						break;
					}
				}
			} else {

			}
		}
		s.close();
		out.println(" DONE");
	}

	// Splits a string into a list, I could have used string.split but that
	// would split a path (e.g. "/users/Amit Prasad/image.png") into multiple
	// arguments (e.g. {"/users/Amit, Prasad/image.png"})
	// TODO learn regex pattern matching to make this unneeded.
	public List<String> splitSpace(String toSplit) {
		boolean inQuotes = false;
		List<String> splitted = new ArrayList<String>();
		int lastSplit = 0;
		for (int i = 0; i < toSplit.length(); i++) {
			char c = toSplit.charAt(i);
			if (c == '\"') {
				inQuotes = !inQuotes;
			}
			if (c == ' ' && !inQuotes) {
				splitted.add(toSplit.substring(lastSplit, i).replace("\"", ""));
				lastSplit = i + 1;
			}
		}
		if (!(lastSplit >= toSplit.length())) {
			splitted.add(toSplit.substring(lastSplit, toSplit.length()).replace("\"", ""));
		}
		return splitted;
	}

	/*
	 * Utility Functions
	 */
	public String fileName(File f) {
		String s = f.getName();
		int index = s.lastIndexOf('.');
		return index != -1 ? s.substring(0, index) : s;
	}

	public static int[] rgbFromInt(int x) {
		// Thanks Stack Overflow for explaining integer RGB values and
		// separating them.
		int[] rgb = new int[3];
		int blue = x & 0xff;
		int green = (x & 0xff00) >> 8;
		int red = (x & 0xff0000) >> 16;
		rgb[0] = red;
		rgb[1] = green;
		rgb[2] = blue;
		return rgb;
	}

	public static int intFromRGB(int r, int g, int b) {
		// Could use java Color object, but it is really REALLY slow...
		// https://stackoverflow.com/questions/4801366/convert-rgb-values-to-integer
		int rgb = r;
		rgb = (rgb << 8) + g;
		rgb = (rgb << 8) + b;
		return rgb;
	}
}
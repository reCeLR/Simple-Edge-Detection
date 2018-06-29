import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Main {

	public static void main(String[] args) throws IOException {
	
		try {
			//input file
			
			String filename = "Images/girl.png";
			File inputFile = new File(filename);
			BufferedImage inputImage = ImageIO.read(inputFile);
		
			//Colors to change pixels too
			Color black = new Color(0,0,0);
			int myBlack = black.getRGB();
			
			Color white = new Color(255,255,255);
			int myWhite = white.getRGB();
		
			//current pixel intensity
			double currentInt;
			//next pixel intensity
			double nextInt;
			
			//some threshhold ive been throwing random numbers into
			double threshhold = 20;
			
			
			/*
			 * go through every pixel, adds up RBG values to get intensity, if the difference of those values
			 * are under the threshhold, assumes not to be and edge, so black
			 * 
			 * if values are over the threshhold, assumed to be an edge, so white (or greyscale depending on if it wants to fucking work)
			 */
			
			
			for(int y = 0; y < inputImage.getHeight(); y++) {
				for(int x = 0; x < inputImage.getWidth()-1; x++) {
					
					int currentPixel = inputImage.getRGB(x,y);
			
					Color currentPixelColor = new Color(currentPixel);
					
					int nextPixel = inputImage.getRGB(x+1,y);
					Color nextPixelColor = new Color(nextPixel);
					
					currentInt = (currentPixelColor.getRed() + currentPixelColor.getGreen() + currentPixelColor.getBlue());
					nextInt = (nextPixelColor.getRed() + nextPixelColor.getGreen() + nextPixelColor.getBlue());
					
					if(Math.abs(currentInt - nextInt) <= threshhold){
						inputImage.setRGB(x,y,0);
					}else {
						//work out grayScale value
						
						int r = currentPixelColor.getRed();
						int g = currentPixelColor.getGreen();
						int b = currentPixelColor.getBlue();
						
						int greyScale = (int) (0.21*r + 0.72*g + 0.07*b);
						
						
						inputImage.setRGB(x,y, greyScale);
					}

				}
			}
			//write to new image
			File outputfile = new File("Images/editGirlBlack.png");
	        ImageIO.write(inputImage, "png", outputfile);
			System.out.println("done");
		
		} catch (IOException e) { //error case
			System.out.println(e);
		}
		
	}
	

}



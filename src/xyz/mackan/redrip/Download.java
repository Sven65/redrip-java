package xyz.mackan.redrip;

import java.io.File;
/*import java.io.FileWriter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;*/


public class Download {

	public Download(){
		
	}
	
	public void createFolderIfNotExist(String filePath) throws Exception{
		File file = new File(filePath);
		file.getParentFile().mkdirs();
	}
	
	public void downloadFile(String imageURL, String savePath) throws Exception{
		createFolderIfNotExist(savePath);
		System.out.println(imageURL);
		/*BufferedImage image = null;
        try{

            URL url = new URL(imageURL);
            image = ImageIO.read(url);

            ImageIO.write(image, "jpg",new File(""));
            ImageIO.write(image, "gif",new File("C:\\out.gif"));
            ImageIO.write(image, "png",new File("C:\\out.png"));

        } catch (IOException e) {
        	e.printStackTrace();
        }
        System.out.println("Done");*/
	}
}

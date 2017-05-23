package xyz.mackan.redrip;

import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

import javafx.scene.control.TextArea;

public class Download {

	public Download(){
		
	}
	
	public void createFolderIfNotExist(String filePath, TextArea logArea) throws Exception{
		File file = new File(filePath);
		file.getParentFile().mkdirs();
		logArea.appendText("\nCreated directory "+file.getParentFile());
	}
	
	public void downloadFile(String imageURL, String savePath, TextArea logArea) throws Exception{
		createFolderIfNotExist(savePath, logArea);
		System.out.println(imageURL);
		BufferedImage image = null;
        try{
        	
        	StringHelper SH = new StringHelper();

            URL url = new URL(imageURL);
            image = ImageIO.read(url);

            ImageIO.write(image, SH.getExtension(imageURL), new File(savePath));
            
            logArea.appendText("\nDownloading image "+imageURL+"...");

        } catch (IOException e) {
        	e.printStackTrace();
        }
        logArea.appendText("Done");
        logArea.appendText("\nSaved to "+savePath);
	}
}

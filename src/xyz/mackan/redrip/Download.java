package xyz.mackan.redrip;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javafx.scene.control.TextArea;

public class Download {

	public Download(){
		
	}
	
	/**
	 * Creates a folder if it doesn't exist
	 * @param filePath
	 * @param logArea
	 * @throws Exception
	 */
	public void createFolderIfNotExist(String filePath, TextArea logArea) throws Exception{
		File file = new File(filePath);
		file.getParentFile().mkdirs();
		logArea.appendText("\nCreated directory "+file.getParentFile());
	}
	
	/**
	 * Downloads a file from URL
	 * @param imageURL The URL to download
	 * @param savePath Where to save it
	 * @param logArea The main windows GUI area
	 * @throws Exception
	 */
	public void downloadFile(String imageURL, String savePath, TextArea logArea) throws Exception{
		createFolderIfNotExist(savePath, logArea);
		System.out.println(imageURL);
		
		URL website = new URL(imageURL);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream(savePath);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		
		System.out.println("\nDownloading image "+imageURL+"...");
        logArea.appendText("\nDownloading image "+imageURL+"...");

        logArea.appendText("Done");
        logArea.appendText("\nSaved to "+savePath);
	}
}

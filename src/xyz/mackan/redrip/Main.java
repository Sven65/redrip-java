package xyz.mackan.redrip;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private BorderPane rootLayout;
	private Stage primaryStage;
	
	public void initRootLayout(){
		try{
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("view/Root.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			System.out.println(scene);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void showApplication(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Main.fxml"));
			AnchorPane mainLayout = (AnchorPane) loader.load();
			rootLayout.setCenter(mainLayout);
			this.rootLayout.setCenter(mainLayout);
			
			//MainController controller = loader.getController();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("RedRip");
		initRootLayout();
		showApplication();
	}
	
	public Stage getPrimaryStage() {
        return primaryStage;
    }

	public static void main(String[] args) {
		launch(args);
	}
}

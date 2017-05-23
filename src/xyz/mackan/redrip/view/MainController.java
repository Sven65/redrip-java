package xyz.mackan.redrip.view;

import xyz.mackan.redrip.Download;
import xyz.mackan.redrip.Reddit;
import xyz.mackan.redrip.StringHelper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class MainController {
	@FXML private TextField subreddit;
	@FXML private TextField lastpost;
	@FXML private TextField amountField;
	@FXML private CheckBox filetypeJPG;
	@FXML private CheckBox filetypePNG;
	@FXML private CheckBox filetypeGIF;
	@FXML private CheckBox filetypeGIFV;
	@FXML private CheckBox filetypeMP4;
	@FXML private CheckBox filetypeWEBM;
	@FXML private CheckBox filetypeJPEG;
	@FXML private ChoiceBox<String> sortbox;
	@FXML private TextArea logarea;
	
	private ArrayList<CheckBox> extensionBoxes;
	
	private String reddit;
	private int amount = 50;
	private String sort = "hot";
	private String after;
	
	public MainController(){
		
	}
	
	private ArrayList<String> getExtensions(){
		ArrayList<String> exts = new ArrayList<String>();
		
		for(CheckBox extensionBox : extensionBoxes){
			if(extensionBox.isSelected()){
				String filetype = extensionBox.getId();
				filetype = filetype.replace("filetype", "");
				exts.add(filetype.toLowerCase());
			}
		}
		
		return exts;
	}
	
	@FXML
    private void initialize(){	
		String[] sortValues = new String[]{"hot", "new", "rising", "controversial", "top", "guilded"};
		sortbox.setItems(FXCollections.observableArrayList(sortValues));
		
		sortbox.setTooltip(new Tooltip("What to sort the submissions by"));
	    sortbox.setValue("hot");
	    
	    extensionBoxes = new ArrayList<CheckBox>();
	    extensionBoxes.addAll(Arrays.asList(filetypeJPG, filetypePNG, filetypeGIF, filetypeGIFV, filetypeMP4, filetypeWEBM, filetypeJPEG));
    }
	
	private String getReddit(String input){
		String pattern = "/r/([^\\s/]+)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(input);
		if(m.find()){
			return m.group(1);
		}else{
			return null;
		}
	}
	
	private void handleEverything(){
		this.reddit = subreddit.getText();
		String amountString = amountField.getText();
		if(amountString == null){
			amountString = "";
		}
		if(amountString.length() <= 0){
			amountString = "50";
		}
		this.amount = Integer.parseInt(amountString);
		
		this.reddit = getReddit(this.reddit);
		if(this.reddit == null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Invalid reddit");
			alert.setContentText("Please specify a valid subreddit");
			alert.showAndWait();
		}else{
			Reddit r = new Reddit();
			try {
				JSONObject jsonObj = r.getRedditData(this.reddit, this.amount, this.sort, this.after, this.logarea);
				JSONObject data = (JSONObject) jsonObj.get("data");
				JSONArray submissions = (JSONArray) data.get("children");
				
				
				
				//System.out.println(submissions.toJSONString());
				
				StringHelper SH = new StringHelper();
				ArrayList<String> exts = this.getExtensions();
				
				Download downloader = new Download();
				
				for(int i=0;i<submissions.size();i++){
					JSONObject current = (JSONObject) submissions.get(i);
					JSONObject currentSubmission = (JSONObject) current.get("data");
					Boolean isSelf = (Boolean) currentSubmission.get("is_self");
					String url = (String) currentSubmission.get("url");
					if(!isSelf){
						if(SH.isDirectLink(url, exts)){
							this.logarea.appendText(String.format("\nFound file of type %s at url %s", SH.getExtension(url), url));
							downloader.downloadFile(url, String.format("%s/%s", this.reddit, SH.getFilename(url)), this.logarea);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void handleKeyDown(KeyEvent e){
		if(e.getCode().toString().equals("ENTER")){
			handleEverything();
		}
	}
	
	@FXML
	private void handleGoButton(){
		handleEverything();
	}
	
}

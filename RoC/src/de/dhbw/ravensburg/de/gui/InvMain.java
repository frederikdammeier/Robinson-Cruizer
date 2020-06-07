package de.dhbw.ravensburg.de.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class InvMain  extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bPane = new BorderPane();
		Label inv = new Label();
		Button showinv = new Button("show");
		Button doButton = new Button("select");
		TextField function = new TextField("eat/drop");
		TextField itemsfield= new TextField("name of the Item");
		
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(function, itemsfield, doButton);
		
		
		
		bPane.setTop(showinv);
		bPane.setCenter(inv);
		bPane.setBottom(hbox);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		Scene scene  = new Scene(bPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        
		
	}
	
	public void main(String[] args) {
		launch();
	}

}

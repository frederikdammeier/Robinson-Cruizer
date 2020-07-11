package de.dhbw.ravensburg.de.gui;

import java.util.ArrayList;

import de.dhbw.ravensburg.zuul.Difficulty;
import de.dhbw.ravensburg.zuul.Game;
import de.dhbw.ravensburg.zuul.Inventory;
import de.dhbw.ravensburg.zuul.item.Item;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	
	private Difficulty difficulty;
	private Game game;
	private Thread gameThread;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		game = new Game(Difficulty.EASY);
		gameThread = new Thread(game, "game");
        gameThread.start();
		
		BorderPane bPane = new BorderPane();
		Label inv = new Label();
		
		
		
		 ObservableList<Inventory> inventory  = FXCollections.observableArrayList(game.getPlayer().getInventory());
//		
//		
//		HBox hbox = new HBox();
//		hbox.getChildren().addAll(function, itemsfield, doButton);
//		
//		
//		
//		bPane.setTop(showinv);
//		bPane.setCenter(inv);
//		bPane.setBottom(hbox);
//		
//
//		
//		Scene scene  = new Scene(bPane);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        
		
	}
	
	public void main(String[] args) {
		launch();
	}

}

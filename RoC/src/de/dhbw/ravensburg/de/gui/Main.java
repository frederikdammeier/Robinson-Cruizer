package de.dhbw.ravensburg.de.gui;



import de.dhbw.ravensburg.zuul.Difficulty;
import de.dhbw.ravensburg.zuul.Game;
import de.dhbw.ravensburg.zuul.Inventory;
import de.dhbw.ravensburg.zuul.Player;
import de.dhbw.ravensburg.zuul.item.Food;
import de.dhbw.ravensburg.zuul.item.Item;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

public class Main extends Application {

	private Difficulty difficulty;
	private Game game;
	private Thread gameThread;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start( Stage primaryStage) throws Exception {
		 final Stage welcome = primaryStage;
		/** 
		 * implements all Layers and Controlls
		 */
		StackPane root= new StackPane(); 
		
		//image
		Image image1 = new Image("/insel2.jpg", true);
		ImageView imgView = new ImageView();
		
        imgView.setImage(image1);
        imgView.setFitWidth(root.getWidth());
        imgView.setFitHeight(root.getHeight());
       
        
        //labels 
		Label welcomeLabel = new Label();
		Label infoLabel = new Label();
		Label label = new Label("by Frederik Dammeier, Philipp Schneider and Moritz Link");
		//button
		Button goSetting  = new Button("go to Setting");
		
		
		/**
		 * defines the text from the labels
		 */
		welcomeLabel.setText("Welcome to the game:  Robinson Cruizer");
		welcomeLabel.setFont(new Font("Arial",  20));
		welcomeLabel.setTextFill(Color.BLACK);
		welcomeLabel.setPadding(new Insets(20,0,0,0));
		//infoLabel.setText("Robinson Cruizer is an adventure game, developed /br by Frederik Dammaier,/n Philipp Schneider /n and Moritz Link");
		label.setPadding(new Insets(50,0,0,0));
		 root.getChildren().addAll(imgView, welcomeLabel, goSetting, label); // , label
		 /**
		 * Defines the position from roots children
		 */	
		root.setAlignment(welcomeLabel, Pos.TOP_CENTER);
		root.setAlignment(label, Pos.TOP_CENTER);
		
		root.setAlignment(goSetting, Pos.BOTTOM_RIGHT);
		

		
		final Scene welcomeScene = new Scene(root, 400, 400);
		welcome.setScene(welcomeScene);
		welcome.show();
		
		
		
//###############################################################################
		
		
		StackPane level = new StackPane();
		
		//GridPane level = new GridPane();
		
		//AnchorPane level = new AnchorPane();
		
		Image img = new Image("/jungle.jpg", true);
		ImageView imgView2 = new ImageView();
		imgView2.setImage(img);
	    imgView2.setFitWidth(level.getWidth());
	    imgView2.setFitHeight(level.getHeight());
		
		
		//BorderPane bp = new BorderPane();
		
		VBox box = new VBox();
		
		
		ToggleGroup group = new ToggleGroup();

		final RadioButton beasy = new RadioButton("Easy");
		final RadioButton bmedium = new RadioButton("Medium");
		final RadioButton bhard = new RadioButton("Hard");
		
		Button goBack = new Button("go back");
		Button goGame = new Button("start the Game");
		Label description = new Label("please choose your level");
		
		
		/**
		 * sets the colors and the text type
		 */
		description.setFont(new Font("Serif",  24));
		description.setTextFill(Color.WHITE);
		
		beasy.setFont(new Font("Serif",  24));
		bmedium.setFont(new Font("Serif",  24));		
		bhard.setFont(new Font("Serif",  24));
		
		beasy.setTextFill(Color.WHITE);
		bmedium.setTextFill(Color.WHITE);
		bhard.setTextFill(Color.WHITE);
		
		/**
		 * build a group with all Radiobuttons
		 */
		beasy.setToggleGroup(group);
		bmedium.setToggleGroup(group);
		bhard.setToggleGroup(group);
		
		// if nothing is selected, system selects  button beasy
		beasy.setSelected(true);
	
		box.getChildren().addAll(beasy, bmedium, bhard ); // description, goBack, goGame
		
//		level.setTopAnchor(box,  100.0);
//		level.setLeftAnchor(box, 150.0);
//		level.setRightAnchor(box, 150.0);
		
	
		//level.getChildren().add(box);
		
		
		
//		bp.setTop(description);
//		bp.setBottom(goBack);
//		bp.setCenter(box);
//		level.getChildren().add(bp);
		
		box.setPadding(new Insets(120,20,20,135));
		level.getChildren().addAll(imgView2,box, description, goBack, goGame);
		
//		level.add(box, 4, 4);
//		level.add(description, 0, 0);
//		level.add(goBack, 1, 1);
//		level.add(goGame, 4, 3);
		
	//	bp.setTop(description);
		
		
		level.setAlignment(description, Pos.TOP_CENTER);
		
	//	bp.setBottom(goBack);
		level.setAlignment(goBack, Pos.BOTTOM_LEFT);
		
	//	c
		level.setAlignment(box, Pos.BOTTOM_CENTER);
		
		
		level.setAlignment(goGame, Pos.BOTTOM_RIGHT);
		
	//	level.getChildren().add(bp);
		
	//	level.setAlignment(bp, Pos.CENTER);
		
		final Scene levelScene = new Scene(level, 400, 400);
		
		/**
		 * Defines the action from the goBack button.
		 * Here you will switch to another scene. 
		 */
		goBack.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
					
			@Override
			public void handle(ActionEvent actionEvent) {
				welcome.setScene(welcomeScene);
				welcome.show();
			}
			
		});
		
		/**
		 * Defines the action from the goSetting button.
		 * Here you will switch to another scene. 
		 */
		goSetting.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
					
			@Override
			public void handle(ActionEvent actionEvent) {
				
				welcome.setScene(levelScene);
				welcome.show();
			}
			
		});
		
		
		/**
		 * Defines the action from the goGame button.
		 * Here you will switch to another scene. 
		 */
		goGame.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
					
			@Override
			public void handle(ActionEvent actionEvent) {
				
				if(beasy.isSelected()) {
					difficulty =Difficulty.EASY;
				}
				else if(bmedium.isSelected()) {
					difficulty =Difficulty.MEDIUM;
				}
				else if(bhard.isSelected()) {
					difficulty =Difficulty.HARD;
				}
				else {
					System.out.println("You level will be easy");
				}
				game = new Game(difficulty);
				System.out.println(difficulty.getInventoryCapacity()); // geht
				
//				welcome.setScene(); // Freddis Scene muss hier rein
//				welcome.show();
				gameThread = new Thread(game, "game");
		        gameThread.start();
			}
		});
		
		
	}
	//################################################################################################	

	
		

	
	@Override
    public void stop() {
        game.endGame();
    }

} 

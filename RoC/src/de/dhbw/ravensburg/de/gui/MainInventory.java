package de.dhbw.ravensburg.de.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.JTextArea;

import de.dhbw.ravensburg.zuul.Command;
import de.dhbw.ravensburg.zuul.Difficulty;
import de.dhbw.ravensburg.zuul.Game;
import de.dhbw.ravensburg.zuul.Inventory;
import de.dhbw.ravensburg.zuul.Player;
import de.dhbw.ravensburg.zuul.item.Item;



public class MainInventory extends Application{

	Label inv ;
	Button showinv ;
	Button doButton ;
	TextField function;
	 TextField itemsfield;
	
	
	
	
	private Difficulty difficulty;
	private Game game;
	private Thread gameThread;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
  
        game = new Game(Difficulty.EASY);
		gameThread = new Thread(game, "game");
        gameThread.start();
      
		
		BorderPane bPane = new BorderPane();
		final Label inv = new Label();
		Button showinv = new Button("show");
		Button doButton = new Button("select");
		final TextField function = new TextField();
		final TextField itemsfield= new TextField();
		itemsfield.setPromptText("name of the Item");
		function.setPromptText("eat/drop");
		
		
		String output = game.getPlayer().getInventory().getContentsAsString();
		inv.setText(output);
		
		HBox hbox = new HBox();
//		bPane.setAlignment(hbox, Pos.BOTTOM_CENTER);
		hbox.getChildren().addAll(function, itemsfield, doButton);
		
	
		
		
		
		
		bPane.setTop(showinv);
		bPane.setCenter(inv);
		bPane.setBottom(hbox);
		
		
		//hier kommen jetzt die ActionHandler
		//########################################################################################
		
		
		/**
		 * this Handler handels the action from the Button showinv
		 * if the player presses the button the label gets the actual Inventory from the player
		 */
		showinv.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent actionEvent) {
				String output = game.getPlayer().getInventory().getContentsAsString();
				inv.setText(output);
			}
	
		});
		
		/**
		 *  this Handler handels the action from the Button doButton
		 *  if the player presses the button the input from both textfields will be selected and checked out. 
		 */	
		doButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent actionEvent) {
				String items = itemsfield.getText();
				String func = function.getText();
				

				
			
				if(func.equals("eat")) {
					
					 

					if(items.equals("Apple") || items.equals("Meat") || items.equals("Banana") || items.equals("Cocunot") ||items.equals("Bread") ||items.equals("Mushroom") || items.equals("MagicMushroom")) {
						Item eatItem = game.getPlayer().getInventory().getItemByName(items);
						
						int eat = getEat(items);

						
						System.out.println("remove Item" + items);	
						game.getPlayer().getInventory().removeItem(eatItem);
						game.getPlayer().eat(eat);
						
						
					}
					
					// wenn es kein Food Item ist
					else {
						System.out.println("you can't eat this item");
					}
					
				
				}
				
				
				// Abfrage bei Drop
				else if(func.equals("drop")) {
					
					inputDrop(items);	
								
				}
				
				else {
					System.out.println("You must write drop or eat");
				}
				
				
				String output = game.getPlayer().getInventory().getContentsAsString();
				inv.setText(output);
				
				
				
			}
	
		});
		
		
		
		
		
		
		
		//##############################################################################################
		
		
		
		Scene scene  = new Scene(bPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        
		
	}
	
	
	
	
	public void inputDrop(String item) {
		
		if(game.getPlayer().getInventory().containsItem(item)){
			Item dropItem = game.getPlayer().getInventory().getItemByName(item);
			String name = dropItem.getName();
			Command commanddrop = new Command("drop", name);
//			String commanddrop = "drop"+name ;
			game.dropItem(commanddrop);
		}
		
		else {
			System.out.println("Item not found, chose a Item from the Inventory");
		}
			
			
		
	}

	public int getEat(String item) { // Item item
		int amount ;
		System.out.println(item);
		
		
		
		
		if(item.equals("Apple")  ) {
			amount = 20;
		}
		else if(item.equals("Meat") ) {
			amount = 50;
		}
		
		else if(item.equals("Banana")) {
			amount = 15;
		}
		else if(item.equals("Cocunot") ) {
			amount = 30;	
		}
		else if(item.equals("Bread") ) {
			amount = 50;
		}
		else if(item.equals("Mushroom") ) {
			amount = 10;
		}
		else if(item.equals("MagicMushroom")) {
			amount = 100;
		}
		else {
			
			System.out.println("not known Food");
			amount = 0;
		}
		
		return amount;
		
	}
	
	
	
	
	
	
	@Override
    public void stop() {
        game.endGame();
    }
}

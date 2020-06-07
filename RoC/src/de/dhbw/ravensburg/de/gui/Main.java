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
	private boolean clicked;
	private String name;
	private HashMap<String, RadioButton> hm; // Button
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start( Stage primaryStage) throws Exception {
		 final Stage welcome = primaryStage;
		/** 
		 * implements all Layers and Controlls
		 */
//		StackPane root= new StackPane(); 
//		
//		//image
//		Image image1 = new Image("/insel2.jpg", true);
//		ImageView imgView = new ImageView();
//		
//        imgView.setImage(image1);
//        imgView.setFitWidth(root.getWidth());
//        imgView.setFitHeight(root.getHeight());
//       
//        
//        //labels 
//		Label welcomeLabel = new Label();
//		Label infoLabel = new Label();
//		Label label = new Label("by Frederik Dammeier, Philipp Schneider and Moritz Link");
//		//button
//		Button goSetting  = new Button("go to Setting");
//		
//		
//		/**
//		 * defines the text from the labels
//		 */
//		welcomeLabel.setText("Welcome to the game:  Robinson Cruizer");
//		welcomeLabel.setFont(new Font("Arial",  20));
//		welcomeLabel.setTextFill(Color.BLACK);
//		welcomeLabel.setPadding(new Insets(20,0,0,0));
//		//infoLabel.setText("Robinson Cruizer is an adventure game, developed /br by Frederik Dammaier,/n Philipp Schneider /n and Moritz Link");
//		label.setPadding(new Insets(50,0,0,0));
//		 root.getChildren().addAll(imgView, welcomeLabel, goSetting, label); // , label
//		 /**
//		 * Defines the position from roots children
//		 */	
//		root.setAlignment(welcomeLabel, Pos.TOP_CENTER);
//		root.setAlignment(label, Pos.TOP_CENTER);
//		
//		root.setAlignment(goSetting, Pos.BOTTOM_RIGHT);
//		
//
//		
//		final Scene welcomeScene = new Scene(root, 400, 400);
//		welcome.setScene(welcomeScene);
//		welcome.show();
//		
//		
//		
////###############################################################################
//		
//		
//		StackPane level = new StackPane();
//		
//		//GridPane level = new GridPane();
//		
//		//AnchorPane level = new AnchorPane();
//		
//		Image img = new Image("/jungle.jpg", true);
//		ImageView imgView2 = new ImageView();
//		imgView2.setImage(img);
//	    imgView2.setFitWidth(level.getWidth());
//	    imgView2.setFitHeight(level.getHeight());
//		
//		
//		//BorderPane bp = new BorderPane();
//		
//		VBox box = new VBox();
//		
//		
//		ToggleGroup group = new ToggleGroup();
//
//		final RadioButton beasy = new RadioButton("Easy");
//		final RadioButton bmedium = new RadioButton("Medium");
//		final RadioButton bhard = new RadioButton("Hard");
//		
//		Button goBack = new Button("go back");
//		Button goGame = new Button("start the Game");
//		Label description = new Label("please choose your level");
//		
//		
//		/**
//		 * sets the colors and the text type
//		 */
//		description.setFont(new Font("Serif",  24));
//		description.setTextFill(Color.WHITE);
//		
//		beasy.setFont(new Font("Serif",  24));
//		bmedium.setFont(new Font("Serif",  24));		
//		bhard.setFont(new Font("Serif",  24));
//		
//		beasy.setTextFill(Color.WHITE);
//		bmedium.setTextFill(Color.WHITE);
//		bhard.setTextFill(Color.WHITE);
//		
//		/**
//		 * build a group with all Radiobuttons
//		 */
//		beasy.setToggleGroup(group);
//		bmedium.setToggleGroup(group);
//		bhard.setToggleGroup(group);
//		
//		// if nothing is selected, system selects  button beasy
//		beasy.setSelected(true);
//	
//		box.getChildren().addAll(beasy, bmedium, bhard ); // description, goBack, goGame
//		
////		level.setTopAnchor(box,  100.0);
////		level.setLeftAnchor(box, 150.0);
////		level.setRightAnchor(box, 150.0);
//		
//	
//		//level.getChildren().add(box);
//		
//		
//		
////		bp.setTop(description);
////		bp.setBottom(goBack);
////		bp.setCenter(box);
////		level.getChildren().add(bp);
//		
//		box.setPadding(new Insets(120,20,20,135));
//		level.getChildren().addAll(imgView2,box, description, goBack, goGame);
//		
////		level.add(box, 4, 4);
////		level.add(description, 0, 0);
////		level.add(goBack, 1, 1);
////		level.add(goGame, 4, 3);
//		
//	//	bp.setTop(description);
//		
//		
//		level.setAlignment(description, Pos.TOP_CENTER);
//		
//	//	bp.setBottom(goBack);
//		level.setAlignment(goBack, Pos.BOTTOM_LEFT);
//		
//	//	c
//		level.setAlignment(box, Pos.BOTTOM_CENTER);
//		
//		
//		level.setAlignment(goGame, Pos.BOTTOM_RIGHT);
//		
//	//	level.getChildren().add(bp);
//		
//	//	level.setAlignment(bp, Pos.CENTER);
//		
//		final Scene levelScene = new Scene(level, 400, 400);
//		
//		/**
//		 * Defines the action from the goBack button.
//		 * Here you will switch to another scene. 
//		 */
//		goBack.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
//					
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				welcome.setScene(welcomeScene);
//				welcome.show();
//			}
//			
//		});
//		
//		/**
//		 * Defines the action from the goSetting button.
//		 * Here you will switch to another scene. 
//		 */
//		goSetting.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
//					
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				
//				welcome.setScene(levelScene);
//				welcome.show();
//			}
//			
//		});
//		
//		
//		/**
//		 * Defines the action from the goGame button.
//		 * Here you will switch to another scene. 
//		 */
//		goGame.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
//					
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				
//				if(beasy.isSelected()) {
//					difficulty =Difficulty.EASY;
//				}
//				else if(bmedium.isSelected()) {
//					difficulty =Difficulty.MEDIUM;
//				}
//				else if(bhard.isSelected()) {
//					difficulty =Difficulty.HARD;
//				}
//				else {
//					System.out.println("You level will be easy");
//				}
//				game = new Game(difficulty);
//				System.out.println(difficulty.getInventoryCapacity()); // geht
//				
////				welcome.setScene(); // Freddis Scene muss hier rein
////				welcome.show();
//			}
//		});
		
		
	//################################################################################################	

	
		
//		 StackPane sp = new StackPane();
		
		GridPane gp = new GridPane();
		
//		Button item = new Button("test");
//		Button b2 = new Button("b2");
//		gp.add(b2, 1, 1);
//		gp.add(item, 1, 2);
		
//		TableView tv = new TableView();
//		TableColumn items = new TableColumn("Items");
//		TableColumn number= new TableColumn("number");
//		TableColumn button= new TableColumn("image");
		
		
//		Player player = game.getPlayer();
	
		game = new Game(Difficulty.EASY);
		gameThread = new Thread(game, "game");
        gameThread.start();
		
		
		
//	int i = game.getPlayer().getInventory().getNumberOfItemsInInventory();
//	String name = game.getPlayer().getInventory().getNamefromList(i);
//       Button bitem;
//      RadioButton bitem;
//		for(int i = 1; i <= game.getPlayer().getInventory().getNumberOfItemsInInventory(); i++) {
//			
//			
//			String name = game.getPlayer().getInventory().getNamefromList(i- 1);
//			Item itemInv = game.getPlayer().getInventory().getItemByName(name);
////			
////			 bitem = new Button(itemInv.getName());
//   //   			bitem = new RadioButton(itemInv.getName());
//      			bitem = new RadioButton(name);
////			 
////			  bitem.setWidth(20.0);
////				bitem.setHeight(20.0);
//			 
//				bitem.setTextFill(Color.BLACK);
////				
////				Image imageI;
//				if(bitem.getText().equals("Apple")) {
////					imageI = new Image(getClass().getResourceAsStream("ok.png"));
////					bitem.setGraphic(new ImageView(imageI));
//					// Fehler
//					
//				}
//				if(bitem.getText().equals("Key")) {
//					
//				}if(bitem.getText().equals("Meat")) {
//					
//				}if(bitem.getText().equals("MagicMushroom")) {
//					
//				}if(bitem.getText().equals("Mushroom")) {
//					
//				}if(bitem.getText().equals("Resin")) {
//						System.out.println("test");
//					
//				}if(bitem.getText().equals("RoomKey")) {
//					
//				}
//				if(bitem.getText().equals("Rope")) {
//									
//								}
//				if(bitem.getText().equals("Sail")) {
//					
//				}
//				if(bitem.getText().equals("Stick")) {
//					
//				}
//				if(bitem.getText().equals("Sword")) {
//					
//				}
//				if(bitem.getText().equals("Timber")) {
//					
//				}
//				
//				if(bitem.getText().equals("Weapon")) {
//									
//								}
//				if(bitem.getText().equals("Banana")) {
//					
//				}
//				if(bitem.getText().equals("Boat")) {
//					
//				}
//				if(bitem.getText().equals("Bread")) {
//									
//								}
//				if(bitem.getText().equals("Coconut")) {
//					
////				}
////				
//////				bitem.setGraphic(new ImageView(imageI)); // name,new ImageView(imageI)
////			  
//					gp.add(bitem, 1, i);
//
////					gp.add(new Button(name), 1, i);
////			 
////				
//				final Popup popup = new Popup();
//				Button beat = new Button("eat Item");
//				Button bdrop = new Button("drop Item");
//				VBox box = new VBox();
//				box.getChildren().addAll(beat, bdrop);
//				popup.setAutoHide(true);
//				
//				popup.getContent().addAll(box);
//				bitem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent actionEvent) {
//						if (!popup.isShowing()) 
//		                    popup.show(welcome); 
//		                else
//		                    popup.hide(); 
//					
//					}
//					});
////
////				beat.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
////					@Override
////					public void handle(ActionEvent actionEvent) {
////						if(bitem.getText().equals("Apple")) {
////							getEat(20);
////							
////						}
////						if(bitem.getText().equals("Meat")) {
////							getEat(50);
////						}
////						if(bitem.getText().equals("Bread")) {
////							getEat(50);
////						}
////						if(bitem.getText().equals("MagicMushroom")) {
////							getEat(100);
////						}
////						if(bitem.getText().equals("Coconut")) {
////							getEat(30);
////						}
////						if(bitem.getText().equals("Banana")) {
////							getEat(15);
////						}
////						if(bitem.getText().equals("Mushroom")) {
////							getEat(10);
////						}
////						else {
////							System.out.println("can't eat this item");
////						}
////						
////					
////					}
////					});
////				
////				
////				bdrop.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
////					@Override
////					public void handle(ActionEvent actionEvent) {
////					
////					
////					}
////					});
//			 
//			
//		}
//		
//		
//		}
//		
////		public ArrayList<Item> getFullInventory(){
////	        return inventory;
////	    }
//		
//		
//
//		
//	//	tv.addAll(items, number, button);
//	//System.out.println(game.getPlayer().getInventory().getNumberOfItemsInInventory());	
//		
//	//	Text text = new Text(game.getPlayer().getInventory().getContentsAsString());
//		
//		
//		gp.setGridLinesVisible(true);
//		
//		
//		sp.getChildren().add(gp);
		
        
        
       
//        
        
        
        
        
  //##############################################################################################################      
        
//		VBox box = new VBox();
//		HBox hbox = new HBox();
//		
//		
//		Button beat = new Button("eat Item");
//		beat.setAlignment(Pos.BOTTOM_CENTER);
////		beat.setPadding(new Insets(0, 0, 60, 0));
//		
//		Button bdrop = new Button("drop Item");
////		bdrop.setAlignment(Pos.TOP_CENTER);
////		bdrop.setPadding(new Insets(50, 0, 0, 0));
//		game.getPlayer().getInventory().getNamefromList(0);
//		
//		
//		
//		hbox.getChildren().addAll(bdrop, beat);
//		
////		ChoiceBox<String> choiceBox = new ChoiceBox<String>();
////		choiceBox.getItems().addAll("EAT", "DROP");
////		
//		RadioButton rb;
//		for(int i = 1; i <= game.getPlayer().getInventory().getNumberOfItemsInInventory(); i++){
//			
//			
//			String text = game.getPlayer().getInventory().getNamefromList(i- 1);
//			rb = new RadioButton(text);
//			box.getChildren().add(rb);
//			box.setPadding(new Insets(100,100,300,250));
//			
//			
//		}
//		
		
		
		
		
		
//		sp.getChildren().addAll(box, choiceBox);
//        sp.getChildren().addAll(box, beat, bdrop);
//		sp.getChildren().addAll(box, hbox);
        
 //###############################################################################################################################       
        
        
        
        BorderPane bp = new BorderPane();
        AnchorPane anchor = new AnchorPane();
        
        
        final Popup pop = new Popup();
        Button bdrop = new Button("drop");
        Button beat = new Button("eat");
//        Button bdrop = new Button("drop"); 
//        bdrop.setAlignment(Pos.BOTTOM_RIGHT);
//        bdrop.setLayoutX(20);
//        bdrop.setLayoutY(20);
//        bdrop.setPadding(new Insets(50,100,300,0));
        Button inv = new Button("show Inventory");
        
        HBox box = new HBox();
        
        
        box.getChildren().addAll(beat, bdrop);
        box.setPadding(new Insets(150,200,0,300));
     
//        bp.getChildren().add(box);
        
		pop.setAutoHide(true);
		
        
		pop.getContent().add(box );
//		pop.getContent().addAll(beat, bdrop );
		
//		bp.getChildren().add(inv);
//		bp.setCenter(inv);
		
		inv.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent actionEvent) {
		     	
			
			pop.getContent().add(getItemButton());
			if (!pop.isShowing()) 
                pop.show(welcome); 
            else
                pop.hide();  
		        

		}
		});
		
		
//		bp.getChildren().add(inv);
	anchor.getChildren().add(inv); 
//		anchor.getChildren().add(box);
//	anchor.getChildren().addAll(box, inv);
		
//	sp.getChildren().add(anchor);
//	sp.getChildren().add(inv);
//		sp.getChildren().add(bp);
//		
//		bp.getChildren().addAll(inv, beat, bdrop);
//		bp.setBottom(bdrop);
//		bp.setTop(beat);
//		bp.setCenter(inv);
		
		
//		bdrop.setAlignment(Pos.BOTTOM_RIGHT);
		
		
//		sp.getChildren().addAll(bp);
		
		
		
		
//	sp.getChildren().addAll(inv, box);
//	sp.getChildren().addAll(inv, beat, bdrop);
	//#########################################################################################
	
//	bitem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//		@Override
//		public void handle(ActionEvent actionEvent) {
//			
//		//	String text = button.getText();
//						
//			setClicked(true);
//			setName(button.getText());
//			
//		}
//		});
	
//###########################################################################################################	
	
	// wird nicht angezeigt
	System.out.println("Test");
	beat.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent actionEvent) {
		
			
//			RadioButton button = hm.get("Apple");
			
//			if(hm.containsKey("Timber")) {
//				System.out.println("ja");
//			}
			
			System.out.println("vor if");
			
			
			System.out.println(hm.get("Meat").isSelected() );
			
			if(hm.get("Meat").isSelected() == true) {
				
				System.out.println("nach if"); // passt bis hier
				
//				hm.remove("Meat"); // bis hier gehts
//				
				Item removeItem = game.getPlayer().getInventory().getItemByName("Meat");
				game.getPlayer().getInventory().removeItem(removeItem);
			}			
		System.out.println("Meat");	

			
			
		}
		});
	
	

	
//#######################################################################################################	
	
//		popup.getContent().addAll(box);
//		box.getChildren().add(tv);
//        TableView tv = new TableView();
//        
//        tv.setEditable(true);
//        
//        TableColumn itemName = new TableColumn("Items");
//        TableColumn lastNameCol = new TableColumn("Buttons");
//        
//        tv.getColumns().addAll(itemName, lastNameCol);
//        
//        
//        
        
//        final ObservableList<Item> data = FXCollections.observableArrayList(
//        		
//        		
//        		);
//        
//        itemName.setCellValueFactory( new PropertyValueFactory<Inventory,String>("name"));
//		Button inventoryButton = new Button("Inventory");
//		
//		JPanel inventoryPanel = new JPanel();
//		inventoryPanel.setBounds(550, 350, 200, 200);
//		inventoryPanel.setForeground(Color.BLUE);

	//#########################################################################################
	
	

	
	
	
//####################################################################################################	
        
        Scene scene  = new Scene(anchor);
		welcome.setScene(scene);
		welcome.show();
		
	}
	
	
	@Override
    public void stop() {
        game.endGame();
    }
	
//	public void getEat(int amount ) {
//		game.getPlayer().eat(amount);
//		removeItem(Item item)
//	}
	
	public VBox getItemButton() {
		VBox hb = new VBox();
		HashMap<Integer, String> hashmap = new HashMap<Integer, String>();
		HashMap<String, RadioButton> hm = new HashMap<String, RadioButton>();
		hb.setPadding(new Insets(42,100,5,0));
		for(int i = 1; i <= game.getPlayer().getInventory().getNumberOfItemsInInventory(); i++ ) {
			
			
			
			String text = game.getPlayer().getInventory().getNamefromList(i- 1);
//			Button bitem = new Button(text);
			
//			RadioButton bitem = new RadioButton(text);
		
			
//			hm.put(text, bitem);

			hashmap.put(i, text);
//			
			
//			hb.getChildren().add(bitem);
//			bItemEvent(bitem);
			
		}
		for (int c= 1; c <= hm.size(); c++) {
			
			
			
			String textButtons = hashmap.get(c);
			RadioButton bitem = new RadioButton(textButtons);
			
			
			
			System.out.println("Hier komm der Fehler");
			System.out.println(bitem.getText());
			chooseImage(bitem);
			hb.getChildren().add(bitem); // Fehler 
		}
		
//		setHashMap(hm);
		return hb;
	}
	
	

	
	
	public void setHashMap(HashMap<String, RadioButton> hm) { //Button
//		this.hm= new HashMap<String, RadioButton>(); //Button
		this.hm = hm;
		if(hm.containsKey("Timber")) {
			System.out.println("HashMap has timer");
		}
		
	}
	public void checkItems() {
		hm.containsKey("Timber");
	}
	
	public HashMap<String, RadioButton> getHashMap() { // Button
		return hm;
	}
	
	
	
	
//	public void bItemEvent(final Button button) {
//		button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				
//			//	String text = button.getText();
//							
//				setClicked(true);
//				setName(button.getText());
//				
//			}
//			});
//	}
	
//	public void bEatEvent(RadioButton button) {
//		button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				
//			//	String text = button.getText();
//				if(getName().equals("Apple")||getName().equals("Bread")||getName().equals("Banana")||getName().equals("Meat")||getName().equals("Coconut")|| getName().equals("Mushroom")|| getName().equals("MagicMushroom") ) {
//					
//					
//					if(getClicked() == true) {
//						String name = getName();
//						
//						Item item = game.getPlayer().getInventory().getItemByName(name);
//						game.getPlayer().getInventory().removeItem(item);
//					}
//				}
//				else {
//					System.out.println("Sie können dieses Item nicht essen");
//				}
//				
//				
//			}
//			});
//	}
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setClicked(boolean clicked) {
		this.clicked =clicked;
		
	}
	
	public boolean getClicked() {
		return clicked;
	}
	
//	public setName(){  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void chooseImage(RadioButton button ) {
		String name = button.getText();
		
		
		//image
		Image image; 
		ImageView imgView = new ImageView();
		imgView.setFitWidth(20);
      imgView.setFitHeight(20);
        
		
		
		
	if(name.equals("Apple")) {
		image = new Image("/Apple.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
		
	}
	
	if(name.equals("Key")) {
		image = new Image("/Key.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
	}
	if(name.equals("Meat")) {
		image = new Image("/Meat.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
	}
	if(name.equals("Magic-Mushroom")) {
		image = new Image("/Magic-Mushroom.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
	}
	if(name.equals("Mushroom")) {
		image = new Image("/Mushroom.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
	}
	if(name.equals("Resin")) {
		image = new Image("/Resin.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
		
	}
	if(name.equals("RoomKey")) {
		image = new Image("/RoomKey.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
	}
	if(name.equals("Rope")) {
		image = new Image("/Rope.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);				
					}
	if(name.equals("Sail")) {
		image = new Image("/Sail.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
	}
	
	if(name.equals("Stick")) {
		image = new Image("/Stick.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
	}
	
	if(name.equals("Sword")) {
		image = new Image("/Sword.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
	}
	
	if(name.equals("Timber")) {
		image = new Image("/Timber.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
	}
	
	if(name.equals("Weapon")) {
		image = new Image("/Weapon.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);	
					}
	if(name.equals("Banana")) {
		image = new Image("/Banana.PNG", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
	}
	
	if(name.equals("Boat")) {
		image = new Image("/insel2.jpg", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
	}
	
	if(name.equals("Bread")) {
		image = new Image("/insel2.jpg", true);
		imgView.setImage(image);
		button.setGraphic(imgView);		
					}
	if(name.equals("Coconut")) {
		image = new Image("/insel2.jpg", true);
		imgView.setImage(image);
		button.setGraphic(imgView);
	}
	
}
} 

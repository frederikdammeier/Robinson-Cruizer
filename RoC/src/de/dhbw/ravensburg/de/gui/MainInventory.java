package de.dhbw.ravensburg.de.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;

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
	
	
	
	
	private Game game;
	private Thread gameThread;
	
	private TableView<Item> table = new TableView<>();
	
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
		

		 //#######################################
		
		 
		 
		 // hier soll die ObservableList an einen Container gegeben werden zum Beispiel an eine ComboBox aber eher An tabelView
		

//		TableView<Item> table = new TableView<>();
		
	     TableColumn<Item, String> colitems = new TableColumn<Item, String>("Inventory");	     
	     	colitems.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
//	     	addButtonToTable();
	     	
	     	
	     	table.getColumns().addAll(colitems);
	     

	       // Display row data
	     ObservableList<String> inventory = getInvList();
	 
	     
//	     System.out.println(inventory.get(2));
	     System.out.println(game.getPlayer().getInventory().getItemfromList(4).getName());
	     System.out.println(game.getPlayer().getInventory().getItemByName("Rope").getName());
	     
	     
	     ArrayList<Item> inventoryItem = new ArrayList<Item>();
	     
	   
	     int b = 0;
	     for(int i =1; i <= inventory.size(); i++) {
	    	
	    	 inventoryItem.add(game.getPlayer().getInventory().getItemByName(inventory.get(b)));
	    	 
	    	System.out.println(inventoryItem.get(b).getName());
	    	 b = b+1;
	     }
	     
	    
	     ObservableList<Item> inventoryItemObs  = FXCollections.observableArrayList(inventoryItem); 
	     
	     table.setItems(inventoryItemObs);
	       
	     System.out.println(table.getItems());

	     
	   //auswahl des Items
	     
	     Button button = new Button("test");
//	     	final Item item = table.getSelectionModel().getSelectedItem();
//	     	final  String name = table.getSelectionModel().getSelectedItem().getName();
//	     	final int index = table.getSelectionModel().getSelectedIndex();
	     
	     	
//	     	String celldata = this.colitems.getCellData(selectedIndex);
	     	final int selectedIndex = this.table.getSelectionModel().getSelectedIndex();
	     	System.out.println("Hier");
//	     	final String selectedString = this.table.getSelectionModel().getSelectedItem().getName();
//			System.out.println(game.getPlayer().getInventory().getItemByIndex(selectedIndex));
		
			
			
	     System.out.println("test bis vor auswahlitem");
	     button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
//					final Item item = game.getPlayer().getInventory().getItemfromList(selectedIndex);
					System.out.println(selectedIndex);
//					String text = button.getText();				
//					setClicked(true);
//					setName(button.getText());
					
//					System.out.println(name);
//					System.out.println(item.getName());
//					System.out.println(name);
				}
				});
	     
	     
	     
	    VBox vb = new VBox();
		vb.getChildren().addAll(table, button);

		 
		//############################################
		
		 
		 String output = game.getPlayer().getInventory().getContentsAsString();
		inv.setText(output);
		
		HBox hbox = new HBox();
//		bPane.setAlignment(hbox, Pos.BOTTOM_CENTER);
		hbox.getChildren().addAll(vb, function, itemsfield, doButton);

		
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
	
	private void addButtonToTable() {
//	        TableColumn<Inventory, Void> colBtn = new TableColumn(" ");
//
//	        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
//	            @Override
//	            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
//	                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {
//
//	                    private final Button btn = new Button("Picture");
//	                  
//	                    {
////	                        btn.setOnAction((ActionEvent event) -> {
////	                        	Item data = getTableView().getItems().get(getIndex());
////	                            System.out.println("selectedData: " + data);
////	                        });
//	                    }
//
//	                    @Override
//	                    public void updateItem(Void item, boolean empty) {
//	                        super.updateItem(item, empty);
//	                        if (empty) {
//	                            setGraphic(null);
//	                        } else {
//	                            setGraphic(btn);
//	                        }
//	                    }
//	                };
//	                return cell;
//	            }
//	        };
//
//	        colBtn.setCellFactory(cellFactory);
//
//	        table.getColumns().add(colBtn);

	    }
	private ObservableList<String> getInvList() {
		  
		// hier wird versucht die ArrayList in eine Oberservabel list zu wandeln
			
//			ArrayList <Item> inventoryArray = new ArrayList<Item>();
			ArrayList <String> inventoryArray = new ArrayList<String>();
			int size = game.getPlayer().getInventory().getNumberOfItemsInInventory();
			
			for(int i = 0; i < size; i ++) {
			inventoryArray.add(game.getPlayer().getInventory().getNamefromList(i));
		//			inventoryArray.add(game.getPlayer().getInventory().getItemByIndex(i));
						
		//			inventoryArray.add(game.getPlayer().getInventory());
		//			inventoryArray.add(game.getPlayer());
	//				Item name=	game.getPlayer().getInventory().getItemfromList(i);
	//				getPictures(name);
			System.out.println(inventoryArray.get(i));
			
//			inventoryArray.add(game.getPlayer().getInventory().getItemfromList(i));
//					System.out.println(name.getName());
			}
			System.out.println("Test");
	//			System.out.println(inventoryArray.get(4));
				// hier soll die ArrayList an die OberservableList übergeben werden 
	//			 ObservableList<Item> inventory  = FXCollections.observableArrayList(inventoryArray);
			ObservableList<String> inventory  = FXCollections.observableArrayList(inventoryArray); 
			
//			ObservableList<Item> inventory  = FXCollections.observableArrayList(inventoryArray); 
System.out.println("test ObsList");
			 
	      return inventory;
	  }
	
	private void getPictures(Item item) {
//		String name = button.getText();
//		Item name = item;
//		
//		//image
//		Image image; 
//		ImageView imgView = new ImageView();
//		imgView.setFitWidth(20);
//		imgView.setFitHeight(20);
//        
//		// müssen jz auf die Button zugreifen
//		
//		
//	if(name.equals("Apple")) {
//		image = new Image("/Apple.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//		
//	}
//	
//	if(name.equals("Key")) {
//		image = new Image("/Key.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//	}
//	if(name.equals("Meat")) {
//		image = new Image("/Meat.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//	}
//	if(name.equals("Magic-Mushroom")) {
//		image = new Image("/Magic-Mushroom.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//	}
//	if(name.equals("Mushroom")) {
//		image = new Image("/Mushroom.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//	}
//	if(name.equals("Resin")) {
//		image = new Image("/Resin.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//		
//	}
//	if(name.equals("RoomKey")) {
//		image = new Image("/RoomKey.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//	}
//	if(name.equals("Rope")) {
//		image = new Image("/Rope.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);				
//					}
//	if(name.equals("Sail")) {
//		image = new Image("/Sail.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//	}
//	
//	if(name.equals("Stick")) {
//		image = new Image("/Stick.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//	}
//	
//	if(name.equals("Sword")) {
//		image = new Image("/Sword.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//	}
//	
//	if(name.equals("Timber")) {
//		image = new Image("/Timber.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//	}
//	
//	if(name.equals("Weapon")) {
//		image = new Image("/Weapon.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);	
//					}
//	if(name.equals("Banana")) {
//		image = new Image("/Banana.PNG", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//	}
//	
//	if(name.equals("Boat")) {
//		image = new Image("/insel2.jpg", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//	}
//	
//	if(name.equals("Bread")) {
//		image = new Image("/insel2.jpg", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);		
//					}
//	if(name.equals("Coconut")) {
//		image = new Image("/insel2.jpg", true);
//		imgView.setImage(image);
//		button.setGraphic(imgView);
//	}
	
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

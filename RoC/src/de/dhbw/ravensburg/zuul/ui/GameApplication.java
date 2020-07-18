package de.dhbw.ravensburg.zuul.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Random;

import de.dhbw.ravensburg.zuul.Command;
import de.dhbw.ravensburg.zuul.Difficulty;
import de.dhbw.ravensburg.zuul.Game;
import de.dhbw.ravensburg.zuul.Predator2;
import de.dhbw.ravensburg.zuul.item.Food;
import de.dhbw.ravensburg.zuul.item.Item;
import de.dhbw.ravensburg.zuul.item.MagicMushroom;
import de.dhbw.ravensburg.zuul.item.RoomKey;
import de.dhbw.ravensburg.zuul.room.Room;
import de.dhbw.ravensburg.zuul.room.RoomType;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

/**
 * Welcome to the Robinson Cruizer Adventure Game.
 * 
 * In this game, you're waking up on a beach on a lonely island somewhere in the
 * middle of the ocean. It' no big deal to leave, you just need a boat.
 * Too bad that the one you came with is hanging between those rocks.
 * 
 * 
 * As you go into the nearby rainforest seeking for building materials, strange things
 * start to happen...
 * ---------------------------------------------------------------------------------------
 * 
 * 
 * This class starts the game and holds the most important methods 
 * relevant for the GUI.
 * 
 * @author 
 * 
 */

public class GameApplication extends Application {
	public static final Double w = 1000.0;
	public static final Double h = 1000.0;
	
	private ArrayList<String> input = new ArrayList<String>();
	private DoubleValue lastNanoTime;
	private Sprite robin, west, east, north, south, up, down;
	private GraphicsContext gc;
	private StringValue lastKeyPressed, goNext;
	private Position mousePosition;
	private Game game;
	private Canvas canvas;
	private Label dialogText;
	private Button okButton, cancelButton, eatButton, dropButton;
	private VBox dialogContainer;
	private HBox buttonContainer;
	private StackPane sPane;
	private Group dialogGroup, goRoomGroup, inventoryGroup;
	private TableView<Item> inventoryTable;
	private Rectangle yesNoBackground, goEastRectangle, goWestRectangle, goSouthRectangle, goNorthRectangle, goUpRectangle, goDownRectangle;
	/** Holds messages as strings which are meant to be helpful for the player of the game */
	private HashMap<String, String> messages;
	/** Holds important events that can occure in form of ActionEvents. */
	private HashMap<String, EventHandler<ActionEvent>> dialogHandlers;
	private HashMap<String, Image> itemImages;
	
	private Predator2 predator;
	private Image sword, food, wArrow, eArrow, sArrow, nArrow, uArrow, dArrow, uStairs, dStairs;
	private Difficulty difficulty;
	private Thread pThread;
	
	
	public static void main(String... args) {
		launch(args);
	}
	
	/**
	 * This method enables the player to take control (mouse and keyboard commands) and renders informations visually (like the hunger bar).
	 */
	@Override
	public void start(Stage stage) throws Exception {	
		//Difficulty selection screen
		final Stage welcome = stage;
		StackPane rootb= new StackPane(); 
		
		//image
		Image image1 = new Image("Images/Room/START.PNG", 400, 400, false, false);
		ImageView imgView = new ImageView();
        imgView.setImage(image1);
        imgView.setFitWidth(rootb.getWidth());
        imgView.setFitHeight(rootb.getHeight());
        
        //labels 
		Label welcomeLabel = new Label();
		Label infoLabel = new Label();
		Label label = new Label("by Frederik Dammeier, Philipp Schneider and Moritz Link");
		//button
		Button goSetting  = new Button("go to Setting");

		welcomeLabel.setText("Welcome to the game:  Robinson Cruizer");
		welcomeLabel.setFont(new Font("Arial",  20));
		welcomeLabel.setTextFill(Color.BLACK);
		welcomeLabel.setPadding(new Insets(20,0,0,0));
		//infoLabel.setText("Robinson Cruizer is an adventure game, developed /br by Frederik Dammaier,/n Philipp Schneider /n and Moritz Link");
		label.setPadding(new Insets(50,0,0,0));
		rootb.getChildren().addAll(imgView, welcomeLabel, goSetting, label); // , label
		 /**
		 * Defines the position from roots children
		 */	
		rootb.setAlignment(welcomeLabel, Pos.TOP_CENTER);
		rootb.setAlignment(label, Pos.TOP_CENTER);		
		rootb.setAlignment(goSetting, Pos.BOTTOM_RIGHT);
		
		final Scene welcomeScene = new Scene(rootb, 400, 400);
		welcome.setScene(welcomeScene);
		welcome.show();
		
		StackPane level = new StackPane();			
		Image img = new Image("Images/Room/START.PNG", 400, 400, false, false);
		ImageView imgView2 = new ImageView();
		imgView2.setImage(img);
	    imgView2.setFitWidth(level.getWidth());
	    imgView2.setFitHeight(level.getHeight());
		VBox box = new VBox();						
		ToggleGroup group = new ToggleGroup();

		final RadioButton beasy = new RadioButton("Easy");
		final RadioButton bmedium = new RadioButton("Medium");
		final RadioButton bhard = new RadioButton("Hard");
		
		Button goBack = new Button("go back");
		Button goGame = new Button("start the Game");
		Label description = new Label("please choose your level");		
		
		description.setFont(new Font("Serif",  24));
		description.setTextFill(Color.WHITE);		
		beasy.setFont(new Font("Serif",  24));
		bmedium.setFont(new Font("Serif",  24));		
		bhard.setFont(new Font("Serif",  24));		
		beasy.setTextFill(Color.WHITE);
		bmedium.setTextFill(Color.WHITE);
		bhard.setTextFill(Color.WHITE);
		beasy.setToggleGroup(group);
		bmedium.setToggleGroup(group);
		bhard.setToggleGroup(group);
		
		// if nothing is selected, system selects  button beasy
		beasy.setSelected(true);
	
		box.getChildren().addAll(beasy, bmedium, bhard ); // description, goBack, goGame
		box.setPadding(new Insets(120,20,20,135));
		level.getChildren().addAll(imgView2,box, description, goBack, goGame);
		level.setAlignment(description, Pos.TOP_CENTER);
		level.setAlignment(goBack, Pos.BOTTOM_LEFT);
		level.setAlignment(box, Pos.BOTTOM_CENTER);		
		level.setAlignment(goGame, Pos.BOTTOM_RIGHT);
		
		final Scene levelScene = new Scene(level, 400, 400);
		final Group root = new Group();
		sPane = new StackPane();
		final	Scene scene = new Scene(root);
	
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
		
		messages = new HashMap<>();
		populateMessageHashMap();
		dialogHandlers = new HashMap<>();
		createDialogHandlers();
		
		lastNanoTime = new DoubleValue();
		lastKeyPressed = new StringValue();
		lastNanoTime.value = 0.0;
		lastKeyPressed.value = "RIGHT";
		goNext = new StringValue();
		
		mousePosition = new Position();
		mousePosition.x = 0.0;
		mousePosition.y = 0.0;
		
		canvas = new Canvas(w, h);
		sPane.getChildren().add(canvas);
		root.getChildren().add(canvas);
		
		//Yes/No Answer
		
		dialogGroup = new Group();
		dialogText = new Label("YesNoQuestion.");
		dialogText.setWrapText(true);
		okButton = new Button("Ok");
		cancelButton = new Button("Cancel");
		dialogText.setMaxWidth(400);
		dialogContainer = new VBox();
		dialogContainer.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		dialogContainer.setAlignment(Pos.BOTTOM_CENTER);
		
		buttonContainer = new HBox();
		
		buttonContainer.getChildren().addAll(okButton, cancelButton);
		dialogContainer.getChildren().addAll(dialogText, buttonContainer);		
		dialogGroup.getChildren().add(dialogContainer);
		
		cancelButton.setOnAction(dialogHandlers.get("acknowledgeEvent"));
		
		
//		sPane.getChildren().add(yesNoBackground);
		dialogGroup.setVisible(false);
		sPane.getChildren().add(dialogGroup);
		root.getChildren().add(dialogGroup);
		
		
		
		scene.setOnKeyPressed(
	            new EventHandler<KeyEvent>()
	            {
	                public void handle(KeyEvent e)
	                {
	                    String code = e.getCode().toString();
	 
	                    // only add once... prevent duplicates
	                    if ( !input.contains(code) )
	                        input.add( code );
	                    
	                    lastKeyPressed.value = code;                    
	                }
	            });
	 
	    scene.setOnKeyReleased(
	            new EventHandler<KeyEvent>()
	            {
	                public void handle(KeyEvent e)
	                {
	                    String code = e.getCode().toString();
	                    input.remove( code );
	                }
	            });
	    
	    scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
	    	public void handle(KeyEvent e) {
	    		//Attacking
	    		if(e.getCharacter().equals("a") || e.getCharacter().equals("A")) {
                	game.playerAttack();
                }
	    		
//	    		if(e.getCharacter().equals("e") || e.getCharacter().equals("E")) {
//	    			if(!game.eat()) System.out.println("Collect some food to eat.");
//	    		}
	    		
	    		//open / close the Inventory
	    		if(e.getCharacter().equals("i") || e.getCharacter().equals("I")) {
	    			if(inventoryGroup.isVisible()) {
	    				inventoryGroup.setVisible(false);
	    			} else {
	    				inventoryGroup.setVisible(true);
	    			}
	    		}
	    	}
	    });
	    
	    scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				mousePosition.x = e.getSceneX();
				mousePosition.y = e.getSceneY();
			}
	    });
	    
		gc = canvas.getGraphicsContext2D();
		
		final AnimationTimer at = new AnimationTimer() {
			double speed = 200.0;
			double enemySpeed = 150.0;
			
			@Override
			public void handle(long currentNanoTime) {				
				// calculate time since last update.
		        double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
		        lastNanoTime.value = currentNanoTime;
				
		        //Calculate the the relative angle that robin needs to have to face the mouse pointer
		        double mouseAngle = getMouseAngle();	        
		        
		        //moveRobin according to mouseDirection
		        if(input.contains("W") && robin.distanceTo(mousePosition.x, mousePosition.y) > 20) {
		        	robin.setVelocity(0,0);
		        	
		        	double newVelocityX = Math.cos(mouseAngle+Math.PI)*speed;
		        	double newVelocityY = Math.sin(mouseAngle+Math.PI)*speed;
		        	
		        	if(robin.intersects(west) && newVelocityX < 0)
		        		newVelocityX = 0;
		        	if(robin.intersects(north) && newVelocityY < 0)
		        		newVelocityY = 0;
		        	if(robin.intersects(east) && newVelocityX > 0)
		        		newVelocityX = 0;	        		
		        	if(robin.intersects(south) && newVelocityY > 0)
		        		newVelocityY = 0;
		        	robin.addVelocity(newVelocityX, newVelocityY);
		        	
		        	robin.update(elapsedTime);
		        }     
		        
		        //Draw background image
		        gc.drawImage(game.getCurrentRoom().getBGImage(), 0, 0);		        
		        
		        if(game.getCurrentRoom().hasExit("up")) {
		        	gc.drawImage(uStairs, goUpRectangle.getX(), goUpRectangle.getY());
		        }
		        if(game.getCurrentRoom().hasExit("down")) {
		        	gc.drawImage(dStairs, goDownRectangle.getX(), goDownRectangle.getY());
		        }
		        
		        //Items in the current room  		        
		        renderRoomIcons();
		        checkForItemCollision();
		        
		      //Mouse intersects one of the Boundaries
		        if(east.intersects(mousePosition.x, mousePosition.y) && goEastRectangle.isVisible()) {
		        	gc.drawImage(eArrow, east.getPositionX(), east.getPositionY());
		        }
		        if(west.intersects(mousePosition.x, mousePosition.y) && goWestRectangle.isVisible()) {
		        	gc.drawImage(wArrow, west.getPositionX(), west.getPositionY());
		        }
		        if(north.intersects(mousePosition.x, mousePosition.y) && goNorthRectangle.isVisible()) {
		        	gc.drawImage(nArrow, north.getPositionX(), north.getPositionY());
		        }
		        if(south.intersects(mousePosition.x, mousePosition.y) && goSouthRectangle.isVisible()) {
		        	gc.drawImage(sArrow, south.getPositionX(), south.getPositionY());
		        }
		        if(up.intersects(mousePosition.x, mousePosition.y) && goUpRectangle.isVisible()) {
		        	gc.drawImage(uArrow, w*0.45, h*0.3);
		        }
		        if(down.intersects(mousePosition.x, mousePosition.y) && goDownRectangle.isVisible()) {
		        	gc.drawImage(dArrow, w*0.45, h*0.5);
		        }
		        	        
		        //Text at the top of the Screen
		        printRoomAndTimeInformation();
		        
		        //Hunger Bar
		        printVitalityBar(gc, game.getPlayer().getHunger(), Color.SADDLEBROWN, w*0.95-200, h*0.95, 200.0, 20.0);
		        
		        //Health Bar
		        printVitalityBar(gc, game.getPlayer().getHealth(), Color.RED, w*0.95-200, h*0.95-30, 200.0, 20.0);
		        
		        //Sword (Attack Icon)
		        gc.drawImage(sword, w*0.07, h*0.87);
		        gc.setFill(Color.BLACK);
		        gc.fillText("\"A\" to attack", w*0.1, h*0.96);
		        
//		        //Meat (Eat Icon)
//		        gc.drawImage(food, w*0.27, h*0.87);
//		        gc.fillText("\"E\" to eat", w*0.3, h*0.96);
		        
		        
		        if(game.getCurrentRoom().getCreature() != null) {
		        	CreatureSprite creature = game.getCurrentRoom().getCreatureSprite();
		        	double creatureAngle = getAngleBetweenSprites(creature, robin);
		        	creature.render(gc, Math.toDegrees(creatureAngle+Math.PI/2));
		        	
		        	checkForCreatureCollision();
	        
			        //move hostile creatures towards robin
			        if(!creature.getCreature().getPeaceful() && creature.distanceTo(robin.getCenterX(), robin.getCenterY()) > 70) {
			        	creature.setVelocity(0,0);
			        	
			        	double newVelocityX = Math.cos(creatureAngle+Math.PI)*enemySpeed;
			        	double newVelocityY = Math.sin(creatureAngle+Math.PI)*enemySpeed;
			        	
			        	if(creature.intersects(west) && newVelocityX < 0)
			        		newVelocityX = 0;
			        	if(creature.intersects(north) && newVelocityY < 0)
			        		newVelocityY = 0;
			        	if(creature.intersects(east) && newVelocityX > 0)
			        		newVelocityX = 0;	        		
			        	if(creature.intersects(south) && newVelocityY > 0)
			        		newVelocityY = 0;
			        	creature.addVelocity(newVelocityX, newVelocityY);
			        	
			        	creature.update(elapsedTime);
			        }    	        
			        //if the creature is hostile and near enough it deals damage to the player. or does it?         
		        }
		        //Rotate Robin according to mouseDirection and Print him to the canvas.
		        robin.render(gc, Math.toDegrees(mouseAngle+Math.PI/2));
			}				
		};
		
		goGame.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){

			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent actionEvent) {
				
				if(beasy.isSelected()) {
					difficulty = Difficulty.EASY;
				}
				else if(bmedium.isSelected()) {
					difficulty = Difficulty.MEDIUM;
				}
				else if(bhard.isSelected()) {
					difficulty = Difficulty.HARD;
				}
				else {
					System.out.println("You level will be easy");
				}
				game = new Game(difficulty);		
				game.start();
				
				predator = new Predator2(game);
				pThread = new Thread(predator, "predator");
				pThread.start();			
				robin = game.getPlayer().getPlayerSprite();
				
				initializeRoomGoup();
				west = new Sprite(0.0, h*0.1, 0.0, 0.0, w*0.1, h*0.8);	
				east = new Sprite(w*0.9, h*0.1, 0.0, 0.0, w*0.1, h*0.8);
				north = new Sprite(w*0.1, 0.0, 0.0, 0.0, w*0.8, h*0.1);
				south = new Sprite(w*0.1, h*0.9, 0.0, 0.0, w*0.8, h*0.1);
				up = new Sprite(goUpRectangle.getX(), goUpRectangle.getY(), 0.0, 0.0, goUpRectangle.getWidth(), goUpRectangle.getHeight());
				down = new Sprite(goDownRectangle.getX(), goDownRectangle.getY(), 0.0, 0.0, goDownRectangle.getWidth(), goDownRectangle.getHeight());
				
				sword = new Image("Images/Item/Sword.PNG", w/15, w/15, false, false);
				food = new Image("Images/Item/Meat.PNG", w/15, w/15, false, false);
				uStairs = new Image("Images/Misc/stairs_up.PNG", goUpRectangle.getWidth(), goUpRectangle.getHeight(), false, false);
				dStairs = new Image("Images/Misc/stairs_down.PNG", goDownRectangle.getWidth(), goDownRectangle.getHeight(), false, false);
				eArrow = new Image("Images/Misc/eastArrow.png", w*0.1, h*0.8, false, false);
				wArrow = new Image("Images/Misc/westArrow.png", w*0.1, h*0.8, false, false);
				nArrow = new Image("Images/Misc/northArrow.png", w*0.8, h*0.1, false, false);
				sArrow = new Image("Images/Misc/southArrow.png", w*0.8, h*0.1, false, false);
				uArrow = new Image("Images/Misc/upDownArrow.png", w*0.1, h*0.2, false, false);
				dArrow = new Image("Images/Misc/upDownArrow.png", w*0.1, h*0.2, false, false);
				
				root.getChildren().add(goRoomGroup);
				
				initializeImageViews();
				
				initializeInventoryGroup(root);
				
				
				
				at.start();
				welcome.setTitle("Robinson Cruizer");
				welcome.getIcons().add(new Image("Images/Item/Icon.png"));
				welcome.setScene(scene);
				welcome.centerOnScreen();
				welcome.show();
			}

			
		});
	}
	
	private void initializeImageViews() {
		double s = 25;
		
		itemImages = new HashMap<>();
		itemImages.put("Apple", new Image("Images/Item/Apple.PNG", s, s, true, true));
		itemImages.put("Banana", new Image("Images/Item/Banana.PNG", s, s, true, true));
		itemImages.put("Boat", new Image("Images/Item/Boat.PNG", s, s, true, true));
		itemImages.put("Bread", new Image("Images/Item/Bread.PNG", s, s, true, true));
		itemImages.put("Coconut", new Image("Images/Item/Coconut.PNG", s, s, true, true));
		itemImages.put("Icon", new Image("Images/Item/Icon.PNG", s, s, true, true));
		itemImages.put("Key", new Image("Images/Item/Key.PNG", s, s, true, true));
		itemImages.put("Magic-Mushroom", new Image("Images/Item/Magic-Mushroom.PNG", s, s, true, true));
		itemImages.put("Meat", new Image("Images/Item/Meat.PNG", s, s, true, true));
		itemImages.put("Mushroom", new Image("Images/Item/Mushroom.PNG", s, s, true, true));
		itemImages.put("Resin", new Image("Images/Item/Resin.PNG", s, s, true, true));
		itemImages.put("Rope", new Image("Images/Item/Rope.PNG", s, s, true, true));
		itemImages.put("Sail", new Image("Images/Item/Sail.PNG", s, s, true, true));
		itemImages.put("Stick", new Image("Images/Item/Stick.PNG", s, s, true, true));
		itemImages.put("Sword", new Image("Images/Item/Sword.PNG", s, s, true, true));
		itemImages.put("Timber", new Image("Images/Item/Timber.PNG", s, s, true, true));
	}
	
	/**
	 * This method adds important Events to the dialogHandlers HashMap.
	 */
	private void createDialogHandlers() {
		dialogHandlers.put("acknowledgeEvent", new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				dialogGroup.setVisible(false);
			}
		});
		
		dialogHandlers.put("roomChangeEvent", new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				Room nextRoom = game.getCurrentRoom().getExit(goNext.value);
	
				if (nextRoom.isLocked()) {
					if (game.getPlayer().getInventory().containsItem(nextRoom.getKey())) {
						dialogText.setText(messages.get("unlockRoom"));
						okButton.setOnAction(dialogHandlers.get("unlockRoomEvent"));
					} else {
						dialogText.setText(messages.get("roomLocked"));
						okButton.setOnAction(dialogHandlers.get("acknowledgeEvent"));
					}
				} else if(!nextRoom.hasExitToRoom(game.getCurrentRoom()) && !nextRoom.getType().equals(RoomType.FINISH)) {
					dialogText.setText(messages.get("trapDoor"));
					okButton.setOnAction(dialogHandlers.get("trapDoorEvent"));
				} else {
					game.setCurrentRoom(nextRoom);
					setExitVisibility();
					dialogGroup.setVisible(false);
				}			
			}
		});
		
		dialogHandlers.put("unlockRoomEvent", new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				game.setCurrentRoom(game.getCurrentRoom().getExit(goNext.value));
				game.getCurrentRoom().unLock();
				setExitVisibility();
				dialogGroup.setVisible(false);
			}
		});
		
		dialogHandlers.put("trapDoorEvent", new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				game.setCurrentRoom(game.getCurrentRoom().getExit(goNext.value));	
				setExitVisibility();
				dialogGroup.setVisible(false);
			}
		});
		
		dialogHandlers.put("freitagTalkEvent", new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				
				
				
				dialogText.setText("Nice to meet you foreign. My name is Freitag and i see you are in trouble. "
						+ "I saw how you ship sunk and you almost died in the water but thanks to god you are alive. "
						+ "You are on a island and i believe you want to escape from here. "
						+ "Hmmm.... To escape you need a new boat. I think you can find on this island everything what you need to build a new one."
						+ " You can ask the Natives on the island, they will know where you can find your equipment. "
						+ "oh but you shouldn't make them angry because then they will try to kill you. "
						+ "But only when you make them angry so try to be polite to them, also you can try to find the Mage somewhere."
						+ " I don't know where he is but i am sure he can help you. "
						+ "Oh but you must be careful. "
						+ "On this island are many hunters who want to kill you because they think you are against their god. "
						+ "So try to avoid them. "
						+ "It was nice to meet you, i hope you will survive. Good bye. " );
//				dialogText.setText("Hello foreign. you have to find items to build a boat. ");
				okButton.setOnAction(dialogHandlers.get("acknowledgeEvent"));
				// funktioniert noch nicht so wirklich
				
//				dialogGroup.setVisible(true);
//				dialogGroup.setVisible(false);
//				String text = dialogText.getText();
//				if(text == dialogText.getText() ) {
//					okButton.setOnAction(dialogHandlers.get("acknowledgeEvent"));
//				}
			}
		});
		
		
		dialogHandlers.put("mageTalkEvent", new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
					dialogText.setText("Hello foreign, "
//							+ "Nice to meet you. If you want I can help you. "
//							+ "I am a powerful mage and can teleport you to different places on this island. "
//							+ "Do you want to?
							);
					dialogText.setText("Hello, should i teleport you? ");
					okButton.setOnAction(dialogHandlers.get("mageTeleportEvent"));
			}
		});
		dialogHandlers.put("mageTeleportEvent", new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
					dialogText.setText("OK i hope you will escape from this island. Good bye");
					game.teleport(); // public machen in Game
					okButton.setOnAction(dialogHandlers.get("acknowledgeEvent"));
			}
		});
		dialogHandlers.put("prisonerTalkEvent", new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
//				dialogText.setText("Ah please help me. I am in this Dungeon since two weeks. "
//						+ "Can you open it? When you will free me i can give you information about the island. "
//						+ "Do you have the key for me? ");	
				dialogText.setText("Help me than i tell you something about this place.");
				okButton.setOnAction(dialogHandlers.get("prisonerTradeEvent"));
				
			
			}
		});
		dialogHandlers.put("prisonerTradeEvent", new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				//neue metode für handeln?
				Item key = game.getPlayer().getInventory().getItemByName("Key");
				if(game.getPlayer().getInventory().containsItem("Key") == true) {
//					dialogText.setText("Thank you now i am free."  + 
//										"So i will tell you my secret information about the Mage. " + 
//										"On this island is a mage and he can help you. " + 
//										"He waits on the second floor in the Room named ruinMage. " + 
//										"I hope you can find him.");
					dialogText.setText("Thank you. The mage is on the second floor.");
					game.getPlayer().getInventory().removeItem(key);
					okButton.setOnAction(dialogHandlers.get("acknowledgeEvent"));
				}
				else {
					dialogText.setText("You don't have the key. Then go and find him! ");
					okButton.setOnAction(dialogHandlers.get("acknowledgeEvent"));
				}				
			}
		});
		dialogHandlers.put("nativeTalkEvent", new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
//				dialogText.setText("Hello do you have any meat for me? "
//									+ "i can give you some inforamtion about the island in exchange for the meat. "
//									+ "Do you want to change? ");
				dialogText.setText("Give me meat and i tell you something. ");
				okButton.setOnAction(dialogHandlers.get("nativeTradeEvent"));
			}
		});
		dialogHandlers.put("nativeTradeEvent", new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				dialogText.setText("That's nice. ");
				Item meat = game.getPlayer().getInventory().getItemByName("Meat");
				if(game.getPlayer().getInventory().containsItem(meat) == true) {
					
					dialogText.setText(getInfosFromNative());
					game.getPlayer().getInventory().removeItem(meat);	

				}
				
				else {
					dialogText.setText("Oh i see you tried to betray me! ");
					game.getCurrentRoom().getCreature().setPeaceful(false);// changePeaceful(); hier muss noch der Native böse werden.
					
				}
				okButton.setOnAction(dialogHandlers.get("acknowledgeEvent"));
			}
		});
		dialogHandlers.put("buildBoatEvent", new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(game.getBoatBuilder().buildBoat(game.getPlayer().getInventory())) {
					game.getMap().activateFinish();
					dialogText.setText(messages.get("boatBuilt"));					
				} else {					
					dialogText.setText(messages.get("couldntBuildBoat"));
				}
				
				okButton.setOnAction(dialogHandlers.get("acknowledgeEvent"));
			}
		});
	}
	
	/**
	 * This method adds important messages to the messages HashMap.
	 */
	private void populateMessageHashMap() {
		messages.put("roomLocked", "The room you're trying to access is locked! Try finding a key.");
		messages.put("unlockRoom", "Would you like to unlock this room now?");
		messages.put("inventoyFull", "You can't carry any more!");
		messages.put("trapDoor", "You're trying to enter through a trapdoor. Continue?");
		messages.put("boatReady", "You're ready to build a boat.");
		messages.put("goDirection", "Would you like to go %s?");
		messages.put("talkAction", "Would you like to talk?");
		messages.put("buildBoat", "You are ready to build a boat now. Proceed?");
		messages.put("boatBuilt", "A boat has been added to your inventory. Go to any beach to leave the island.");
		messages.put("couldntBuildBoat", "It seems like you dropped a required item.");
		}
	
	
	/**
	 * Methods that enable and displays exits of a room visually.
	 */
	private void initializeRoomGoup() {
		goRoomGroup = new Group();
		
		goEastRectangle = new Rectangle(w*0.9, h*0.1, w*0.1, h*0.8);
		goWestRectangle = new Rectangle(0.0, h*0.1, w*0.1, h*0.8);
		goNorthRectangle = new Rectangle(w*0.1, 0.0, w*0.8, h*0.1);
		goSouthRectangle = new Rectangle(w*0.1, h*0.9, w*0.8, h*0.1);
		goUpRectangle = new Rectangle(w*0.35, h*0.3, w*0.3, h*0.2);
		goDownRectangle = new Rectangle(w*0.35, h*0.5, w*0.3, h*0.2);
		
		// make rectangles invisible
		goEastRectangle.setFill(Color.TRANSPARENT);
		goWestRectangle.setFill(Color.TRANSPARENT);
		goNorthRectangle.setFill(Color.TRANSPARENT);
		goSouthRectangle.setFill(Color.TRANSPARENT);
		goUpRectangle.setFill(Color.TRANSPARENT);
		goDownRectangle.setFill(Color.TRANSPARENT);
		
		goEastRectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(!dialogGroup.isVisible()) {
					goNext.value = "east";
					dialogText.setText(String.format(messages.get("goDirection"), goNext.value));
					okButton.setOnAction(dialogHandlers.get("roomChangeEvent"));
	    			dialogGroup.setVisible(true);
				}
			}
		});
		goWestRectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(!dialogGroup.isVisible()) {	
					goNext.value = "west";
					dialogText.setText(String.format(messages.get("goDirection"), goNext.value));
					okButton.setOnAction(dialogHandlers.get("roomChangeEvent"));
	    			dialogGroup.setVisible(true);
				}
			}
		});
		goNorthRectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(!dialogGroup.isVisible()) {
					goNext.value = "north";
					dialogText.setText(String.format(messages.get("goDirection"), goNext.value));
					okButton.setOnAction(dialogHandlers.get("roomChangeEvent"));
	    			dialogGroup.setVisible(true);
				}
			}
		});
		goSouthRectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(!dialogGroup.isVisible()) {
					goNext.value = "south";
					dialogText.setText(String.format(messages.get("goDirection"), goNext.value));
					okButton.setOnAction(dialogHandlers.get("roomChangeEvent"));
	    			dialogGroup.setVisible(true);
				}
			}
		});
		goUpRectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(!dialogGroup.isVisible()) {
					goNext.value = "up";
					dialogText.setText(String.format(messages.get("goDirection"), goNext.value));
					okButton.setOnAction(dialogHandlers.get("roomChangeEvent"));
	    			dialogGroup.setVisible(true);
				}
			}
		});
		goDownRectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(!dialogGroup.isVisible()) {
					goNext.value = "down";
					dialogText.setText(String.format(messages.get("goDirection"), goNext.value));
					okButton.setOnAction(dialogHandlers.get("roomChangeEvent"));
	    			dialogGroup.setVisible(true);
				}
			}
		});
		
		goRoomGroup.getChildren().add(goEastRectangle);
		goRoomGroup.getChildren().add(goWestRectangle);
		goRoomGroup.getChildren().add(goNorthRectangle);
		goRoomGroup.getChildren().add(goSouthRectangle);
		goRoomGroup.getChildren().add(goUpRectangle);
		goRoomGroup.getChildren().add(goDownRectangle);
		
		setExitVisibility();
	}
	
	/**
	 * Sets the visibility of exits-markers in relation to the exits of a room.
	 */
	private void setExitVisibility() {
		goWestRectangle.setVisible(false);
		goEastRectangle.setVisible(false);
		goSouthRectangle.setVisible(false);
		goNorthRectangle.setVisible(false);
		goUpRectangle.setVisible(false);
		goDownRectangle.setVisible(false);
		
		if(game.getCurrentRoom().hasExit("east")) {
			goEastRectangle.setVisible(true);
		}
		if(game.getCurrentRoom().hasExit("west")) {
			goWestRectangle.setVisible(true);
		}
		if(game.getCurrentRoom().hasExit("north")) {
			goNorthRectangle.setVisible(true);
		}
		if(game.getCurrentRoom().hasExit("south")) {
			goSouthRectangle.setVisible(true);
		}
		if(game.getCurrentRoom().hasExit("up")) {
			goUpRectangle.setVisible(true);
		}
		if(game.getCurrentRoom().hasExit("down")) {
			goDownRectangle.setVisible(true);
		}
	}
	
	/**
	 * Displays all items held in the currentRoom inventory.
	 */
	private void renderRoomIcons() {
		for(Sprite item : game.getCurrentRoom().getItemSprites()) {
        	item.render(gc);
        }
	}
	
	/**
	 * Enables the player to collect items nearby. 
	 */
	private void checkForItemCollision() {
		ListIterator<ItemSprite> itemIterator = game.getCurrentRoom().getItemSprites().listIterator();
        while(itemIterator.hasNext()) {
        	ItemSprite item = itemIterator.next();
        	if(robin.intersects(item)) {
        		gc.setFill(Color.ANTIQUEWHITE);
        		gc.setFont(Font.font("Algerian", 15));
        		gc.fillText("\"T\"", item.getPositionX(), item.getPositionY());	
        		if(input.contains("T")) {
        			if(game.getPlayer().getInventory().addItem(item.getItem())) {
        				game.getPlayer().updatePlayerDamage(item.getItem());
        				itemIterator.remove();	
        				
        				if(game.getBoatBuilder().checkIfBoatCanBeBuilt(game.getPlayer().getInventory())) {
        					dialogText.setText(messages.get("buildBoat"));
        					okButton.setOnAction(dialogHandlers.get("buildBoatEvent"));
        					dialogGroup.setVisible(true);
        				}
        			} 	else	{
        				dialogText.setText("Inventory Full");
        				okButton.setOnAction(dialogHandlers.get("acknowledgeEvent"));
            			dialogGroup.setVisible(true);
        			}	
        		}
        	}
        }
	}
	
	/**
	 * @return Current angle between the mouse pointer and Robin
	 */
	private double getMouseAngle() {
		return Math.atan2(robin.getCenterY() - mousePosition.y, robin.getCenterX() - mousePosition.x);
	}
	
	/** 
	 * @param a Sprite A
	 * @param b Sprite B
	 * @return Angle between two sprites
	 */
	private double getAngleBetweenSprites(Sprite a, Sprite b) {
		return Math.atan2(a.getCenterY() - b.getCenterY(), a.getCenterX() - b.getCenterX());
	}
	
	/**
	 * Prints a horizontal bar to indicate a variables status (value).
	 * The variable needs to be in the range [0...100]
	 * 
	 * @param gc Where to print the bar.
	 * @param value What value the bar should display at the given moment.
	 * @param color The color of the bar.
	 * @param posX 
	 * @param posY
	 * @param width
	 * @param height
	 */
	private void printVitalityBar(GraphicsContext gc, float value, Color color, double posX, double posY, double width, double height) {
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(posX, posY, width, height);
		gc.setFill(color);
		gc.fillRect(posX+height*0.05, posY+height*0.05, (width-(2*height*0.05))*(value/100), height*0.9);
	}
	
	/**
	 * Displays the current room and the remaining playtime in minutes and seconds. 
	 */
	private void printRoomAndTimeInformation() {
		int minutes = (int) game.getTimeLeft()/60;
        int seconds = (int) game.getTimeLeft()-(minutes*60);
        
        StringBuffer sb = new StringBuffer();
        
        if(minutes < 10) sb.append("0");
        sb.append(minutes);
        sb.append(":");
        if(seconds < 10) sb.append("0");
        sb.append(seconds);
        
        gc.setFont(Font.font("Algerian", 20));
        gc.setFill(Color.ANTIQUEWHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(game.getCurrentRoom().getShortDescription(), w/2, 20);
        gc.fillText(sb.toString(), w/2, 40);
	}
	
	/**
	 * This method checks if the Creature collide with the player.
	 * When they collide the player can press "H" to trade or talk with special creatures.  
	 */
	private void checkForCreatureCollision() {
	CreatureSprite creature= 	game.getCurrentRoom().getCreatureSprite();
	String name = creature.getCreature().getName();
	
		if(robin.intersects(creature)) {
			
			if(name == "Native" || name == "Freitag" || name == "Guy Fawkes" || name == "Gandalf der Graue" ) {
				gc.setFill(Color.ANTIQUEWHITE);
				gc.setFont(Font.font("Algerian", 15));
	    		gc.fillText("\"H\"", creature.getPositionX(), creature.getPositionY());	
	    		
		   		if(input.contains("H")) {

		   			dialogText.setText(messages.get("talkAction"));
		   			dialogGroup.setVisible(true);
//		   			okButton.setOnAction(dialogHandlers.get("talkEvent"));
		   			checkTradeHandlerMethode();

		    		}
			}
    		
		}
	}
	
	/**
	 * This methode checks with which creature the player is able to talk or trade. 
	 */
	private void checkTradeHandlerMethode() {
		
		CreatureSprite creature= 	game.getCurrentRoom().getCreatureSprite();
		String name = creature.getCreature().getName();
		
			if(name == "Native") {
				okButton.setOnAction(dialogHandlers.get("nativeTalkEvent"));	
				
			} 
			else if (name == "Freitag") {
				okButton.setOnAction(dialogHandlers.get("freitagTalkEvent"));
			
			}
			else if(name == "Guy Fawkes") {
				okButton.setOnAction(dialogHandlers.get("prisonerTalkEvent"));
				
			}
			else if(name == "Gandalf der Graue") {
				okButton.setOnAction(dialogHandlers.get("mageTalkEvent"));
				
			}
	}
	
	/**
	 * This methode returns a String. The Native chooses random a String from the HashMap and returns it.
	 * @return information The String with the information.
	 */
	private String getInfosFromNative() {
		Random random = new Random();

		HashMap<Integer, String> trade = new HashMap<Integer, String>();

		trade.put(1, "The prisoner knows where the mage is. ");
		trade.put(2, "The prisoner is in the dungeon. "); // wissen wo das material ist
		trade.put(3, "Don't try to betray the natives they will attack you. "); // "-"
		trade.put(4, "Somewhere on this island is a magic-mushroom which can give you unlimited power. ");
		trade.put(5, "i have nothing for you you fool ");
		trade.put(6, "Go to the Library there is something important for you. "); // noch etwas neues
		int info = random.nextInt(6)+1;
		String information = trade.get(info);
		return information;
	}
	
	/**
	 * Ends the game
	 */
	@Override
	public void stop() {
		if(predator != null) {
			predator.stopThread();
		}	
		if(game != null) {
			game.endGame();
		}
	}

	@SuppressWarnings("unchecked")
	private void initializeInventoryGroup(final Group root) {
		inventoryGroup = new Group();
		inventoryTable = new TableView<Item>();
		final Label invSize = new Label();
		invSize.setFont(new Font("Arial", 16));
		
		inventoryTable.setItems(game.getPlayer().getInventory().getFullInventory());
		
		TableColumn<Item, String> itemNameCol = new TableColumn<Item, String>("Name");
		itemNameCol.setCellValueFactory(new Callback<CellDataFeatures<Item, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Item, String> i) {
				return new SimpleStringProperty(i.getValue().getName());
			}			
		});
		TableColumn<Item, ImageView> itemImageCol = new TableColumn<Item, ImageView>("Image");
		itemImageCol.setCellValueFactory(new Callback<CellDataFeatures<Item, ImageView>, ObservableValue<ImageView>>() {
			@Override
			public ObservableValue<ImageView> call(CellDataFeatures<Item, ImageView> i) {
				if(i.getValue() instanceof RoomKey) {
					return new SimpleObjectProperty<ImageView>(new ImageView(itemImages.get("Key")));
				} else {
					return new SimpleObjectProperty<ImageView>(new ImageView(itemImages.get(i.getValue().getName())));
				}
			}			
		});
		
		inventoryTable.getColumns().setAll(itemImageCol, itemNameCol);
		dropButton = new Button("Drop");
		dropButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Item i = inventoryTable.getSelectionModel().getSelectedItem();
				if(i != null) {
					game.getCurrentRoom().addItem(i, game.getPlayer().getPlayerSprite().getCenterX(), game.getPlayer().getPlayerSprite().getCenterY());
					game.getPlayer().getInventory().removeItem(i);	
				}				
			}		
		});
		
		eatButton = new Button("Eat");
		eatButton.setVisible(false);
		eatButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Item i = inventoryTable.getSelectionModel().getSelectedItem();
				if(i != null) {
					if(i instanceof MagicMushroom) {
						game.getPlayer().getInventory().setUnlimited();
						
					}
					game.getPlayer().eat(((Food) i).getNutrition());
					game.getPlayer().getInventory().removeItem(i);	
				}
			}
		});
		
		inventoryTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
				Item i = inventoryTable.getSelectionModel().getSelectedItem();
				if(i != null) {
					if(i instanceof Food) {
						eatButton.setVisible(true);
					}
					else { 
						eatButton.setVisible(false);
					}	
				} 
			}	
		});
		
		game.getPlayer().getInventory().getFullInventory().addListener(new ListChangeListener() {

			@Override
			public void onChanged(@SuppressWarnings("rawtypes") Change arg0) {
				invSize.setText(game.getPlayer().getInventory().getCurrentInventoryWeight() + "Kg / " + difficulty.getInventoryCapacity() + "Kg ");
			}
			
		});
		
		inventoryTable.setMaxHeight(h*0.8);
		inventoryTable.setMinWidth(w*0.1);
		inventoryTable.setMaxWidth(w*0.2);
		
		
		invSize.setText(game.getPlayer().getInventory().getCurrentInventoryWeight() + "Kg / " + difficulty.getInventoryCapacity() + "Kg ");
		
		VBox fp = new VBox();
		HBox hb = new HBox();
		fp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		hb.getChildren().addAll(dropButton, eatButton);
		fp.getChildren().addAll(invSize, inventoryTable, hb);
		
		inventoryGroup.getChildren().add(fp);
		inventoryGroup.setVisible(false);
		root.getChildren().add(inventoryGroup);
	}
		
}

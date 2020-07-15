package de.dhbw.ravensburg.zuul.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Random;

import de.dhbw.ravensburg.zuul.Difficulty;
import de.dhbw.ravensburg.zuul.Game;
import de.dhbw.ravensburg.zuul.Predator2;
import de.dhbw.ravensburg.zuul.creature.Creature;
import de.dhbw.ravensburg.zuul.item.Item;
import de.dhbw.ravensburg.zuul.room.Room;
import de.dhbw.ravensburg.zuul.room.RoomType;
//import invtest.Item;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GameApplication extends Application {
	public static final Double w = 994.0;
	public static final Double h = 1023.0;
	
	private ArrayList<String> input = new ArrayList<String>();
	private DoubleValue lastNanoTime;
	private Sprite robin, west, east, north, south;
	private GraphicsContext gc;
	private Image testForestBackground;
	private StringValue lastKeyPressed, goNext;
	private Position mousePosition;
	private Game game;
	private Thread gameThread;
	private Canvas canvas;
	private Text dialogText;
	private Button okButton, cancelButton;
	private GridPane dialogContainer;
	private StackPane sPane;
	private Group dialogGroup, goRoomGroup;
	private Rectangle yesNoBackground, goEastRectangle, goWestRectangle, goSouthRectangle, goNorthRectangle, goUpRectangle, goDownRectangle;
	private HashMap<String, String> messages;
	private HashMap<String, EventHandler<ActionEvent>> dialogHandlers;
	private Predator2 predator;
	private Difficulty difficulty;
	
	public static void main(String... args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		
		final Stage welcome = stage;
		StackPane rootb= new StackPane(); 
		
		//image
		Image image1 = new Image("/Key.PNG", true);
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
		
		//###############################################################################
		
		
				StackPane level = new StackPane();			
				Image img = new Image("/Key.PNG", true);
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
				
				
			
		
		//############################################################################################
		
		
		
		
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
		
		//Difficulty selection screen
		
		//GameInstance
//		game = new Game(Difficulty.EASY);
//		gameThread = new Thread(game, "game");
//		gameThread.start();
		
//		predator = new Predator2(game);
//		Thread pThread = new Thread(predator, "predator");
//		pThread.start();
		
//		Group root = new Group();
//		sPane = new StackPane();
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
		
		canvas = new Canvas(w, h);
		sPane.getChildren().add(canvas);
		root.getChildren().add(canvas);
		
		
		// Group for GoRoom Elements
//		initializeRoomGoup();
//		root.getChildren().add(goRoomGroup);
		
		//Yes/No Answer
		yesNoBackground = new Rectangle(220, 110, Color.BEIGE);
		dialogGroup = new Group();
		dialogText = new Text("YesNoQuestionfects of the prefWidth() method in class Group.");
		okButton = new Button("Ok");
		cancelButton = new Button("Cancel");
		dialogText.setWrappingWidth(200);
		dialogContainer = new GridPane();
		dialogContainer.add(dialogText, 0, 0, 2, 1);
		dialogContainer.add(okButton,  0, 1);
		dialogContainer.add(cancelButton, 1, 1);

		dialogGroup.setAutoSizeChildren(true);
		dialogGroup.getChildren().add(yesNoBackground);
		dialogGroup.getChildren().add(dialogContainer);
		
		
//		yesButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				game.goRoom(goNext.value);
//				setExitVisibility();
//				yesNoGroup.setVisible(false);
//				
//			}			
//		});
		
		cancelButton.setOnAction(dialogHandlers.get("acknowledgeEvent"));
		
		
//		sPane.getChildren().add(yesNoBackground);
		dialogGroup.setVisible(false);
		sPane.getChildren().add(dialogGroup);
		root. getChildren().add(dialogGroup);
		
		
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
	    
	    scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				mousePosition.x = e.getSceneX();
				mousePosition.y = e.getSceneY();
			}
	    });
		
		gc = canvas.getGraphicsContext2D();
		
		testForestBackground = new Image("forest_test.jpeg");
		
		
		//The players sprite
//		robin = game.getPlayer().getPlayerSprite();
		
		//Hitboxes around the Map
		west = new Sprite(0.0, canvas.getHeight()*0.1, 0.0, 0.0, canvas.getWidth()*0.1, canvas.getHeight()*0.8);	
		east = new Sprite(canvas.getWidth()*0.9, canvas.getHeight()*0.1, 0.0, 0.0, canvas.getWidth()*0.1, canvas.getHeight()*0.8);
		north = new Sprite(canvas.getWidth()*0.1, 0.0, 0.0, 0.0, canvas.getWidth()*0.8, canvas.getHeight()*0.1);
		south = new Sprite(canvas.getWidth()*0.1, canvas.getHeight()*0.9, 0.0, 0.0, canvas.getWidth()*0.8, canvas.getHeight()*0.1);

		
	final	AnimationTimer at = new AnimationTimer() {
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
		        
	
		        
		        gc.drawImage(testForestBackground, 0, 0);		        
		        
		        //Items in the current room  		        
		        renderRoomIcons();
		        checkForItemCollision();
		        
		        //Mouse intersects one of the Boundaries
		        if(east.intersects(mousePosition.x, mousePosition.y) && goEastRectangle.isVisible()) {
		        	gc.drawImage(new Image("eastArrow.png", canvas.getWidth()*0.1, canvas.getHeight()*0.8, false, false), east.getPositionX(), east.getPositionY());
		        }
		        if(west.intersects(mousePosition.x, mousePosition.y) && goWestRectangle.isVisible()) {
		        	gc.drawImage(new Image("westArrow.png", canvas.getWidth()*0.1, canvas.getHeight()*0.8, false, false), west.getPositionX(), west.getPositionY());
		        }
		        if(north.intersects(mousePosition.x, mousePosition.y) && goNorthRectangle.isVisible()) {
		        	gc.drawImage(new Image("northArrow.png", canvas.getWidth()*0.8, canvas.getHeight()*0.1, false, false), north.getPositionX(), north.getPositionY());
		        }
		        if(south.intersects(mousePosition.x, mousePosition.y) && goSouthRectangle.isVisible()) {
		        	gc.drawImage(new Image("southArrow.png", canvas.getWidth()*0.8, canvas.getHeight()*0.1, false, false), south.getPositionX(), south.getPositionY());
		        }
		        	        
		        //Text at the top of the Screen
		        printRoomAndTimeInformation();
		        
		        //Hunger Bar
		        printVitalityBar(gc, game.getPlayer().getHunger(), Color.SADDLEBROWN, canvas.getWidth()*0.95-200, canvas.getHeight()*0.95, 200.0, 20.0);
		        
		        //Health Bar
		        printVitalityBar(gc, game.getPlayer().getHealth(), Color.RED, canvas.getWidth()*0.95-200, canvas.getHeight()*0.95-30, 200.0, 20.0);
		        
		        if(game.getCurrentRoom().getCreatureSprite() != null) {
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

//		at.start();
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
//				System.out.println(difficulty.getInventoryCapacity()); // geht
				
				
				
				
				gameThread = new Thread(game, "game");
				gameThread.start();
				predator = new Predator2(game);
				Thread pThread = new Thread(predator, "predator");
				pThread.start();
				initializeRoomGoup();
				robin = game.getPlayer().getPlayerSprite();
				root.getChildren().add(goRoomGroup);
				at.start();
				welcome.setTitle("Robinson Cruizer");
				welcome.getIcons().add(new Image("Icon.png"));
				welcome.setScene(scene); // Freddis Scene muss hier rein
				welcome.show();
			}
		});
		
//		stage.setTitle("Robinson Cruizer");
//		stage.getIcons().add(new Image("Icon.png"));
////		stage.setFullScreen(true);
//		stage.show();
	}
	
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
				dialogGroup.setVisible(false);
			}
		});
		
//		dialogHandlers.put("talkEvent", new EventHandler<ActionEvent>(){
//			@Override
//			public void handle(ActionEvent e) {
//				
//				
//				checkTradeHandlerMethode();
//			}
//		});
		
		dialogHandlers.put("freitagTalkEvent", new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				
				
				
//				dialogText.setText("Nice to meet you foreign. My name is Freitag and i see you are in trouble. "
//						+ "I saw how you ship sunk and you almost died in the water but thanks to god you are alive. "
//						+ "You are on a island and i believe you want to escape from here. "
//						+ "Hmmm.... To escape you need a new boat. I think you can find on this island everything what you need to build a new one."
//						+ " You can ask the Natives on the island, they will know where you can find your equipment. "
//						+ "oh but you shouldn't make them angry because then they will try to kill you. "
//						+ "But only when you make them angry so try to be polite to them, also you can try to find the Mage somewhere."
//						+ " I don't know where he is but i am sure he can help you. "
//						+ "Oh but you must be careful. "
//						+ "On this island are many hunters who want to kill you because they think you are against their god. "
//						+ "So try to avoid them. "
//						+ "It was nice to meet you, i hope you will survive. Good bye. " );
				dialogText.setText("Hello foreign. you have to find items to build a boat. ");
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
	}
	
	private void populateMessageHashMap() {
		messages.put("roomLocked", "The room you're trying to access is locked! Try finding a key.");
		messages.put("unlockRoom", "Would you like to unlock this room now?");
		messages.put("inventoyFull", "You can't carry any more!");
		messages.put("trapDoor", "You're trying to enter through a trapdoor. Continue?");
		messages.put("boatReady", "You're ready to build a boat.");
		messages.put("goDirection", "Would you like to go %s?");
		messages.put("talkAction", "Would you like to talk?");
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
	
	
	
	
	private void initializeRoomGoup() {
		goRoomGroup = new Group();
		
		goEastRectangle = new Rectangle(canvas.getWidth()*0.9, canvas.getHeight()*0.1, canvas.getWidth()*0.1, canvas.getHeight()*0.8);
		goWestRectangle = new Rectangle(0.0, canvas.getHeight()*0.1, canvas.getWidth()*0.1, canvas.getHeight()*0.8);
		goNorthRectangle = new Rectangle(canvas.getWidth()*0.1, 0.0, canvas.getWidth()*0.8, canvas.getHeight()*0.1);
		goSouthRectangle = new Rectangle(canvas.getWidth()*0.1, canvas.getHeight()*0.9, canvas.getWidth()*0.8, canvas.getHeight()*0.1);
//		Rectangle goUpRectangle = new Rectangle(canvas.getWidth()*0.9, 0.0, canvas.getWidth()/10, canvas.getHeight());
//		Rectangle goDownRectangle = new Rectangle(canvas.getWidth()*0.9, 0.0, canvas.getWidth()/10, canvas.getHeight());
		
		// make rectangles invisible
		goEastRectangle.setFill(Color.TRANSPARENT);
		goWestRectangle.setFill(Color.TRANSPARENT);
		goNorthRectangle.setFill(Color.TRANSPARENT);
		goSouthRectangle.setFill(Color.TRANSPARENT);
		
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
		
		goRoomGroup.getChildren().add(goEastRectangle);
		goRoomGroup.getChildren().add(goWestRectangle);
		goRoomGroup.getChildren().add(goNorthRectangle);
		goRoomGroup.getChildren().add(goSouthRectangle);
		
		setExitVisibility();
	}
	
	private void setExitVisibility() {
		goWestRectangle.setVisible(false);
		goEastRectangle.setVisible(false);
		goSouthRectangle.setVisible(false);
		goNorthRectangle.setVisible(false);
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
	}
	
	private void renderRoomIcons() {
		for(Sprite item : game.getCurrentRoom().getItemSprites()) {
        	item.render(gc);
        }
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
        				itemIterator.remove();	
        			} 	else	{
        				dialogText.setText("Inventory Full");
            			dialogGroup.setVisible(true);
        			}	
        		}
        	}
        }
	}
	
	private double getMouseAngle() {
		return Math.atan2(robin.getCenterY() - mousePosition.y, robin.getCenterX() - mousePosition.x);
	}
	
	private double getAngleBetweenSprites(Sprite a, Sprite b) {
		return Math.atan2(a.getCenterY() - b.getCenterY(), a.getCenterX() - b.getCenterX());
	}
	
	private void printVitalityBar(GraphicsContext gc, float value, Color color, double posX, double posY, double width, double height) {
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(posX, posY, width, height);
		gc.setFill(color);
		gc.fillRect(posX+height*0.05, posY+height*0.05, (width-(2*height*0.05))*(value/100), height*0.9);
	}
	
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
        gc.fillText(game.getCurrentRoom().getShortDescription(), canvas.getWidth()/2, 20);
        gc.fillText(sb.toString(), canvas.getWidth()/2, 40);
	}
	
	@Override
	public void stop() {
		game.endGame();
		predator.stopThread();
	}
}

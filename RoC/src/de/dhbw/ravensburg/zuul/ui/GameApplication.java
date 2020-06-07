package de.dhbw.ravensburg.zuul.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;

import de.dhbw.ravensburg.zuul.Command;
import de.dhbw.ravensburg.zuul.Difficulty;
import de.dhbw.ravensburg.zuul.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
	private Text yesNoQuestion;
	private Button yesButton, noButton;
	private GridPane yesNoContainer;
	private StackPane sPane;
	private Group yesNoGroup, goRoomGroup;
	private Rectangle yesNoBackground, goEastRectangle, goWestRectangle, goSouthRectangle, goNorthRectangle, goUpRectangle, goDownRectangle;
	
	
	public static void main(String... args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
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
		game = new Game(Difficulty.EASY);
		gameThread = new Thread(game, "game");
		gameThread.start();
		
		Group root = new Group();
		sPane = new StackPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		canvas = new Canvas(w, h);
		sPane.getChildren().add(canvas);
		root.getChildren().add(canvas);
		
		
		// Group for GoRoom Elements
		initializeRoomGoup();
		root.getChildren().add(goRoomGroup);
		
		//Yes/No Answer
		yesNoBackground = new Rectangle(220, 110, Color.BEIGE);
		yesNoGroup = new Group();
		yesNoQuestion = new Text("YesNoQuestionfects of the prefWidth() method in class Group.");
		yesButton = new Button("Yes");
		noButton = new Button("No");
		yesNoQuestion.setWrappingWidth(200);
		yesNoContainer = new GridPane();
		yesNoContainer.add(yesNoQuestion, 0, 0, 2, 1);
		yesNoContainer.add(yesButton,  0, 1);
		yesNoContainer.add(noButton, 1, 1);

		yesNoGroup.setAutoSizeChildren(true);
		yesNoGroup.getChildren().add(yesNoBackground);
		yesNoGroup.getChildren().add(yesNoContainer);
		
		yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				game.goRoom(goNext.value);
				setExitVisibility();
				yesNoGroup.setVisible(false);
				
			}			
		});
		
		noButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				yesNoGroup.setVisible(false);			
			}			
		});
		
		
//		sPane.getChildren().add(yesNoBackground);
		yesNoGroup.setVisible(false);
		sPane.getChildren().add(yesNoGroup);
		root. getChildren().add(yesNoGroup);
		
		
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
		Image robinImage = new Image("Robinson.PNG", 125, 125, true, true);
		
		//The players sprite
		robin = new Sprite(400.0, 400.0, 50.0, 50.0, robinImage);
		
		//Hitboxes around the Map
		west = new Sprite(0.0, canvas.getHeight()*0.1, 0.0, 0.0, canvas.getWidth()*0.1, canvas.getHeight()*0.8);	
		east = new Sprite(canvas.getWidth()*0.9, canvas.getHeight()*0.1, 0.0, 0.0, canvas.getWidth()*0.1, canvas.getHeight()*0.8);
		north = new Sprite(canvas.getWidth()*0.1, 0.0, 0.0, 0.0, canvas.getWidth()*0.8, canvas.getHeight()*0.1);
		south = new Sprite(canvas.getWidth()*0.1, canvas.getHeight()*0.9, 0.0, 0.0, canvas.getWidth()*0.8, canvas.getHeight()*0.1);

		
		AnimationTimer at = new AnimationTimer() {
			double speed = 200.0;
			
			@Override
			public void handle(long currentNanoTime) {
				// calculate time since last update.
		        double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
		        lastNanoTime.value = currentNanoTime;
				
		        //Calculate the the relative angle that robin needs to have to face the mouse pointer
		        double mouseAngle = getMouseAngle();

		        robin.setVelocity(0,0);
		        
		        //moveRobin according to mouseDirection
		        if(input.contains("W")) {
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
		        }     
		        robin.update(elapsedTime);
	
		        
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
		        
		        
		        //Rotate Robin according to mouseDirection and Print him to the canvas.
		        robin.render(gc, Math.toDegrees(mouseAngle+Math.PI/2));
			}				
		};

		at.start();
		
		stage.setTitle("Robinson Cruizer");
		stage.getIcons().add(new Image("Icon.png"));
//		stage.setFullScreen(true);
		stage.show();
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
				if(!yesNoGroup.isVisible()) {
					yesNoQuestion.setText("Would you like to go east?");
					goNext.value = "east";
	    			yesNoGroup.setVisible(true);
				}
			}
		});
		goWestRectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(!yesNoGroup.isVisible()) {
					yesNoQuestion.setText("Would you like to go west?");
					goNext.value = "west";
	    			yesNoGroup.setVisible(true);
				}
			}
		});
		goNorthRectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(!yesNoGroup.isVisible()) {
					yesNoQuestion.setText("Would you like to go north?");
					goNext.value = "north";
	    			yesNoGroup.setVisible(true);
				}
			}
		});
		goSouthRectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(!yesNoGroup.isVisible()) {
					yesNoQuestion.setText("Would you like to go south?");
					goNext.value = "south";
	    			yesNoGroup.setVisible(true);
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
	
	private void checkForItemCollision() {
		ListIterator<ItemSprite> itemIterator = game.getCurrentRoom().getItemSprites().listIterator();
        while(itemIterator.hasNext()) {
        	ItemSprite item = itemIterator.next();
        	if(robin.intersects(item)) {
        		gc.setFill(Color.ANTIQUEWHITE);
        		gc.setFont(Font.font("Algerian", 15));
        		gc.fillText("\"T\"", item.getPositionX(), item.getPositionY());	
        		if(input.contains("T")) itemIterator.remove();	
        		game.getPlayer().getInventory().addItem(item.getItem());
        	}
        }
	}
	
	private double getMouseAngle() {
		return Math.atan2(robin.getCenterY() - mousePosition.y, robin.getCenterX() - mousePosition.x);
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
	}
}
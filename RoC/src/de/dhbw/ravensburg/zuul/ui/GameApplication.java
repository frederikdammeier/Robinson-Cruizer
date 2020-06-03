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
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameApplication extends Application {
	public static final Double w = 994.0;
	public static final Double h = 1023.0;
	
	private ArrayList<String> input = new ArrayList<String>();
	private DoubleValue lastNanoTime;
	private Sprite robin;
	private GraphicsContext gc;
	private Image testForestBackground;
	private Sprite west, east, north, south;
	private StringValue lastKeyPressed;
	private Position mousePosition;
	private Game game;
	private Thread gameThread;
	private Canvas canvas;
	
	public static void main(String... args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		lastNanoTime = new DoubleValue();
		lastKeyPressed = new StringValue();
		lastNanoTime.value = 0.0;
		lastKeyPressed.value = "RIGHT";
		
		mousePosition = new Position();
		mousePosition.x = 0.0;
		mousePosition.y = 0.0;
		
		game = new Game(Difficulty.EASY);
		gameThread = new Thread(game, "game");
		gameThread.start();
		
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		canvas = new Canvas(w, h);
		root.getChildren().add(canvas);
		
		
		
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
		west = new Sprite(0.0, 0.0, 0.0, 0.0, canvas.getWidth()/10, canvas.getHeight());
		east = new Sprite(canvas.getWidth()*0.9, 0.0, 0.0, 0.0, canvas.getWidth()/10, canvas.getHeight());
		north = new Sprite(0.0, 0.0, 0.0, 0.0, canvas.getWidth(), canvas.getHeight()/10);
		south = new Sprite(0.0, canvas.getHeight()*0.9, 0.0, 0.0, canvas.getWidth(), canvas.getHeight()/10);
		
//		gc.drawImage(testForestBackground, 0, 0);
		
		
		
		AnimationTimer at = new AnimationTimer() {
			double speed = 200.0;
			
			@Override
			public void handle(long currentNanoTime) {
				// calculate time since last update.
		        double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
		        lastNanoTime.value = currentNanoTime;
				
		        double mouseAngle = Math.atan2(robin.getCenterY() - mousePosition.y, robin.getCenterX() - mousePosition.x);
		        
		        
		        
		        robin.setVelocity(0,0);
		        
		        //Move Robin according to key presses
//		        if (input.contains("LEFT") && !robin.intersects(west))
//		        	robin.addVelocity(-150,0);
//		        if (input.contains("RIGHT") && !robin.intersects(east))
//		        	robin.addVelocity(150,0);
//		        if (input.contains("UP") && !robin.intersects(north))
//		        	robin.addVelocity(0,-150);
//		        if (input.contains("DOWN") && !robin.intersects(south))
//		        	robin.addVelocity(0,150);
		        
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
		        for(Sprite item : game.getCurrentRoom().getItemSprites()) {
		        	item.render(gc);
		        }
		        
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
		        	        
		        //Text at the top of the Screen
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
		        
		        //Hunger Bar
		        printVitalityBar(gc, game.getPlayer().getHunger(), Color.SADDLEBROWN, canvas.getWidth()*0.95-200, canvas.getHeight()*0.95, 200.0, 20.0);
		        
		        //Health Bar
		        printVitalityBar(gc, game.getPlayer().getHealth(), Color.RED, canvas.getWidth()*0.95-200, canvas.getHeight()*0.95-30, 200.0, 20.0);
		        
		        
		        //Rotate Robin according to mouseDirection and Print him to the canvas.
		        robin.render(gc, Math.toDegrees(mouseAngle+Math.PI/2));
		        
		        //Rotate Robin according to key presses
//		        if(lastKeyPressed.value.equals("RIGHT")) {
//		        	robin.render(gc, -90.0);
//		        } else if(lastKeyPressed.value.equals("UP")){
//		        	robin.render(gc, 180.0);
//		        } else if(lastKeyPressed.value.equals("LEFT")) {
//		        	robin.render(gc, -270.0);
//		        } else {
//		        	robin.render(gc);
//		        }
			}
			
			private void printVitalityBar(GraphicsContext gc, float value, Color color, double posX, double posY, double width, double height) {
				gc.setFill(Color.ANTIQUEWHITE);
				gc.fillRect(posX, posY, width, height);
				gc.setFill(color);
				gc.fillRect(posX+height*0.05, posY+height*0.05, (width-(2*height*0.05))*(value/100), height*0.9);
			}
			
		};

		at.start();
		
		stage.setTitle("Robinson Cruizer");
		stage.getIcons().add(new Image("Icon.png"));
//		stage.setFullScreen(true);
		stage.show();
	}
	
	@Override
	public void stop() {
		game.endGame();
	}
}

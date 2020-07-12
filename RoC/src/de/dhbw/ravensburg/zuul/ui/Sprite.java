package de.dhbw.ravensburg.zuul.ui;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class Sprite
{
    private Image image;
    private double positionX;
	private double positionY;    
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    
    public Sprite(double pX, double pY, double vX, double vY, Image image) {
    	positionX = pX;
    	positionY = pY;
    	velocityX = vX;
    	velocityY = vY;
    	this.image = image;
    	width = image.getWidth();
    	height = image.getHeight();
    }
    
    public Sprite(double pX, double pY, double vX, double vY, double w, double h) {
    	positionX = pX;
    	positionY = pY;
    	velocityX = vX;
    	velocityY = vY;
    	width = w;
    	height = h;
    }
 
    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }
 
    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }
    
    public void render(GraphicsContext gc, Double angle) {
    	drawRotatedImage(gc, image, angle, positionX, positionY);
    }
 
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }
 
    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public boolean intersects(double mouseX, double mouseY) {
    	return this.getBoundary().intersects(mouseX, mouseY, 1, 1);
    }
    
    public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setPosition(double x, double y) {
    	positionX = x;
    	positionY = y;
    }
	
	public void setVelocity(double x, double y) {
		velocityX = x;
		velocityY = y;
	}
	
	public void addVelocity(double x, double y) {
		velocityX += x;
		velocityY += y;
	}
    
    public double getPositionX() {
		return positionX;
	}
    
    public double getCenterX() {
    	return positionX + ( width / 2 );
    }
    
    public double getCenterY() {
    	return positionY + ( height / 2 );
    }

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	/**
	 * from: https://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas
	 * 
     * Draws an image on a graphics context.
     *
     * The image is drawn at (tlpx, tlpy) rotated by angle pivoted around the point:
     *   (tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2)
     *
     * @param gc the graphics context the image is to be drawn on.
     * @param angle the angle of rotation.
     * @param tlpx the top left x co-ordinate where the image will be plotted (in canvas co-ordinates).
     * @param tlpy the top left y co-ordinate where the image will be plotted (in canvas co-ordinates).
     */
    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); // back to original state (before rotation)
    }
    
    /**
     * from: https://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas
     * 
     * Sets the transform for the GraphicsContext to rotate around a pivot point.
     *
     * @param gc the graphics context the transform to applied to.
     * @param angle the angle of rotation.
     * @param px the x pivot co-ordinate for the rotation (in canvas co-ordinates).
     * @param py the y pivot co-ordinate for the rotation (in canvas co-ordinates).
     */
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
    
    
    /**
     * Returns the euklidic distance from the sprites center to given coordinates
     * 
     * @param x
     * @param y
     * @return
     */
    public double distanceTo(double x, double y) {
    	return Math.sqrt(Math.pow(x - getCenterX(), 2) + Math.pow(y - getCenterY(), 2));
    }
}

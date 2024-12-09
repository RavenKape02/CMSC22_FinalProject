package application;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Sprite {
	protected Image img;
	protected double xPos, yPos;
	protected int dx, dy;
	protected int lives;
	protected double width;
	protected double height;
	protected boolean alive;
	
	//initiating sprites and positions
    public Sprite(double xPos, double yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		this.alive = true;
	}
    //set sizes
	private void setSize(){
		this.width = this.img.getWidth();
        this.height = this.img.getHeight();
	}
	
	//method that will check for collision of two sprites
	public boolean collidesWith(Sprite rect2)	{
		Rectangle2D rectangle1 = this.getBounds();
		Rectangle2D rectangle2 = rect2.getBounds();

		return rectangle1.intersects(rectangle2);
	}
	
	//load images
	protected void loadImage(Image img){
		try{
			this.img = img; 
	        this.setSize();
		} catch(Exception e)	{
			e.printStackTrace();
		}
	}		
	//render graphics/images
	public void render(GraphicsContext gc){
        gc.drawImage( this.img, this.xPos, this.yPos );
    }
	
	//getting bounds for collis
	public Rectangle2D getBounds(){
		return new Rectangle2D(this.xPos, this.yPos, this.width, this.height);
	}
	
	//setters and getters
	public Image getImage(){
		return this.img;
	}

	public double getXPos(){
		return this.xPos;
	}

	public double getYPos(){
		return this.yPos;
	}

	public void setXPos(int xPos){
		this.xPos = xPos;
	}

	public void setYPos(int yPos){
		this.yPos = yPos;
	}
	
	public void setDXPos(int dx){
		this.dx = dx;
	}

	public void setDYPos(int dy){
		this.dy = dy;
	}
	
	public void turnDead() {
		this.alive = false;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
}
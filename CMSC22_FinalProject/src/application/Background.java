//package application;
//
//import java.util.Random;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.paint.Color;
//
//public class Background {
//	int posX, posY;
//	private int h, w, r, g, b;
//	private double opacity;
//
//	public Background() {		
//		
//		Random rand = new Random();
//		posX = rand.nextInt(512);
//		posY = 0;
//		
//		w = rand.nextInt(5)+1;
//		h = rand.nextInt(5)+1;
//		r = rand.nextInt(100)+150;
//		g = rand.nextInt(100)+150;
//		b = rand.nextInt(100)+150;
//		opacity = rand.nextFloat();
//		if(opacity < 0)opacity*=1;
//		if(opacity > 0.5)opacity = 0.5;
//	}
//	
//	public void draw(GraphicsContext gc) {
//		if(opacity > 0.8) opacity -=0.01;
//		if(opacity < 0.1) opacity +=0.01;
//		gc.setFill(Color.rgb(r,g,b,opacity));
//		gc.fillOval(posX, posY, w, h);
//		posY+=20;
//	}
//
//}

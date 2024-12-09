package application;

//importing libraries needed
import javafx.stage.Stage;

public class Game {
	public final static int WINDOW_WIDTH = 600;
	public final static int WINDOW_HEIGHT = 400;
	
	public Game() {
		//initiate size and bg here
	}
	
	public void setStage(Stage stage) {
		stage.setTitle("space");
		stage.setResizable(false);
		Menu menu = new Menu(stage, WINDOW_WIDTH, WINDOW_HEIGHT);
		menu.setStage(stage);
	}
}
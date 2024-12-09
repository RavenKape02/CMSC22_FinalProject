package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*************************************************************************************************************************
*
* CMSC 22 Object-Oriented Programming
* Multithreading (with GUI): Play as a knight and an elf and avoid the moving fires and slimes.
* 
* Student Number: 2023-04048
* Section: UV-2L
* @author: Magpili, Yssa Gabrielle D.
* @created_date 2024-11-23-11:30PM (date of submission)
* 
*************************************************************************************************************************/
import application.Game;

public class Main extends Application {
		
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) 
    {
    	//call game to start
        Game game = new Game();
    	game.setStage(stage);
    }
}

package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game {
	private Scene scene;
    private Parent root;
    private Canvas canvas;
    private Stage stage;

    public static final int WINDOW_WIDTH = 568;
    public static final int WINDOW_HEIGHT = 568;
    
    public Game() {
        this.root = new Group(); // Group for general scene handling
        this.scene = new Scene(root); // Attach the root to the scene
        this.canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT); // Create canvas
        this.stage = new Stage();
        stage.setScene(scene);
        ((Group) this.root).getChildren().add(this.canvas); // Add the canvas to the group root
    }
    
    public void startGame() {
    	this.stage.show();
    }
    
    public void mainMenu() {
    	StackPane mainMenu = new StackPane();
    	VBox menuLayout = new VBox(20);
    	
    	Button newgame = new Button("New Game");
    	Button about = new Button("About");
    	Button developers = new Button("Developers");
    	
    	StackPane.setAlignment(newgame, Pos.BOTTOM_LEFT);
    	StackPane.setAlignment(about, Pos.BOTTOM_CENTER);
    	StackPane.setAlignment(developers, Pos.BOTTOM_RIGHT);
    	
    	newgame.setOnAction(e->{
    		//function for starting a game
    	});
    	
    	about.setOnAction(e->{
    		aboutPage();
    	});
    	
    	developers.setOnAction(e->{
    		developersPage();
    	});
    	
    	mainMenu.getChildren().addAll(newgame, about, developers);
    	
    	
    	this.root = new Group();
    	this.scene = new Scene(mainMenu, 1280, 720);
    	this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	stage.setScene(scene);
    	this.stage.show();
    }
    
    public void aboutPage() {
    	StackPane about = new StackPane();
    	
    	Text aboutText = new Text("This is just a simple shoot em up game");
    	aboutText.setFont(Font.font("Showcard Gothic", 20));
    	about.getChildren().add(aboutText);
    	StackPane.setAlignment(aboutText, Pos.TOP_LEFT);
    	
    	Button goBack = new Button("Go Back to Main Menu");
    	goBack.setAlignment(Pos.BOTTOM_LEFT);
    	about.getChildren().add(goBack);
    	StackPane.setAlignment(goBack, Pos.BOTTOM_LEFT);
    	
    	goBack.setOnAction(e->{
    		mainMenu();
    	});
    	
    	this.scene = new Scene(about, 1280, 720);
    	this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	stage.setScene(scene);
    	this.stage.show();
    }
    
    public void developersPage() {
        StackPane developers = new StackPane();
        
        VBox content = new VBox(10);
        content.setAlignment(Pos.TOP_LEFT); 
        
        Label header = new Label("DEVELOPERS");
        header.setFont(Font.font("Showcard Gothic", 20));
        
        Text yssa = new Text("Yssa Gabrielle Magpili");
        Text rin = new Text("Tanya Marinelle Manaoat");
        Text raven = new Text("John Raven Caduyac");
        
        content.getChildren().addAll(header, yssa, rin, raven);
        
        developers.getChildren().add(content);

        Button goBack = new Button("Go Back to Main Menu");
        StackPane.setAlignment(goBack, Pos.BOTTOM_LEFT);
        developers.getChildren().add(goBack);
        
        goBack.setOnAction(e -> mainMenu());
        
        this.scene = new Scene(developers, 1280, 720);
        this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        this.stage.show();
    }

}

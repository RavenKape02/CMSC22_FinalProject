package application;

import java.io.IOException;



import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

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
    	Button winningSceneButton = new Button("Winning Scene");
    	
    	StackPane.setAlignment(newgame, Pos.BOTTOM_LEFT);
    	StackPane.setAlignment(about, Pos.BOTTOM_CENTER);
    	StackPane.setAlignment(developers, Pos.BOTTOM_RIGHT);
    	StackPane.setAlignment(winningSceneButton, Pos.TOP_RIGHT); 
    	
    	newgame.setOnAction(e->{
    		//function for starting a game
    	});
    	
    	about.setOnAction(e->{
    		aboutPage();
    	});
    	
    	developers.setOnAction(e->{
    		developersPage();
    	});
    	
    	winningSceneButton.setOnAction(e->{
    		winningScene();
    	});
    	
    	
    	mainMenu.getChildren().addAll(newgame, about, developers, winningSceneButton);
    	
    	
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
    
    public void winningScene() {
        StackPane winningScene = new StackPane();
        String contentGameOver = "GAME OVER!";
        String contentWinner = "Winner: Player";

        // Text node for "GAME OVER!"
        Text animatedTextGameOver = new Text();
        animatedTextGameOver.setStyle("-fx-font-size: 100px; -fx-fill: white;");
        animatedTextGameOver.setFont(Font.font("Showcard Gothic", 20));
        StackPane.setAlignment(animatedTextGameOver, Pos.TOP_CENTER);
        StackPane.setMargin(animatedTextGameOver, new Insets(100, 0, 0, 0));

        // Text node for "Winner: Player"
        Text animatedTextWinner = new Text();
        animatedTextWinner.setStyle("-fx-font-size: 50px; -fx-fill: white;");
        animatedTextWinner.setFont(Font.font("Showcard Gothic", 20));
        StackPane.setAlignment(animatedTextWinner, Pos.TOP_CENTER);
        StackPane.setMargin(animatedTextWinner, new Insets(200, 0, 0, 0)); 

        // BG
        Image bgImage = new Image(getClass().getResource("winning_scene_bg.gif").toExternalForm());
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setFitWidth(1280);
        bgImageView.setFitHeight(720);
        bgImageView.setPreserveRatio(false);
        
        Button goBack = new Button("Go Back to Main Menu");
	    goBack.setPrefWidth(200);
	    goBack.setPrefHeight(50);
	    goBack.setStyle("-fx-background-color: white; -fx-text-fill: #3498db; -fx-font-size: 16px; -fx-border-color: #2980b9; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
	    StackPane.setAlignment(goBack, Pos.BOTTOM_LEFT);
	    StackPane.setMargin(goBack, new Insets(20, 0, 20, 20));
	    goBack.setOnAction(e -> mainMenu());


        // Timeline for "GAME OVER!"
        Timeline timelineGameOver = new Timeline();
        final StringBuilder displayedTextGameOver = new StringBuilder();
        for (int i = 0; i < contentGameOver.length(); i++) {
            final int index = i;
            KeyFrame keyFrame = new KeyFrame(
                Duration.millis(100 * (index + 1)),
                e -> {
                    displayedTextGameOver.append(contentGameOver.charAt(index));
                    animatedTextGameOver.setText(displayedTextGameOver.toString());
                }
            );
            timelineGameOver.getKeyFrames().add(keyFrame);
        }

        Timeline timelineWinner = new Timeline();
        final StringBuilder displayedTextWinner = new StringBuilder();
        for (int i = 0; i < contentWinner.length(); i++) {
            final int index = i;
            KeyFrame keyFrame = new KeyFrame(
                Duration.millis(100 * (index + 1)),
                e -> {
                    displayedTextWinner.append(contentWinner.charAt(index));
                    animatedTextWinner.setText(displayedTextWinner.toString());
                }
            );
            timelineWinner.getKeyFrames().add(keyFrame);
        }

        timelineGameOver.setOnFinished(e -> timelineWinner.play());
        timelineGameOver.play();

        winningScene.getChildren().addAll(bgImageView, animatedTextGameOver, animatedTextWinner, goBack);
        Scene scene = new Scene(winningScene, 1280, 720);
        stage.setScene(scene);
        this.stage.show();
    }


}

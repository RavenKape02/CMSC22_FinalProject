package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;

public class Developers {

    public static Scene getDevelopersScene(Stage primaryStage) {
        Menu menu = new Menu(600, 400);
        // StackPane layout to allow placing the background image below the VBox
        StackPane layout = new StackPane();
        layout.setAlignment(Pos.CENTER);

        // Load the GIF as background using ClassLoader
        Image bgImage = null;
        ImageView bgImageView = null;

        try {
            // Adjust the resource path based on your project structure
            URL resource = Developers.class.getClassLoader().getResource("resources/devBackground.gif");
            if (resource != null) {
                System.out.println("Resource found at: " + resource.toExternalForm());
                bgImage = new Image(resource.toExternalForm());
                bgImageView = new ImageView(bgImage);
                bgImageView.setFitWidth(600);  // Adjust width to fit the scene
                bgImageView.setFitHeight(400); // Adjust height to fit the scene
            } else {
                System.out.println("Background GIF not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If the background image was found, add it to the layout
        if (bgImageView != null) {
            layout.getChildren().add(bgImageView); // Add the background image to the layout
        }

        // VBox layout for the content (title, developer names, images, back button)
        VBox layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        layout1.getStyleClass().add("developers-layout");

        // Title
        Text title = new Text("DEVELOPERS");
        title.getStyleClass().add("title-text");

        // Developer images (resizing and placing them horizontally)
        Image devImage1 = new Image("resources/dev1.png"); // Image path
        Image devImage2 = new Image("resources/dev2.png"); // Image path
        Image devImage3 = new Image("resources/dev3.png"); // Image path

        ImageView devImageView1 = new ImageView(devImage1);
        ImageView devImageView2 = new ImageView(devImage2);
        ImageView devImageView3 = new ImageView(devImage3);

        // Resize images (adjust the size to be smaller)
        devImageView1.setFitWidth(120);  
        devImageView1.setFitHeight(120);

        devImageView2.setFitWidth(120);  
        devImageView2.setFitHeight(120);

        devImageView3.setFitWidth(120);  
        devImageView3.setFitHeight(120);

        // Create an HBox to arrange the images horizontally
        HBox imageBox = new HBox(0);  // 0px space between images
        imageBox.setAlignment(Pos.CENTER);  // Center the images horizontally
        imageBox.getChildren().addAll(devImageView1, devImageView2, devImageView3);  // Add images to HBox

        // Developer names (under the images)
        Text dev1 = new Text("CADUYAC, John Raven");
        Text dev2 = new Text("MAGPILI, Yssa Gabrielle");
        Text dev3 = new Text("MANAOAT, Tanya Marinelle");
        dev1.getStyleClass().add("developer-name");
        dev2.getStyleClass().add("developer-name");
        dev3.getStyleClass().add("developer-name");

        // Back to Menu button
        Button backButton = new Button("Back to Menu");
        backButton.setId("back-button");  // Add ID to backButton for specific styling
        backButton.setOnAction(e -> menu.setStage(primaryStage));

        // Add components to layout1 (the VBox)
        layout1.getChildren().addAll(title, imageBox, dev1, dev2, dev3, backButton);

        // Add both the background image and the content layout to the StackPane
        layout.getChildren().add(layout1); // Only adds layout1 here

        // Scene setup
        Scene developersScene = new Scene(layout, 600, 400);
        developersScene.getStylesheets().add(Developers.class.getResource("application.css").toExternalForm());

        return developersScene;
    }
}

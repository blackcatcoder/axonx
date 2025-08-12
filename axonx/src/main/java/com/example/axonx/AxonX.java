package com.example.axonx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.robot.Robot;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

import javafx.application.Platform;

public class AxonX extends Application {

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new javafx.scene.layout.StackPane(), 200, 100));
        stage.setTitle("Auto Double Click");
        stage.show();

        // Use JavaFX Robot
        Robot robot = new Robot();

        // Delay so UI loads before clicking
        PauseTransition delay = new PauseTransition(Duration.seconds(1200));
        delay.setOnFinished(event -> {
            double x = 25;
            double y = 25;

            // Move mouse to the position
            robot.mouseMove(x, y);

            // First click
            robot.mousePress(javafx.scene.input.MouseButton.PRIMARY);
            robot.mouseRelease(javafx.scene.input.MouseButton.PRIMARY);

            // Short delay for double click
            robot.mousePress(javafx.scene.input.MouseButton.PRIMARY);
            robot.mouseRelease(javafx.scene.input.MouseButton.PRIMARY);

            System.out.println("Double-clicked at (" + x + ", " + y + ")");

            Platform.exit();
        });
        delay.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

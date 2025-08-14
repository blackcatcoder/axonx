package com.example.axons;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;


// 1p -> 60
// 10p -> 600
// 20p -> 1200
// 30p -> 1800
// 40p -> 2400

// 47 -> 57 -> 07 -> 12


// 15'


// auto click after 25'

// guest: auto clock after 15', because after 10' we already trigger the first 1.
// conclusion: we can trigger after every 10'

// test 3:

/*
test case 1:
-

test 3:
- turn off screen
- set 14'
- then open screen
- expect that: your os still working and your code execute successfully

 */


public class AxonX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private void click(Robot robot) {
        // click
        robot.mousePress(javafx.scene.input.MouseButton.PRIMARY);
        robot.mouseRelease(javafx.scene.input.MouseButton.PRIMARY);
    }

    private void doubleClick(Robot robot) {
        // First click
        click(robot);

        // Second click
        click(robot);
    }

    private void execute(Data data) {

        System.out.println("seconds: " + data.seconds);

        System.out.println("start time: " + LocalDateTime.now().toLocalTime());

        Robot robot = new Robot();

        // Delay so UI loads before clicking
        PauseTransition delay = new PauseTransition(Duration.seconds(data.seconds));

        delay.setOnFinished(event -> {
            // 25 - 25
            // 25 - 125
            // 25 - 225
            double x = data.x;
            double y = data.y;

            // Move mouse to the position
            robot.mouseMove(x, y);

            // double click
            doubleClick(robot);

            System.out.println("Double-clicked at (" + x + ", " + y + ")");

            System.out.println("end time: " + LocalDateTime.now().toLocalTime());

            if (data.seconds == 3000) {
                System.out.println("the last one -> end program");
                Platform.exit();
            }

        });

        delay.play();
    }

    @Override
    public void start(Stage stage) {

        //Data data0 = new Data(25, 625, 6);
       // execute(data0);


        Data data1 = new Data(25, 25, 600);
        execute(data1);

        Data data2 = new Data(25, 125, 1200);
        execute(data2);

        Data data3 = new Data(25, 225, 1800);
        execute(data3);

        Data data4 = new Data(25, 325, 2400);
        execute(data4);

        Data data5 = new Data(25, 425, 3000);
        execute(data5);

//        Data data6 = new Data(25, 525, 3600);
//        execute(data6);
//
//        Data data7 = new Data(25, 625, 4200);
//        execute(data7);

        // start at 6:00


    }

    private static class Data {
        public int x;
        public int y;
        public int seconds;

        public Data(int x, int y, int seconds) {
            this.x = x;
            this.y = y;
            this.seconds = seconds;
        }
    }
}

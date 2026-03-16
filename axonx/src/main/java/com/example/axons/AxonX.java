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

test 3:
- turn off screen
- set 14'
- then open screen
- expect that: your os still working and your code execute successfully

 */


public class AxonX extends Application {
    //private static final int TIMER_PARAM = 2;
    private static final int TIMER_PARAM = 100; //100


    private static final int LOOP_LIMIT = 6;
    //private static final int LOOP_LIMIT = 10;


    /*

    40 minutes
    TIMER_PARAM: 100
    LOOP_LIMIT: 4
    = ((100 * 6) / 60) * 4 = 40 minutes

    100 minutes
    TIMER_PARAM: 100
    LOOP_LIMIT: 10
    = ((100 * 6) / 60) * 10 = 100 minutes

     */



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

//            if(data.index == 6){
//                System.out.println("the first time");
//            }

            if (data.index == LOOP_LIMIT+2) {
                System.out.println("the last one -> end program");
                Platform.exit();
            }
        });

        delay.play();
    }

    @Override
    public void start(Stage stage) {
        int timer = 6;
        int index = 1;

        for(int i = 0; i < LOOP_LIMIT; i++) {
            Data data;

            // break point
//            if(i+1 == 6){//225
//                // button refresh
//                data = new Data(90, 50, timer * TIMER_PARAM, index);
//                execute(data);
//
//                // click login
//                data = new Data(1200, 650, (timer * TIMER_PARAM) + 2, index+1);
//                execute(data);
//
//                // checkout
//                data = new Data(50, 225, (timer* TIMER_PARAM) + 4, index+2);
//                execute(data);
//
//            }
            if(i+1 == LOOP_LIMIT){//225
                // button refresh
                data = new Data(90, 50, timer * TIMER_PARAM, index);
                execute(data);

                // click login
                data = new Data(1200, 650, (timer * TIMER_PARAM) + 10, index+1);
                execute(data);

                // checkout
                data = new Data(50, 225, (timer* TIMER_PARAM) + 20, index+2);
                execute(data);
                break;
            }

            // running
            if(i % 2 == 0){
                data = new Data(100, 650, timer* TIMER_PARAM, index);
                execute(data);
            } else {
                 data =  new Data(900, 500, timer* TIMER_PARAM, index);
                execute(data);
            }

            timer = timer + 6;
            index = index + 1;
        }
    }

    private static class Data {
        public int x;
        public int y;
        public int seconds;
        public int index;

        public Data(int x, int y, int seconds, int index) {
            this.x = x;
            this.y = y;
            this.seconds = seconds;
            this.index=index;
        }
    }
}

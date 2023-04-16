package com.example.rezerford;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;



public class HelloController {

    @FXML
    private Circle circle1;
    @FXML
    protected Circle circle2;
    @FXML
    protected Button btn;

    Move mv;
    @FXML
    protected void Button(){
        mv = new Move(circle1,circle2);
        mv.start();
    }

    @FXML
    protected void BtnStop(){
        System.exit(0);
    }

    @FXML
    protected void initialize(){
        circle1.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseEvent -> circle1.setLayoutY(mouseEvent.getSceneY()));
    }
}

class Move extends Thread{

    Circle circle1;
    Circle circle2;
    double q1 = 2 * Math.pow(10,-19);
    double q2 = 79 * Math.pow(10,-19);
    double k = 9 * Math.pow(10,12);
    double m = 6.64 * Math.pow(10,-27);
    double vx = 5;
    double vy = 0;

    public Move(Circle circle1, Circle circle2) {
        this.circle1 = circle1;
        this.circle2 = circle2;
    }

    @Override
    public void run() {
        double t = 0.01;

        while (circle1.getLayoutY() < 500 && circle1.getLayoutY() > 0 && circle1.getLayoutX() > 0 && circle1.getLayoutX() < 1000){
            double r = Math.sqrt(Math.pow((circle1.getLayoutX() - circle2.getLayoutX()), 2) + Math.pow((circle1.getLayoutY() - circle2.getLayoutY()), 2));
            double F = k * (q1 * q2) / Math.pow(r, 2);
            double angle = Math.acos((circle2.getLayoutX() - circle1.getLayoutX()) / r);
            if(circle1.getLayoutY() > circle2.getLayoutY()){
                angle = -1 * angle;
            }

            double ax = F * Math.cos(angle) / m;
            double ay = F * Math.sin(angle) / m;

            vx -=  ax * t;
            vy -= ay * t;

            t += 0.01;

            System.out.println("c1x:" + circle1.getLayoutX() + " " + "c1y:" + circle1.getLayoutY());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            circle1.setLayoutX(circle1.getLayoutX() + vx);
            circle1.setLayoutY(circle1.getLayoutY() + vy);
        }

    }
}
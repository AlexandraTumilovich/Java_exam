package com.stormnet.figuresfx.controller;

import com.stormnet.figuresfx.drawutils.Drawer;
import com.stormnet.figuresfx.exceptions.UnknownFigureException;
import com.stormnet.figuresfx.figures.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MainScreenViewController implements Initializable {

    private List<Figure> figures = new ArrayList<>();
    private Random random;
    private final static Logger logger = Logger.getLogger(MainScreenViewController.class);

    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.info("App is initialized!");
        random = new Random(System.currentTimeMillis());
    }

    private Figure createFigure(double x, double y) throws UnknownFigureException {
        Figure figure = null;
        int type = random.nextInt(4);

        switch (type) {
            case Figure.FIGURE_TYPE_CIRCLE:
                int circleRadius = myRandomSize(50,15);
                figure = new Circle(x, y, myRandomSize(5,5), Color.RED, circleRadius);
                logger.info("New circle with radius: " + circleRadius + ".");
                break;
            case Figure.FIGURE_TYPE_RECTANGLE:
                // Here we can catch example of error for rectangle line width! See log file.
                int rectangleWidth = myRandomSize(60,1);
                int rectangleHeight = myRandomSize(80,1);
                figure = new Rectangle(x, y, random.nextInt(5), Color.PINK, rectangleWidth, rectangleHeight);
                logger.info("New rectangle with sides: " + rectangleWidth + " and " + rectangleHeight + ".");
                break;
            case Figure.FIGURE_TYPE_TRIANGLE:
                double[] xTriangle = {myRandomSize(50,1), myRandomSize(50,1), myRandomSize(50,1)};
                double[] yTriangle = {myRandomSize(50,1), myRandomSize(50,1), myRandomSize(50,1)};
                figure = new Triangle(x, y, myRandomSize(5,2), Color.ORCHID, xTriangle, yTriangle);
                logger.info("New triangle with coordinates: x [" + (x - xTriangle[0]) + ","+ (x - xTriangle[1]) + "," + (x + xTriangle[2]) +
                        "], y [" + (y - yTriangle[0]) + ","+ (y - yTriangle[1]) + "," + (y - yTriangle[2]) + "].");
                break;
            case Figure.FIGURE_TYPE_ARC:
                double heightArc = myRandomSize(80,30);
                double widthArc = myRandomSize(80,30);
                double startAngleArc = myRandomSize(300,50);
                double arcExtentArc = myRandomSize(300,50);
                figure = new Arc(x, y, myRandomSize(5,2), Color.AQUAMARINE, heightArc, widthArc, startAngleArc, arcExtentArc);
                logger.info("New arc is created: height: " + heightArc + ", width: " + widthArc + ", startAngle: " + startAngleArc + ", arcExtentArc: " + arcExtentArc + ".");
                break;
            default:
                throw new UnknownFigureException(type);
        }

        return figure;
    }

    private void repaint() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Drawer<Figure> drawer = new Drawer<>(figures);
        drawer.draw(canvas.getGraphicsContext2D());
    }

    @FXML
    private void onMousedClicked(MouseEvent mouseEvent) {
        try {
            figures.add(createFigure(mouseEvent.getX(), mouseEvent.getY()));
            repaint();
        } catch (UnknownFigureException ex) {
            logger.error(ex.getMessage());
        }
    }

    private int myRandomSize(int size, int min){
        return random.nextInt(size) + min;
    }
}

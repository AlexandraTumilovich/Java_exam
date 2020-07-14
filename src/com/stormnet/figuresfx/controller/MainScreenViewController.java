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
                figure = new Circle(x, y, myRandomSize(5,5), Color.RED, myRandomSize(50,15));
                break;
            case Figure.FIGURE_TYPE_RECTANGLE:
                figure = new Rectangle(x, y, random.nextInt(5) + 5, Color.PINK, random.nextInt(60), random.nextInt(80));
                break;
            case Figure.FIGURE_TYPE_TRIANGLE:
                double[] xTriangle = {myRandomSize(50,1), myRandomSize(50,1), myRandomSize(50,1)};
                double[] yTriangle = {myRandomSize(50,1), myRandomSize(50,1), myRandomSize(50,1)};
                figure = new Triangle(x, y, myRandomSize(5,2), Color.ORCHID, xTriangle, yTriangle);
                break;
            case Figure.FIGURE_TYPE_ARC:
                figure = new Arc(x, y, myRandomSize(5,2), Color.AQUAMARINE, myRandomSize(80,30),
                        myRandomSize(80,30), myRandomSize(300,50), myRandomSize(300,50));
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

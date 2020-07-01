package com.stormnet.figuresfx.controller;

import com.stormnet.figuresfx.drawutils.Drawer;
import com.stormnet.figuresfx.exceptions.FigureException;
import com.stormnet.figuresfx.figures.*;
import org.apache.log4j.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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

    private Figure createFigure(double x, double y) throws FigureException {
        Figure figure = null;
        int type;

        switch (type = random.nextInt(4)) {
            case Figure.FIGURE_TYPE_CIRCLE:
                figure = new Circle(x, y, Math.min(random.nextInt(50), 50), Color.GREEN, random.nextInt(50));
                break;
            case Figure.FIGURE_TYPE_RECTANGLE:
                figure = new Rectangle(x, y, Math.min(random.nextInt(50), 50), Color.DARKSLATEBLUE, random.nextInt(45), random.nextInt(70));
                break;
            case Figure.FIGURE_TYPE_SQUARE:
                figure = new Square(x, y, Math.min(random.nextInt(50), 50), Color.BLACK, random.nextInt(45));
                break;
            case Figure.FIGURE_TYPE_TRIANGLE:
                double[] xPoints = {random.nextInt(45), random.nextInt(45), random.nextInt(45)};
                double[] yPoints = {random.nextInt(45), random.nextInt(45), random.nextInt(45)};
                figure = new Triangle(x, y, Math.min(random.nextInt(50), 50), Color.BLUEVIOLET, xPoints, yPoints);
                break;
            default:
                throw new FigureException("Unknown figure type!", type);
        }
        return figure;
    }

    private void repaint() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Drawer<Figure> drawer = new Drawer<Figure>(figures);
        drawer.draw(canvas.getGraphicsContext2D());
    }

    @FXML
    private void onMousedClicked(MouseEvent mouseEvent) {
        try {
            figures.add(createFigure(mouseEvent.getX(), mouseEvent.getY()));
            repaint();
        } catch (FigureException ex) {
            logger.error(ex.getMessage());
        }

    }
}

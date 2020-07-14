package com.stormnet.figuresfx.figures;

import com.stormnet.figuresfx.drawutils.Drawable;
import com.stormnet.figuresfx.exceptions.InvalidSizeException;
import com.stormnet.figuresfx.exceptions.UnknownFigureException;
import javafx.scene.paint.Color;
import org.apache.log4j.Logger;

public abstract class Figure implements Drawable {
    public static final int FIGURE_TYPE_CIRCLE = 0;
    public static final int FIGURE_TYPE_RECTANGLE = 1;
    public static final int FIGURE_TYPE_TRIANGLE = 2;
    public static final int FIGURE_TYPE_ARC = 3;
    private int type;
    protected  double cx;
    protected  double cy;
    protected  double lineWidth;
    protected Color color;
    private final static Logger logger = Logger.getLogger(Figure.class);


    public Figure(int type, double cx, double cy, double lineWidth, Color color) {
        try{
            if(cx<0){
                throw new InvalidSizeException("Invalid figure x coordinate: ", cx);
            } else if (cy<0){
                throw new InvalidSizeException("Invalid figure y coordinate: ", cy);
            } else if (lineWidth<=0){
                throw new InvalidSizeException("Invalid figure line width: ", lineWidth);
            }
        }catch (InvalidSizeException e) {
            logger.error(e.getMessage() + e.getSize());
        }
        this.type = type;
        this.cx = cx;
        this.cy = cy;
        this.lineWidth = lineWidth;
        this.color = color;
    }

    public int getType() {
        return type;
    }

    public double getCx() {
        return cx;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public double getCy() {
        return cy;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

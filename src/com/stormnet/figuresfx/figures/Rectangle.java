package com.stormnet.figuresfx.figures;

import com.stormnet.figuresfx.exceptions.InvalidSizeException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.log4j.Logger;

import java.util.Objects;

public class Rectangle extends Figure{
    private double width;
    private double height;
    private final static Logger logger = Logger.getLogger(Rectangle.class);

    private Rectangle(double cx, double cy, double lineWidth, Color color) {
        super(FIGURE_TYPE_RECTANGLE, cx, cy, lineWidth, color);
    }

    public Rectangle(double cx, double cy, double lineWidth, Color color, double width, double height) {
        this(cx, cy, lineWidth, color);
        try{
            if(width <=0){
                throw new InvalidSizeException("Invalid rectangle width: ", width);
            } else if (height <=0){
                throw new InvalidSizeException("Invalid rectangle height: ", height);
            }
        }catch (InvalidSizeException e) {
            logger.error(e.getMessage() + e.getSize());
        }
        this.width = width < 10 ? 50 :width;
        this.height = height < 10 ? 50 : height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(lineWidth);
        gc.setStroke(color);
        gc.strokeRect(cx - width / 2, cy - height / 2, width, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.width, width) == 0 &&
                Double.compare(rectangle.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rectangle{");
        sb.append("width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", cx=").append(cx);
        sb.append(", cy=").append(cy);
        sb.append(", lineWidth=").append(lineWidth);
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }
}

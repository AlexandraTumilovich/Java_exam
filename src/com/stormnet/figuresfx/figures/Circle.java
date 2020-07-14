package com.stormnet.figuresfx.figures;

import com.stormnet.figuresfx.exceptions.InvalidSizeException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.log4j.Logger;

import java.util.Objects;

public class Circle extends Figure {
    private double radius;
    private final static Logger logger = Logger.getLogger(Circle.class);

    private Circle(double cx, double cy, double lineWidth, Color color) {
        super(FIGURE_TYPE_CIRCLE, cx, cy, lineWidth, color);
    }

    public Circle(double cx, double cy, double lineWidth, Color color, double radius) {
        this(cx, cy, lineWidth, color);
        try {
            if (radius <= 0) {
                throw new InvalidSizeException("Invalid circle radius: ", radius);
            }
        } catch (InvalidSizeException e) {
            logger.error(e.getMessage() + e.getSize());
        }
        this.radius = radius < 10 ? 50 : radius;
    }


    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(lineWidth);
        gc.setStroke(color);
        gc.strokeOval(cx - radius, cy - radius, radius * 2, radius * 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Circle{");
        sb.append("radius=").append(radius);
        sb.append(", cx=").append(cx);
        sb.append(", cy=").append(cy);
        sb.append(", lineWidth=").append(lineWidth);
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }
}
package com.stormnet.figuresfx.figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Square extends Figure{

    private double widthSquare;

    private Square(double cx, double cy, double lineWidth, Color color) {
        super(FIGURE_TYPE_SQUARE, cx, cy, lineWidth, color);
    }

    public Square(double cx, double cy, double lineWidth, Color color, double widthSquare) {
        this(cx, cy, lineWidth, color);
        this.widthSquare = widthSquare < 10 ? 50 : widthSquare;
    }

    public double getWidth() {
        return widthSquare;
    }

    public void setWidth(double widthSquare) {
        this.widthSquare = widthSquare;
    }


    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(lineWidth);
        gc.setStroke(color);
        gc.strokeRect(cx - widthSquare / 2, cy - widthSquare / 2, widthSquare, widthSquare);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Double.compare(square.widthSquare, widthSquare) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(widthSquare);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Square{");
        sb.append("side=").append(widthSquare);
        sb.append(", cx=").append(cx);
        sb.append(", cy=").append(cy);
        sb.append(", lineWidth=").append(lineWidth);
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }
}
package com.stormnet.figuresfx.figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Arrays;

public class Triangle extends Figure {

    private double[] xPoints;
    private double[] yPoints;

    private Triangle(double cx, double cy, double lineWidth, Color color) {
        super(FIGURE_TYPE_TRIANGLE, cx, cy, lineWidth, color);
    }

    public Triangle(double cx, double cy, double lineWidth, Color color, double[] xPoints, double[] yPoints) {
        this(cx, cy, lineWidth, color);
        this.xPoints = new double[]{cx - xPoints[0], cx + xPoints[1], cx};
        this.yPoints = new double[]{cy - yPoints[0], cy - yPoints[1], cy + yPoints[2]};
    }

    public double[] getxPoints() {
        return xPoints;
    }

    public void setxPoints(double[] xPoints) {
        this.xPoints = xPoints;
    }

    public double[] getyPoints() {
        return yPoints;
    }

    public void setyPoints(double[] yPoints) {
        this.yPoints = yPoints;
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setLineWidth(lineWidth);
        gc.setStroke(color);
        gc.strokePolygon(xPoints, yPoints, 3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Arrays.equals(triangle.xPoints, xPoints) &&
                Arrays.equals(triangle.yPoints, yPoints);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(xPoints) + Arrays.hashCode(yPoints);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rectangle{");
        sb.append("Point 1={").append(xPoints[0]).append(",").append(yPoints[0]).append('}');
        sb.append("Point 2={").append(xPoints[1]).append(",").append(yPoints[1]).append('}');
        sb.append("Point 3={").append(xPoints[2]).append(",").append(yPoints[2]).append('}');
        sb.append(", cx=").append(cx);
        sb.append(", cy=").append(cy);
        sb.append(", lineWidth=").append(lineWidth);
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }

}

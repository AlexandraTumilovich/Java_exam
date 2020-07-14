package com.stormnet.figuresfx.figures;

import com.stormnet.figuresfx.exceptions.CoordinatesException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class Triangle extends Figure {

    private double[] xTriangle;
    private double[] yTriangle;
    private final static Logger logger = Logger.getLogger(Circle.class);

    private Triangle(double cx, double cy, double lineWidth, Color color) {
        super(FIGURE_TYPE_TRIANGLE, cx, cy, lineWidth, color);
    }

    public Triangle(double cx, double cy, double lineWidth, Color color, double[] xTriangle, double[] yTriangle) {
        this(cx, cy, lineWidth, color);
            try{
                if(xTriangle[0]==xTriangle[1]&&yTriangle[0]==yTriangle[1]){
                    throw new CoordinatesException();
                } else if(xTriangle[1]==xTriangle[2]&&yTriangle[1]==yTriangle[2]){
                    throw new CoordinatesException();
                } else if(xTriangle[0]==xTriangle[2]&&yTriangle[0]==yTriangle[2]){
                    throw new CoordinatesException();
                }
            }catch(CoordinatesException e){
                logger.error(e.getMessage());
            }

        this.xTriangle = new double[]{cx + xTriangle[0], cx - xTriangle[1], cx + xTriangle[2]};
        this.yTriangle = new double[]{cy + yTriangle[0], cy - yTriangle[1], cy - yTriangle[2]};
    }

    public double[] getXTriangle() {
        return xTriangle;
    }

    public void setX(double[] xTriangle) {
        this.xTriangle = xTriangle;
    }

    public double[] getYTriangle() {
        return yTriangle;
    }

    public void setY(double[] yTriangle) {
        this.yTriangle = yTriangle;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(lineWidth);
        gc.setStroke(color);
        gc.strokePolygon(xTriangle, yTriangle, 3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Arrays.equals(triangle.xTriangle, xTriangle) &&
                Arrays.equals(triangle.yTriangle, yTriangle);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(xTriangle) + Arrays.hashCode(yTriangle);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rectangle{");
        sb.append("First point={").append(xTriangle[0]).append(",").append(yTriangle[0]).append('}');
        sb.append("Second point={").append(xTriangle[1]).append(",").append(yTriangle[1]).append('}');
        sb.append("Third point={").append(xTriangle[2]).append(",").append(yTriangle[2]).append('}');
        sb.append(", cx=").append(cx);
        sb.append(", cy=").append(cy);
        sb.append(", lineWidth=").append(lineWidth);
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }
}

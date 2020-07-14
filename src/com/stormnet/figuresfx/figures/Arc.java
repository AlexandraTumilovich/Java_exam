package com.stormnet.figuresfx.figures;

import com.stormnet.figuresfx.controller.MainScreenViewController;
import com.stormnet.figuresfx.exceptions.InvalidSizeException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import org.apache.log4j.Logger;

import java.util.Objects;

public class Arc extends Figure {

    private double height;
    private double width;
    private double startAngle;
    private double arcExtent;
    private final static Logger logger = Logger.getLogger(Arc.class);

    public Arc(double cx, double cy, double lineWidth, Color color) {
        super(FIGURE_TYPE_ARC, cx, cy, lineWidth, color);
    }

    public Arc(double cx, double cy, double lineWidth, Color color, double height, double width, double startAngle, double arcExtent) {
        this(cx, cy, lineWidth, color);

            try {
                if(height<=0){
                    throw new InvalidSizeException("Invalid arc height: ", height);
                } else if (width<=0){
                    throw new InvalidSizeException("Invalid arc width: ", width);
                } else if (startAngle<=0){
                    throw new InvalidSizeException("Invalid arc startAngle: ", startAngle);
                } else if (arcExtent<=0){
                    throw new InvalidSizeException("Invalid arc arcExtent: ", arcExtent);
                }
            } catch (InvalidSizeException e) {
                logger.error(e.getMessage() + e.getSize());
            }

        this.height = height;
        this.width = width;
        this.startAngle = startAngle;
        this.arcExtent = arcExtent;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(lineWidth);
        gc.setStroke(color);
        gc.strokeArc(cx-width/2, cy-height/2, width, height, startAngle, arcExtent, ArcType.ROUND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arc arc = (Arc) o;
        return (Double.compare(arc.height, height) == 0)
                &&(Double.compare(arc.width, width) == 0)
                &&(Double.compare(arc.startAngle, startAngle) == 0)
                &&(Double.compare(arc.arcExtent, arcExtent) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height,width,startAngle,arcExtent);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Circle{");
        sb.append("height=").append(height);
        sb.append(", width=").append(width);
        sb.append(", startAngle=").append(startAngle);
        sb.append(", arcExtent=").append(arcExtent);
        sb.append(", cx=").append(cx);
        sb.append(", cy=").append(cy);
        sb.append(", lineWidth=").append(lineWidth);
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }
}

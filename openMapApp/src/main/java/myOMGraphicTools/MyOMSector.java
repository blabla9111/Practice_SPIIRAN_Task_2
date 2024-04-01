package myOMGraphicTools;

import com.bbn.openmap.omGraphics.OMEllipse;
import com.bbn.openmap.proj.Length;
import com.bbn.openmap.proj.coords.LatLonPoint;

public class MyOMSector extends OMEllipse {

    private String name;

    private double centerX;
    private double centerY;
    private double radiusX;
    private double radiusY;
    private double rotateAngle;

    public MyOMSector(String name, double x1, double y1, double majorAxisSpan, double minorAxisSpan, double rotateAngle) {
        super(new LatLonPoint.Double(x1, y1), majorAxisSpan, minorAxisSpan, Length.NM, rotateAngle);
        this.name = name;
        this.centerX = x1;
        this.centerY = y1;
        this.radiusX = majorAxisSpan;
        this.radiusY = minorAxisSpan;
        this.rotateAngle = rotateAngle;
    }

    public String[] getInfoAboutParams(){
        return  new String[]{this.name, String.valueOf(this.centerX), String.valueOf(this.centerY), String.valueOf(this.radiusX), String.valueOf(this.radiusY), String.valueOf(this.rotateAngle)};
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }

    public double getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }

    public double getRotateAngle() {
        return rotateAngle;
    }

    public void setRotateAngle(double rotateAngle) {
        this.rotateAngle = rotateAngle;
    }
}

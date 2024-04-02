package myOMGraphicTools;

import com.bbn.openmap.omGraphics.OMGraphic;
import com.bbn.openmap.omGraphics.OMPoint;

import java.io.Serializable;

public class MyOMPoint extends OMPoint implements OMGraphic, Serializable {

    private String name;
    private double lat;
    private double lon;
    private int course;
    private int radius;

    public MyOMPoint(String name, double lat, double lon, int radius) {
        super(lat, lon, radius);
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.radius = radius;
        this.course = 0;
        this.set(lat, lon);
    }

    public String[] getInfoAboutParams(){
        return  new String[]{this.name, String.valueOf(this.lat), String.valueOf(this.lon), String.valueOf(this.radius), String.valueOf(this.course)};
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public void setRadius(int radius) {
        this.radius = radius;
    }
}

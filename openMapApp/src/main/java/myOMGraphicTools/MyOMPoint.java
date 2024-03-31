package myOMGraphicTools;

import com.bbn.openmap.omGraphics.OMGraphic;
import com.bbn.openmap.omGraphics.OMPoint;

import java.io.Serializable;

public class MyOMPoint extends OMPoint implements OMGraphic, Serializable {

    public String name;
    public Double lat;
    public Double lon;
    public int course;
    public int radius;

    public MyOMPoint(String name, double lat, double lon, int radius) {
        super(lat, lon, radius);
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.radius = radius;
        this.course = 0;
        this.set(lat, lon);
    }

}

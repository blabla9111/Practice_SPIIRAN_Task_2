package myOMGraphicTools;

import com.bbn.openmap.omGraphics.OMGraphic;
import com.bbn.openmap.omGraphics.OMPoint;

import java.io.Serializable;

/**
 *  Растровый (точечный) объект
 *
 */
public class MyOMPoint extends OMPoint implements OMGraphic, Serializable {

    /**
     * Имя
     *
     */
    public String name;
    /**
     * широта
     *
     */
    public Double lat;
    /**
     * долгота
     *
     */
    public Double lon;
    /**
     * Курс
     *
     */
    public int course;
    /**
     * Радиус (для отрисовки)
     *
     */
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

    public String[] getInfoAboutParams(){
        return  new String[]{this.name, String.valueOf(this.lat), String.valueOf(this.lon), String.valueOf(this.radius), String.valueOf(this.course)};
    }

}

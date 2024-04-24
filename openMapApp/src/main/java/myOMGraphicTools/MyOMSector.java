package myOMGraphicTools;

import com.bbn.openmap.omGraphics.OMEllipse;
import com.bbn.openmap.proj.Length;
import com.bbn.openmap.proj.coords.LatLonPoint;

/**
 *  Сектор
 *
 */
public class MyOMSector extends OMEllipse {

    /**
     * Имя
     *
     */
    public String name;

    /**
     * Координата X центра
     *
     */
    public double centerX;
    /**
     * Координата Y центра
     *
     */
    public double centerY;
    /**
     * Координата X  радиуса
     *
     */
    public double radiusX;
    /**
     *  Координата Y радиуса
     *
     */
    public double radiusY;
    /**
     * Угол поворота
     *
     */
    public double rotateAngle;

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

}

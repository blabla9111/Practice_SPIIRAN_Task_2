package myOMGraphicTools;

import com.bbn.openmap.omGraphics.OMPoly;
import com.bbn.openmap.omGraphics.labeled.LabeledOMSpline;

/**
 *  Полигон
 *
 */
public class MyOMPoly extends LabeledOMSpline {
    /**
     * Имя
     *
     */
    public String name;
    /**
     * список X координат
     *
     */
    public int[] xs = null;
    /**
     * список Y координат
     *
     */
    public int[] ys = null;

    // необходим, иначе ошибка MissingResourceException
    public MyOMPoly(){

    }

    public MyOMPoly(String name, int[] llPointsx, int[] llPointsy) {
        super(0, 0, llPointsx, llPointsy, OMPoly.COORDMODE_ORIGIN);
        this.name=name;
        this.xs=llPointsx;
        this.ys=llPointsy;
    }
    public MyOMPoly(String name, double lat, double lon, int[] xs, int[] ys) {
        super((float) lat, (float) lon,xs, ys, OMPoly.COORDMODE_ORIGIN);
        this.name=name;
        this.xs=xs;
        this.ys=ys;
    }
}

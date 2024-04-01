package myOMGraphicTools;

import com.bbn.openmap.omGraphics.OMPoly;
import com.bbn.openmap.omGraphics.labeled.LabeledOMSpline;

public class MyOMPoly extends LabeledOMSpline {
    private String name;
    private int[] xs = null;
    private int[] ys = null;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int[] getXs() {
        return xs;
    }

    @Override
    public void setXs(int[] xs) {
        this.xs = xs;
    }

    @Override
    public int[] getYs() {
        return ys;
    }

    @Override
    public void setYs(int[] ys) {
        this.ys = ys;
    }
}

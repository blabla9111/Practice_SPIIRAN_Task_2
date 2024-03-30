package myOMGraphicTools;

import com.bbn.openmap.omGraphics.OMPoly;
import com.bbn.openmap.omGraphics.labeled.LabeledOMSpline;


public class MyOMPoly extends LabeledOMSpline {
    public String name;
    public int[] xs;
    public int[] ys;

    public MyOMPoly(String name, int[] llPointsx, int[] llPointsy) {
        super(0, 0, llPointsx, llPointsy, OMPoly.COORDMODE_ORIGIN);
        this.name=name;
        this.xs=llPointsx;
        this.ys=llPointsy;
    }

}

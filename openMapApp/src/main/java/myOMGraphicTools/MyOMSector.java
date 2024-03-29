package myOMGraphicTools;

import com.bbn.openmap.omGraphics.OMEllipse;
import com.bbn.openmap.proj.Length;
import com.bbn.openmap.proj.coords.LatLonPoint;

public class MyOMSector extends OMEllipse {

    public String name;

    public double centerX;
    public double centerY;
    public double radiusX;
    public double radiusY;
    public double rotateAngle;
    public MyOMSector(String name, double x1, double y1, double majorAxisSpan, double minorAxisSpan, double rotateAngle) {
        super(new LatLonPoint.Double(x1, y1), majorAxisSpan, minorAxisSpan, Length.NM, rotateAngle);
        this.name=name;
        this.centerX=x1;
        this.centerY=y1;
        this.radiusX=majorAxisSpan;
        this.radiusY=minorAxisSpan;
        this.rotateAngle=rotateAngle;
    }
}

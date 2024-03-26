package myTools;

import com.bbn.openmap.omGraphics.OMGraphic;
import com.bbn.openmap.omGraphics.OMGraphicAdapter;
import com.bbn.openmap.omGraphics.OMPoint;
import com.bbn.openmap.proj.Projection;

import java.io.Serializable;

public class MyOMPoint extends OMPoint implements OMGraphic, Serializable {

    public String name;
    public Double x;
    public Double y;

    public MyOMPoint(double x, double y){

    }

}

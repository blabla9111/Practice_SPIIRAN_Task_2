package layers;

import com.bbn.openmap.layer.DemoLayer;
import com.bbn.openmap.layer.policy.BufferedImageRenderPolicy;
import com.bbn.openmap.omGraphics.*;
import com.bbn.openmap.proj.Length;
import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.tools.drawing.DrawingTool;
import com.bbn.openmap.tools.drawing.DrawingToolRequestor;
import myOMGraphicTools.MyOMSector;

import javax.swing.*;
import java.awt.*;

public class MyOMSectorLayer extends DemoLayer implements DrawingToolRequestor {
    protected DrawingTool drawingTool;



    public DrawingTool getDrawingTool() {
        // Usually set in the findAndInit() method.
        return drawingTool;
    }

    public void setDrawingTool(DrawingTool dt) {
        // Called by the findAndInit method.
        drawingTool = dt;
    }

    public MyOMSectorLayer() {
        setName("MyOMSector Layer");
        setProjectionChangePolicy(new com.bbn.openmap.layer.policy.StandardPCPolicy(this, true));
        setRenderPolicy(new BufferedImageRenderPolicy());
    }

    public synchronized OMGraphicList prepare() {
        OMGraphicList list = getList();
        if (list == null) {
            list = init();
        }
        list.generate(getProjection());
//        System.out.println("alooooo Sector");

        return list;
    }

    public OMGraphicList init() {

        OMGraphicList omList = new OMGraphicList();
        MyOMSector sector = new MyOMSector("New sector",160.0, -110.0, 3000.0, 600.0,  0.0);
        sector.setFillPaint(Color.orange);
        omList.add(sector);
        return omList;
    }

    public String getToolTipTextFor(OMGraphic omg) {
        Object tt = omg.getAttribute("Tooltip Poly");
        if (tt instanceof String) {
            return (String)tt;
        } else {
            String classname = omg.getClass().getName();
            int lio = classname.lastIndexOf(46);
            if (lio != -1) {
                classname = classname.substring(lio + 1);
            }
            if (omg instanceof MyOMSector){
                MyOMSector sector = (MyOMSector) omg;
                return "MyOMSector: "+ sector.getName();
            }

            return "MyOMSector Layer Object: " + classname;
        }
    }
    @Override
    public void drawingComplete(OMGraphic omg, OMAction action) {
        if (! (omg instanceof OMCircle)){
            JOptionPane.showMessageDialog(null, "В этом слое можно создавать объекты только OMCircle");
            repaint();
            return;
        }
//        System.out.println(omg.getClass()+" cllllllllllllllllllllllass");
        if (!(omg instanceof MyOMSector)){
            // Создан объект OMPoint, который нужно преобразовать в MyOMPoint
//            System.out.println("OMCircle");
            OMCircle circle = (OMCircle) omg;
            MyOMSector sector = new MyOMSector("New sector",circle.getCenter().getX(),circle.getCenter().getY(),1000.0,500.0,0.0);
            sector.setCenter(new LatLonPoint.Double(sector.getCenterX(), sector.getCenterY()));
            sector.setRenderType(1);
            sector.setAxis(sector.getRadiusX(), sector.getRadiusY(), Length.NM);
            sector.setRotationAngle(sector.getRotateAngle());
            sector.setFillPaint(Color.orange);
            omg=sector;
        }
        if (!doAction(omg, action)) {
            // null OMGraphicList on failure, should only occur if
            // OMGraphic is added to layer before it's ever been
            // on the map.
            setList(new OMGraphicList());
            doAction(omg, action);
        }
        omg.setFillPaint(Color.orange);
        repaint();
    }
}

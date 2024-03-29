package layers;

import com.bbn.openmap.layer.DemoLayer;
import com.bbn.openmap.layer.policy.BufferedImageRenderPolicy;
import com.bbn.openmap.omGraphics.*;
import com.bbn.openmap.proj.Length;
import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.tools.drawing.DrawingTool;
import com.bbn.openmap.tools.drawing.DrawingToolRequestor;
import myOMGraphicTools.MyOMPoly;
import myOMGraphicTools.MyOMSector;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

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
        // Sets the name of the layer that is visible in the GUI. Can also be
        // set with properties with the 'prettyName' property.
        setName("MyOMSector Layer");
        // This is how to set the ProjectionChangePolicy, which
        // dictates how the layer behaves when a new projection is
        // received. The StandardPCPolicy is the default policy and you don't
        // need to set it, this method call is here to illustrate where and how
        // you would make that call with a different policy.
        setProjectionChangePolicy(new com.bbn.openmap.layer.policy.StandardPCPolicy(this, true));
        // Improves performance
        setRenderPolicy(new BufferedImageRenderPolicy());
    }

    public synchronized OMGraphicList prepare() {
        OMGraphicList list = getList();

        // Here's a test to see if it's the first time that the layer has been
        // added to the map. This list object will be whatever was returned from
        // this method the last time prepare() was called. In this
        // example, we always return an OMGraphicList object, so if it's null,
        // prepare() must not have been called yet.

        if (list == null) {
            list = init();
        }

        /*
         * This call to the list is critical! OMGraphics need to be told where to
         * paint themselves, and they figure that out when they are given the
         * current Projection in the generate(Projection) call. If an OMGraphic's
         * location is changed, it will need to be regenerated before it is
         * rendered, otherwise it won't draw itself. You generally know you have a
         * generate problem when OMGraphics show up with the projection changes
         * (zooms and pans), but not at any other time after something about the
         * OMGraphic changes.
         *
         * If you want to be more efficient, you can replace this call to the list
         * as an else clause to the (list == null) check above, and call
         * generate(Projection) on all the OMGraphics in the init() method below
         * as you create them. This will prevent the
         * OMGraphicList.generate(Projection) call from making an additional loop
         * through all of the OMGraphics before they are returned.
         */
        list.generate(getProjection());
        System.out.println("alooooo Sector");

        return list;
    }

    public OMGraphicList init() {

        // This layer keeps a pointer to an OMGraphicList that it uses
        // for painting. It's initially set to null, which is used as
        // a flag in prepare() to signal that the OMGraphcs need to be
        // created. The list returned from prepare() gets set in the
        // layer.
        // This layer uses the StandardPCPolicy for new
        // projections, which keeps the list intact and simply calls
        // generate() on it with the new projection, and repaint()
        // which calls paint().

        OMGraphicList omList = new OMGraphicList();


//        double[] llp2 = new double[] {10.0,0.0, 50.0,0.0, 50.0,20.0};
//
//        OMPoly p2 = new OMPoly(llp2, OMGraphic.RADIANS, OMGraphic.LINETYPE_RHUMB);
//        p2.setLinePaint(Color.yellow);
//        p2.setFillPaint(Color.CYAN);

//        omList.add(p2);
//        OMEllipse ellipse = new OMEllipse(new LatLonPoint.Double(60.0, -110.0), 1000.0, 300.0, Length.NM, 0.7853981852531433);
//        ellipse.setFillPaint(Color.BLUE);
//        ellipse.setLinePaint(Color.blue);
////        ellipse.setRenderType(3);
//        omList.add(ellipse);

        MyOMSector sector = new MyOMSector("New sector",160.0, -110.0, 3000.0, 600.0,  0.0);
        sector.setFillPaint(Color.orange);
        omList.add(sector);
//        omList.add((OMGraphic) b);
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
                // !!!!!!!!!!!!!!!!!!!!! РЕДАКТИРОВАТЬ
//                MyOMPoly poly = (MyOMPoly) omg;
                MyOMSector sector = (MyOMSector) omg;
                return "MyOMSector: "+sector.name;
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
        System.out.println(omg.getClass()+" cllllllllllllllllllllllass");
        if (omg instanceof OMCircle && !(omg instanceof MyOMSector)){
            // Создан объект OMPoint, который нужно преобразовать в MyOMPoint
            System.out.println("OMCircle");
            OMCircle circle = (OMCircle) omg;
            MyOMSector sector = new MyOMSector("New sector",circle.getCenter().getX(),circle.getCenter().getY(),1000.0,500.0,0.0);
            sector.setCenter(new LatLonPoint.Double(sector.centerX,sector.centerY));
            sector.setRenderType(1);
            sector.setAxis(sector.radiusX,sector.radiusY, Length.NM);
            sector.setRotationAngle(sector.rotateAngle);
            sector.setFillPaint(Color.orange);
            omg=sector;
            System.out.println(circle.getCenter().getX());
            System.out.println(circle.getCenter().getY());

//            omg = poly;

//            OMPoint omPoint = (OMPoint) omg;
//            MyOMPoint point = new MyOMPoint("New MyOMPoint", omPoint.getLat(),omPoint.getLon(),omPoint.getRadius()+10);
//            omg = point;
        }
        if (!doAction(omg, action)) {
            // null OMGraphicList on failure, should only occur if
            // OMGraphic is added to layer before it's ever been
            // on the map.
            setList(new OMGraphicList());
            doAction(omg, action);
        }
        // создание и настройка параметров отрисовки нового экземпляра MyOMPoint
//        MyOMPoint point = (MyOMPoint) omg;
//        point.set(point.lat,point.lon);
//        point.setRadius(point.radius);
//        point.setFillPaint(Color.MAGENTA);
//        point.setRenderType(3);
//        point.setOval(true);

        MyOMSector sector = (MyOMSector) omg;
        // а почему это не сделать в edit?
//        sector.setCenter(new LatLonPoint.Double(sector.centerX,sector.centerY));
//        sector.setRenderType(1);
//        sector.setAxis(sector.radiusX,sector.radiusY,Length.NM);
//        sector.setRotationAngle(sector.rotateAngle);
//        sector.setFillPaint(Color.orange);
        repaint();
    }
}

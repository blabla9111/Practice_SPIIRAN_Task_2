package layers;

import com.bbn.openmap.layer.DemoLayer;
import com.bbn.openmap.layer.policy.BufferedImageRenderPolicy;
import com.bbn.openmap.omGraphics.*;
import com.bbn.openmap.tools.drawing.DrawingTool;
import com.bbn.openmap.tools.drawing.DrawingToolRequestor;
import myOMGraphicTools.MyOMPoint;

import javax.swing.*;
import java.awt.*;

public class MyOMPointLayer
        extends DemoLayer implements DrawingToolRequestor {


    protected DrawingTool drawingTool;

    public DrawingTool getDrawingTool() {
        return drawingTool;
    }

    public void setDrawingTool(DrawingTool dt) {
        drawingTool = dt;
    }

    public MyOMPointLayer() {
        setName("MyOMPoint Layer");
        setProjectionChangePolicy(new com.bbn.openmap.layer.policy.StandardPCPolicy(this, true));
        setRenderPolicy(new BufferedImageRenderPolicy());
    }

    public synchronized OMGraphicList prepare() {
        OMGraphicList list = getList();
        if (list == null) {
            list = init();
        }
        list.generate(getProjection());
//        System.out.println("alooooo");

        return list;
    }

    /**
     * Called from the prepare() method if the layer discovers that its
     * OMGraphicList is null.
     *
     * @return new OMGraphicList with OMGraphics that you always want to display
     * and reproject as necessary.
     */
    public OMGraphicList init() {

        OMGraphicList omList = new OMGraphicList();


//        OMPoint point = new OMPoint((float) (Math.random() * 89f), (float) (Math.random() * -179f), 3);
        MyOMPoint myOMPoint = new MyOMPoint("Dipoint", (float) (Math.random() * 89f), (float) (Math.random() * -179f), 20);
        myOMPoint.setFillPaint(Color.MAGENTA);
        myOMPoint.setRenderType(3);
        myOMPoint.setOval(true);

        omList.add(myOMPoint);
        return omList;
    }

    public String getToolTipTextFor(OMGraphic omg) {
        Object tt = omg.getAttribute("Tooltip");
        if (tt instanceof String) {
            return (String) tt;
        } else {
            String classname = omg.getClass().getName();
            int lio = classname.lastIndexOf(46);
            if (lio != -1) {
                classname = classname.substring(lio + 1);
            }
            if (omg instanceof MyOMPoint) {
                MyOMPoint point = (MyOMPoint) omg;
                return "MyOMPoint: " + point.name;
            }

            return "MyOMPoint Layer Object: " + classname;
        }
    }

    @Override
    public void drawingComplete(OMGraphic omg, OMAction action) {
        if (!(omg instanceof OMPoint)) {
            JOptionPane.showMessageDialog(null, "В этом слое можно создавать объекты только OMPoint");
            repaint();
            return;
        }

        if ( !(omg instanceof MyOMPoint)) {
            // Создан объект OMPoint, который нужно преобразовать в MyOMPoint
//            System.out.println("OMPoint");
            OMPoint omPoint = (OMPoint) omg;
            MyOMPoint point = new MyOMPoint("New MyOMPoint", omPoint.getLat(), omPoint.getLon(), omPoint.getRadius() + 10);
            point.setFillPaint(Color.MAGENTA);
            point.setOval(true);
            omg = point;
        }
        if (!doAction(omg, action)) {
            // null OMGraphicList on failure, should only occur if
            // OMGraphic is added to layer before it's ever been
            // on the map.
            setList(new OMGraphicList());
            doAction(omg, action);
        }
        omg.setFillPaint(Color.MAGENTA);
//        System.out.println("@222222222");
        repaint();
    }
}
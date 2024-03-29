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
        // Usually set in the findAndInit() method.
        return drawingTool;
    }

    public void setDrawingTool(DrawingTool dt) {
        // Called by the findAndInit method.
        drawingTool = dt;
    }

    /**
     * The empty constructor is necessary for any layer being created using the
     * openmap.properties file, via the openmap.layers property. This method
     * needs to be public, too. Don't try to do too much in the constructor -
     * remember, this code gets executed whether the user uses the layer or not.
     * Performance-wise, it's better to do most initialization the first time the
     * layer is made part of the map. You can test for that in the prepare()
     * method, by testing whether the OMGraphicList for the layer is null or not.
     *
     * @see #prepare
     */
    public MyOMPointLayer() {
        // Sets the name of the layer that is visible in the GUI. Can also be
        // set with properties with the 'prettyName' property.
        setName("MyOMPoint Layer");
        // This is how to set the ProjectionChangePolicy, which
        // dictates how the layer behaves when a new projection is
        // received. The StandardPCPolicy is the default policy and you don't
        // need to set it, this method call is here to illustrate where and how
        // you would make that call with a different policy.
        setProjectionChangePolicy(new com.bbn.openmap.layer.policy.StandardPCPolicy(this, true));
        // Improves performance
        setRenderPolicy(new BufferedImageRenderPolicy());
    }

    /**
     * This is an important Layer method to override. The prepare method gets
     * called when the layer is added to the map, or when the map projection
     * changes. We need to make sure the OMGraphicList returned from this method
     * is what we want painted on the map. The OMGraphics need to be generated
     * with the current projection. We test for a null OMGraphicList in the layer
     * to see if we need to create the OMGraphics. This layer doesn't change its
     * OMGraphics for different projections, if your layer does, you need to
     * clear out the OMGraphicList and add the OMGraphics you want for the
     * current projection.
     */
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
        System.out.println("alooooo");

        return list;
    }

    /**
     * Called from the prepare() method if the layer discovers that its
     * OMGraphicList is null.
     *
     * @return new OMGraphicList with OMGraphics that you always want to display
     *         and reproject as necessary.
     */
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



        // Add a list of OMPoints.
        OMGraphicList pointList = new OMGraphicList();
        for (int i = 0; i < 1; i++) {
            OMPoint point = new OMPoint((float) (Math.random() * 89f), (float) (Math.random() * -179f), 3);
            MyOMPoint myOMPoint = new MyOMPoint("Dipoint",(float) (Math.random() * 89f), (float) (Math.random() * -179f),20);
            myOMPoint.setFillPaint(Color.MAGENTA);
            myOMPoint.setRenderType(3);
            myOMPoint.setOval(true);
//            point.
            pointList.add(point);
            pointList.add(myOMPoint);
        }
        omList.add(pointList);
//        omList.add((OMGraphic) b);
        return omList;
    }

    public String getToolTipTextFor(OMGraphic omg) {
        Object tt = omg.getAttribute("Tooltip");
        if (tt instanceof String) {
            return (String)tt;
        } else {
            String classname = omg.getClass().getName();
            int lio = classname.lastIndexOf(46);
            if (lio != -1) {
                classname = classname.substring(lio + 1);
            }
            if (omg instanceof  MyOMPoint){
                MyOMPoint point = (MyOMPoint) omg;
                return "MyOMPoint: " + point.name+" lat:"+point.lat+" lon:"+point.lon;
            }

            return "MyOMPoint Layer Object: " + classname;
        }
    }
    @Override
    public void drawingComplete(OMGraphic omg, OMAction action) {
        if (! (omg instanceof OMPoint)){
            JOptionPane.showMessageDialog(null, "В этом слое можно создавать объекты только OMPoint");
            repaint();
            return;
        }

        if (omg instanceof OMPoint && !(omg instanceof MyOMPoint)){
            // Создан объект OMPoint, который нужно преобразовать в MyOMPoint
            System.out.println("OMPoint");
            OMPoint omPoint = (OMPoint) omg;
            MyOMPoint point = new MyOMPoint("New MyOMPoint", omPoint.getLat(),omPoint.getLon(),omPoint.getRadius()+10);
            omg = point;
        }
        if (!doAction(omg, action)) {
            // null OMGraphicList on failure, should only occur if
            // OMGraphic is added to layer before it's ever been
            // on the map.
            setList(new OMGraphicList());
            doAction(omg, action);
        }
        // создание и настройка параметров отрисовки нового экземпляра MyOMPoint
        MyOMPoint point = (MyOMPoint) omg;
        point.set(point.lat,point.lon);
        point.setRadius(point.radius);
        point.setFillPaint(Color.MAGENTA);
        point.setRenderType(3);
        point.setOval(true);

//        System.out.println(point.name);
        System.out.println("@222222222");
        repaint();
    }
}
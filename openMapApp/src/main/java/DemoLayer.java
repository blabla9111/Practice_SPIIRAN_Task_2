import com.bbn.openmap.layer.OMGraphicHandlerLayer;
import com.bbn.openmap.layer.policy.BufferedImageRenderPolicy;
import com.bbn.openmap.omGraphics.*;
import com.bbn.openmap.tools.drawing.DrawingTool;
import com.bbn.openmap.tools.drawing.DrawingToolRequestor;
import myTools.MyOMPoint;

import javax.swing.*;
import java.awt.*;

public class DemoLayer
        extends com.bbn.openmap.layer.DemoLayer implements DrawingToolRequestor {


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
    public DemoLayer() {
        // Sets the name of the layer that is visible in the GUI. Can also be
        // set with properties with the 'prettyName' property.
        setName("Basic Layer");
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

        // Add an OMLine
        OMLine line = new OMLine(40f, -145f, 42f, -70f, OMGraphic.LINETYPE_GREATCIRCLE);
        // line.addArrowHead(true);
        line.setStroke(new BasicStroke(2));
        line.setLinePaint(Color.red);
        line.putAttribute(OMGraphicConstants.LABEL, new OMTextLabeler("Line Label"));

        omList.add(line);

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

            return "MyDemo Layer Object: " + classname;
        }
    }
    @Override
    public void drawingComplete(OMGraphic omg, OMAction action) {
        if (! (omg instanceof MyOMPoint)){
            System.out.println("Nooooooooooooooooo!!!");
            repaint();
            return;
        }
        if (!doAction(omg, action)) {
            // null OMGraphicList on failure, should only occur if
            // OMGraphic is added to layer before it's ever been
            // on the map.
            setList(new OMGraphicList());
            doAction(omg, action);
        }
        MyOMPoint point = (MyOMPoint) omg;
        System.out.println(point.x);
//        point.setFillPaint(Color.BLUE);
        point.set(point.x,point.y);
//        point.set(point.getLat(),point.getLon());

        System.out.println(point.name);
        System.out.println("@222222222");
        repaint();
    }

    @Override
    public Component getGUI() {

        System.out.println("public Component getGUI()");
        return super.getGUI();
    }
}
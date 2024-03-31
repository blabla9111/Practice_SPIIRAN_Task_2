package layers;

import com.bbn.openmap.layer.DemoLayer;
import com.bbn.openmap.layer.policy.BufferedImageRenderPolicy;
import com.bbn.openmap.omGraphics.*;
import com.bbn.openmap.tools.drawing.DrawingTool;
import com.bbn.openmap.tools.drawing.DrawingToolRequestor;
import myOMGraphicTools.MyOMPoly;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MyOMPolyLayer extends DemoLayer implements DrawingToolRequestor {
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
    public MyOMPolyLayer() {
        // Sets the name of the layer that is visible in the GUI. Can also be
        // set with properties with the 'prettyName' property.
        setName("MyOMPoly Layer");
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
        System.out.println("alooooo Poly");

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


//        double[] llp2 = new double[] {10.0,0.0, 50.0,0.0, 50.0,20.0};
//
//        OMPoly p2 = new OMPoly(llp2, OMGraphic.RADIANS, OMGraphic.LINETYPE_RHUMB);
//        p2.setLinePaint(Color.yellow);
//        p2.setFillPaint(Color.CYAN);

//        omList.add(p2);
        int[] llPointsx = new int[6];
        int[] llPointsy = new int[6];
        llPointsy[0] = 0;
        llPointsx[0] = 0;
        llPointsy[1] = 10;
        llPointsx[1] = 0;
        llPointsy[2] = -10;
        llPointsx[2] = 0;
        llPointsy[3] = 20;
        llPointsx[3] = 0;
        llPointsy[4] = -20;
        llPointsx[4] = 0;
        llPointsy[5] = 0;
        llPointsx[5] = 0;

//        LabeledOMSpline spline = new LabeledOMSpline(0, 0, llPointsx, llPointsy, OMPoly.COORDMODE_ORIGIN);
//        spline.setText("Testing");
//        spline.setLocateAtCenter(true);
//        spline.setLinePaint(Color.green);
//        omList.add(spline);

        MyOMPoly poly = new MyOMPoly("New Poly",llPointsx, llPointsy);
        poly.setText("Poly");
        poly.setLocateAtCenter(true);
        poly.setLinePaint(Color.red);
        omList.add(poly);
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
            if (omg instanceof MyOMPoly){
                MyOMPoly poly = (MyOMPoly) omg;
                return "MyOMPoly: "+poly.name;
            }

            return "MyOMPoint Layer Object: " + classname;
        }
    }
    @Override
    public void drawingComplete(OMGraphic omg, OMAction action) {
        if (! (omg instanceof OMPoly)){
            JOptionPane.showMessageDialog(null, "В этом слое можно создавать объекты только OMPoly");
            repaint();
            return;
        }
        System.out.println(omg.getClass()+" cllllllllllllllllllllllass");
        if (omg instanceof OMSpline && !(omg instanceof MyOMPoly)){
            // Создан объект OMPoint, который нужно преобразовать в MyOMPoint
            System.out.println("OMSpline");
            OMSpline spline = (OMSpline) omg;
            System.out.println(spline.getRawllpts().length);
            System.out.println(spline.getUnits());
            double[] rawllpts = spline.getRawllpts();
            int rawllptsLen = rawllpts.length;
            System.out.println("Line coordinats "+Arrays.toString(Arrays.stream(rawllpts).toArray()));
            int[] xs = new int[rawllptsLen/2];
            int[] ys = new int[rawllptsLen/2];
            for(int i = 0;i<rawllptsLen/2;i++){
                xs[i]= (int) (rawllpts[i*2]*100);
                ys[i]=(int) (rawllpts[i*2+1]*100);
            }
            double lat=spline.getLat();
            double lon =spline.getLon();
            System.out.println("lat  = "+lat);
            System.out.println("lon  = "+lon);
            MyOMPoly poly = new MyOMPoly("New MyOMPoly",lat,lon, xs,ys);
            poly.setText("New MyOMPoly");
            poly.setRenderType(3);
//        poly.setR
//            poly.setLocateAtCenter(true);
            poly.setLinePaint(Color.red);
            poly.setLat(lat);
            poly.setLon(lon);
            poly.setLocation(xs,ys);
            omg = poly;

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

        MyOMPoly poly = (MyOMPoly) omg;
//        poly.setLocation(poly.getXs(),poly.getYs());
//        poly.setRenderType(3);
////        poly.setR
//        poly.setLocateAtCenter(true);
//        poly.setLinePaint(Color.red);
//        poly.setVisible(true);
//        poly.setCoordMode(0);
//        poly.setDoShapes(true);
//        poly.setFillPaint(Color.MAGENTA);
//        poly.set
//        for(int i =0;i<poly.ys.length;i++){
//            System.out.println(poly.xs[i]+"   "+poly.ys[i]);
//        }
//        poly.render((Graphics) omg);
//        System.out.println(point.name);
//
//        LabeledOMSpline spline = new LabeledOMSpline(0, 0, poly.xs, poly.ys, OMPoly.COORDMODE_ORIGIN);
//        spline.setText("Testing");
//        spline.setLocateAtCenter(true);
//        spline.setLinePaint(Color.green);
//
//        omg = spline;
//        System.out.println("Poly name "+ Arrays.toString(poly.getXs()));
//        System.out.println("Poly name "+ Arrays.toString(poly.getYs()));
//        omg = poly;
        repaint();
    }
}

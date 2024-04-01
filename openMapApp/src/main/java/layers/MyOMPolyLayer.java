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
    public MyOMPolyLayer() {
        setName("MyOMPoly Layer");
        setProjectionChangePolicy(new com.bbn.openmap.layer.policy.StandardPCPolicy(this, true));
        setRenderPolicy(new BufferedImageRenderPolicy());
    }

    public synchronized OMGraphicList prepare() {
        OMGraphicList list = getList();
        if (list == null) {
            list = init();
        }
        list.generate(getProjection());
//        System.out.println("alooooo Poly");

        return list;
    }

    public OMGraphicList init() {


        OMGraphicList omList = new OMGraphicList();

        int[] llPointsx = new int[6];
        int[] llPointsy = new int[6];
        llPointsy[0] = 0;
        llPointsx[0] = 0;
        llPointsy[1] = 10;
        llPointsx[1] = 20;
        llPointsy[2] = -10;
        llPointsx[2] = -15;
        llPointsy[3] = 25;
        llPointsx[3] = 60;
        llPointsy[4] = -20;
        llPointsx[4] = 50;
        llPointsy[5] = 0;
        llPointsx[5] = 0;

        MyOMPoly poly = new MyOMPoly("New Poly",llPointsx, llPointsy);
        poly.setText("Init Poly");
        poly.setLocateAtCenter(true);
        poly.setFillPaint(Color.pink);
        poly.setLinePaint(Color.red);
        omList.add(poly);
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
//        System.out.println(omg.getClass()+" cllllllllllllllllllllllass");
        if (omg instanceof OMSpline && !(omg instanceof MyOMPoly)){
            // Создан объект OMPoint, который нужно преобразовать в MyOMPoint
//            System.out.println("OMSpline");
            OMSpline spline = (OMSpline) omg;
//            System.out.println(spline.getRawllpts().length);
//            System.out.println(spline.getUnits());
            double[] rawllpts = spline.getRawllpts();
            int rawllptsLen = rawllpts.length;
            int[] xs = new int[rawllptsLen/2];
            int[] ys = new int[rawllptsLen/2];
            for(int i = 0;i<rawllptsLen/2;i++){
                xs[i]= (int) (rawllpts[i*2]*100);
                ys[i]=(int) (rawllpts[i*2+1]*100);
            }
            double lat=spline.getLat();
            double lon =spline.getLon();
            MyOMPoly poly = new MyOMPoly("New MyOMPoly",lat,lon, xs,ys);
            poly.setText("New MyOMPoly");
            poly.setRenderType(3);
            poly.setFillPaint(Color.pink);
            poly.setLinePaint(Color.red);
            poly.setLat(lat);
            poly.setLon(lon);
            poly.setLocation(xs,ys);
            omg = poly;
        }
        if (!doAction(omg, action)) {
            // null OMGraphicList on failure, should only occur if
            // OMGraphic is added to layer before it's ever been
            // on the map.
            setList(new OMGraphicList());
            doAction(omg, action);
        }

        omg.setLinePaint(Color.red);
        omg.setFillPaint(Color.pink);
        repaint();
    }
}

package layers;

import com.bbn.openmap.layer.DemoLayer;
import com.bbn.openmap.layer.policy.BufferedImageRenderPolicy;
import com.bbn.openmap.omGraphics.*;
import com.bbn.openmap.tools.drawing.DrawingTool;
import com.bbn.openmap.tools.drawing.DrawingToolRequestor;
import myOMGraphicTools.MyOMPoint;

import javax.swing.*;
import java.awt.*;

/**
 *  Слой точек
 *
 */
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

    /**
     * Добавление элементов в слой
     *
     * @return {@link OMGraphicList}
     * @see OMGraphicList
     */
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
     *  Создание изначальных элементов в слое
     *
     * @return {@link OMGraphicList}
     * @see OMGraphicList
     */
    public OMGraphicList init() {

        OMGraphicList omList = new OMGraphicList();


//        OMPoint point = new OMPoint((float) (Math.random() * 89f), (float) (Math.random() * -179f), 3);
        MyOMPoint myOMPoint = new MyOMPoint("Dipoint",  20.0, -70.0, 20);
        myOMPoint.setFillPaint(Color.MAGENTA);
        myOMPoint.setRenderType(3);
        myOMPoint.setOval(true);

        omList.add(myOMPoint);
        return omList;
    }

    /**
     * Получение Tool tip
     *
     * @param omg omg
     * @return {@link String}
     * @see String
     */
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
                return "MyOMPoint: " + point.getName();
            }

            return "MyOMPoint Layer Object: " + classname;
        }
    }

    /**
     * Отрисовка объекта
     *
     * @param omg omg
     * @param action action
     */
    @Override
    public void drawingComplete(OMGraphic omg, OMAction action) {
        if (!(omg instanceof OMPoint)) {
            JOptionPane.showMessageDialog(null, "В этом слое можно создавать объекты только OMPoint");
            repaint();
            return;
        }

        if ( !(omg instanceof MyOMPoint)) {
            // Создан объект OMPoint, который нужно преобразовать в MyOMPoint
            OMPoint omPoint = (OMPoint) omg;
            MyOMPoint point = new MyOMPoint("New MyOMPoint", omPoint.getLat(), omPoint.getLon(), omPoint.getRadius() + 10);
            point.setFillPaint(Color.MAGENTA);
            point.setOval(true);
            omg = point;
        }
        if (!doAction(omg, action)) {
            setList(new OMGraphicList());
            doAction(omg, action);
        }
        omg.setFillPaint(Color.MAGENTA);
        repaint();
    }
}
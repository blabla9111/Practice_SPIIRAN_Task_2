package myTools;

import com.bbn.openmap.omGraphics.OMGraphic;
import com.bbn.openmap.omGraphics.OMGraphicAdapter;
import com.bbn.openmap.omGraphics.OMPoint;
import com.bbn.openmap.proj.Projection;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class MyOMPoint extends OMPoint implements OMGraphic, Serializable {

    public String name;
    public Double x;
    public Double y;

    public int course;

    public int radius;

    public MyOMPoint(){

    }
    public MyOMPoint(String name, double x, double y, int radius){
        super(x,y, (int) radius);
        this.name = name;
        this.x=x;
        this.y=y;
        this.radius = radius;
        this.course = 0;
        this.set(x,y);

    }

    @Override
    public void  select(){
        System.out.println("select");

    }

    @Override
    public void deselect(){
        Window[] windows = Window.getWindows();
        System.out.println("**************");
//        for (Window win : windows) {
//            if (win instanceof JFrame)
//                win.setVisible(false);
//        }

    }

}

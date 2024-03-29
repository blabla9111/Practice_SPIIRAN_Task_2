package drawingTools;

import com.bbn.openmap.omGraphics.OMGraphic;
import com.bbn.openmap.tools.drawing.DrawingToolRequestor;
import com.bbn.openmap.tools.drawing.OMDrawingTool;
import myOMGraphicTools.MyOMPoint;
import myOMGraphicTools.MyOMPoly;

import javax.swing.*;
import java.awt.*;

public class MyCustomDrawingTool extends OMDrawingTool {

    public MyCustomDrawingTool() {
        super();
        setBehaviorMask(OMDrawingTool.QUICK_CHANGE_BEHAVIOR_MASK);
    }

    @Override
    public OMGraphic edit(OMGraphic g, DrawingToolRequestor requestor, boolean showGUI) {
        System.out.println("edit");
        if (g instanceof MyOMPoint) {
            makeChangeObjectParamsMyOMPoint(g);
        }
        else if(g instanceof MyOMPoly){
            System.out.println("_________"+g.getClass());
            makeChangeObjectParamsMyOMPoly(g);
        }
        System.out.println(g.getClass());
        return super.edit(g,requestor,showGUI);
    }


//    @Override
//    public JPopupMenu createPopupMenu() {
//        JPopupMenu popup =  super.createPopupMenu();
//        popup.removeAll();
//        popup.add(new JMenuItem("Dinara"));
//        popup.add(new JMenuItem("Gindullina"));
//        return popup;
//    }

    public void makeChangeObjectParamsMyOMPoint(OMGraphic g){
        JFrame frame = new JFrame("Test");
        MyOMPoint point = (MyOMPoint) g;
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        JTextField inputName = new JTextField(20);
        JLabel labelName =new JLabel("Name");
        JTextField inputX = new JTextField(20);
        JLabel labelX =new JLabel("X");
        JTextField inputY = new JTextField(20);
        JLabel labelY =new JLabel("Y");
        JTextField inputRadius = new JTextField(20);
        JLabel labelRadius =new JLabel("Radius");
        JTextField inputCourse = new JTextField(20);
        JLabel labelCourse =new JLabel("Course");
        inputName.setText(point.name);
        inputX.setText(String.valueOf(point.lat));
        inputY.setText(String.valueOf(point.lon));
        inputRadius.setText(String.valueOf(point.radius));
        inputCourse.setText(String.valueOf(point.course));
        JButton button = new JButton("Сохранить");
        button.addActionListener(e->{
            point.name=inputName.getText();
            point.lat=Double.parseDouble(inputX.getText());
            point.lon =Double.parseDouble(inputY.getText());
            point.radius=Integer.parseInt(inputRadius.getText());
            point.course=Integer.parseInt(inputCourse.getText());
        });
//        input.addActionListener(e -> {
//            JTextField source = (JTextField) e.getSource();
//            JOptionPane.showMessageDialog(null, "Text entered: " + source.getText());
//        });
        panel.add(labelName);
        panel.add(inputName);
        panel.add(labelX);
        panel.add(inputX);
        panel.add(labelY);
        panel.add(inputY);
        panel.add(labelRadius);
        panel.add(inputRadius);
        panel.add(labelCourse);
        panel.add(inputCourse);
        panel.add(button);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        point.setFillPaint(Color.CYAN);
        System.out.println("dfghj");
    }

    public void makeChangeObjectParamsMyOMPoly(OMGraphic g){
        JFrame frame = new JFrame("Test");
        MyOMPoly poly = (MyOMPoly) g;
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        JTextField inputName = new JTextField(20);
        inputName.setText(poly.name);
        JLabel labelName =new JLabel("Name");
        panel.add(labelName);
        panel.add(inputName);
        int[] xs = poly.getXs();
        int[] ys =poly.getYs();
        JTextField[] jTextFields = new JTextField[xs.length*2];
        for(int i =0;i<xs.length;i++){
            JLabel labelX =new JLabel("X_"+i);
            JTextField inputX = new JTextField(20);
            JLabel labelY =new JLabel("Y_"+i);
            JTextField inputY = new JTextField(20);
            inputX.setText(String.valueOf(xs[i]));
            inputY.setText(String.valueOf(ys[i]));
            panel.add(labelX);
            panel.add(inputX);
            panel.add(labelY);
            panel.add(inputY);
            jTextFields[i*2]=inputX;
            jTextFields[i*2+1]=inputY;
        }
        JButton button = new JButton("Сохранить");
        button.addActionListener(e->{
            poly.name = inputName.getText();
            for(int i=0;i<xs.length;i++){
                xs[i]= Integer.parseInt(jTextFields[i*2].getText());
                ys[i]= Integer.parseInt(jTextFields[i*2+1].getText());
            }
//            poly.setLocation(xs,ys);
            poly.xs = xs;
            poly.ys = ys;
            poly.setLocation(poly.getXs(),poly.getYs());
            poly.setRenderType(3);
            poly.setLocateAtCenter(true);
            poly.setLinePaint(Color.red);
            poly.setText(inputName.getText());
        });

//        input.addActionListener(e -> {
//            JTextField source = (JTextField) e.getSource();
//            JOptionPane.showMessageDialog(null, "Text entered: " + source.getText());
//        });

        panel.add(button);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        poly.setLinePaint(Color.CYAN);
        System.out.println(poly.name);
    }
}
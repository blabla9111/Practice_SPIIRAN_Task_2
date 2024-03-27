import com.bbn.openmap.omGraphics.OMGraphic;
import com.bbn.openmap.tools.drawing.DrawingToolRequestor;
import com.bbn.openmap.tools.drawing.OMDrawingTool;
import myTools.MyOMPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MyDrawingTool extends OMDrawingTool {

    public MyDrawingTool() {
        super();
        setBehaviorMask(OMDrawingTool.QUICK_CHANGE_BEHAVIOR_MASK);
    }




    @Override
    public boolean select(OMGraphic omg, DrawingToolRequestor req, MouseEvent e) {

        System.out.println("select Drawing tool");
        return  super.select(omg,req,e);
    }

    @Override
    public OMGraphic edit(OMGraphic g, DrawingToolRequestor requestor, boolean showGUI) {
        System.out.println("edit");
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        JTextField input = new JTextField(20);
        JButton button = new JButton("Enter");
        input.addActionListener(e -> {
            JTextField source = (JTextField) e.getSource();
            JOptionPane.showMessageDialog(null, "Text entered: " + source.getText());
        });
        panel.add(input);
        panel.add(button);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        MyOMPoint point = (MyOMPoint) g;
        point.setFillPaint(Color.CYAN);
        System.out.println(input.getText());
        return super.edit(g,requestor,showGUI);
    }


    @Override
    public JPopupMenu createPopupMenu() {
        JPopupMenu popup =  super.createPopupMenu();
        popup.removeAll();
        popup.add(new JMenuItem("Dinara"));
        popup.add(new JMenuItem("Gindullina"));
        return popup;
    }
}
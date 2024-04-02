package editPanel;

import com.bbn.openmap.omGraphics.OMGraphic;
import myOMGraphicTools.MyOMPoint;
import tools.MyJTextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyOMPointEditFrame implements EditFrame {

    private final String[] paramList = {"Name", "Lat", "Lon", "Radius", "Course"};
    private final String[] paramListDataType = {"String", "double", "double", "int", "int"};

    //    @Override
    public void editFrame(OMGraphic g) {
        JFrame frame = new JFrame("Edit");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);

        MyOMPoint point = (MyOMPoint) g;
        List<MyJTextField> fields = new ArrayList<>();
        String[] pointInfoList = point.getInfoAboutParams();
        for (int i = 0; i < paramList.length; i++) {
            String s = paramList[i];
            JLabel label = new JLabel(s);
            MyJTextField field = new MyJTextField(paramListDataType[i]);
            field.setText(pointInfoList[i]);
            panel.add(label);
            panel.add(field);
            fields.add(field);
        }
        JButton button = getButton(point, fields);
        panel.add(button);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private static JButton getButton(MyOMPoint point, List<MyJTextField> fields) {
        JButton button = new JButton("Сохранить");
        button.addActionListener(e -> {
            point.setName(fields.get(0).getText());
            point.setLat(fields.get(1).ckeckInput() ? Double.parseDouble(fields.get(1).getText()) : point.getLat());
            point.setLon(fields.get(2).ckeckInput() ? Double.parseDouble(fields.get(2).getText()) : point.getLon());
            point.setRadius(fields.get(3).ckeckInput() ? Integer.parseInt(fields.get(3).getText()) : point.getRadius());
            point.setCourse(fields.get(4).ckeckInput() ? Integer.parseInt(fields.get(4).getText()) : point.getCourse());
            point.set(point.getLat(), point.getLon());
            point.setRadius(point.getRadius());
            point.setRenderType(3);
            point.setOval(true);
        });
        return button;
    }
}

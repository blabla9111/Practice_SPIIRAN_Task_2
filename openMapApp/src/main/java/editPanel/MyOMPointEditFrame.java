package editPanel;

import com.bbn.openmap.omGraphics.OMGraphic;
import myOMGraphicTools.MyOMPoint;
import tools.MyJTextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Окно для редактирования MyOMPoint
 *
 */
public class MyOMPointEditFrame implements EditFrame {

    /**
     * Список названия параметров MyOMPoint
     *
     */
    private final String[] paramList = {"Name", "Lat", "Lon", "Radius", "Course"};
    /**
     * Список формата параметров MyOMPoint
     *
     */
    private final String[] paramListDataType = {"String", "double", "double", "int", "int"};

    /**
     * Сделать окно для редактирования
     *
     * @param g  -- экземпляр OMGraphic
     */
    @Override
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

    /**
     * Создать кнопку и добавить обработчик на неё
     *
     * @param point -- экземпляр MyOMPoint
     * @param fields -- список полей
     * @return {@link JButton}
     * @see JButton
     */
    private static JButton getButton(MyOMPoint point, List<MyJTextField> fields) {
        JButton button = new JButton("Сохранить");
        button.addActionListener(e -> {
            point.name = fields.get(0).getText();
            point.lat = fields.get(1).ckeckInput() ? Double.parseDouble(fields.get(1).getText()) : point.lat;
            point.lon=fields.get(2).ckeckInput() ? Double.parseDouble(fields.get(2).getText()) : point.lon;
            point.radius = fields.get(3).ckeckInput() ? Integer.parseInt(fields.get(3).getText()) : point.radius;
            point.course = fields.get(4).ckeckInput() ? Integer.parseInt(fields.get(4).getText()) : point.course;
            point.set(point.lat, point.lon);
            point.setRadius(point.radius);
            point.setRenderType(3);
            point.setOval(true);
        });
        return button;
    }
}

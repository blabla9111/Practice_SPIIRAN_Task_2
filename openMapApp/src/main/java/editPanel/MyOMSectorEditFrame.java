package editPanel;

import com.bbn.openmap.omGraphics.OMGraphic;
import com.bbn.openmap.proj.Length;
import com.bbn.openmap.proj.coords.LatLonPoint;
import myOMGraphicTools.MyOMSector;
import tools.MyJTextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Окно для редактирования MyOMSector
 *
 */
public class MyOMSectorEditFrame implements EditFrame {
    /**
     * Список названия параметров MyOMSector
     *
     */
    private final String[] paramList = {"Name", "center X", "center Y", "X-Radius", "Y-Radius", "Rotate Angle"};
    /**
     * Список формата параметров MyOMSector
     *
     */
    private final String[] paramListDataType = {"String", "double", "double", "double", "double", "double"};

    /**
     * Сделать окно для редактирования
     *
     * @param g  -- экземпляр OMGraphic
     */
    @Override
    public void editFrame(OMGraphic g) {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);

        MyOMSector sector = (MyOMSector) g;
        List<MyJTextField> fields = new ArrayList<>();
        String[] sectorInfoList = sector.getInfoAboutParams();
        for (int i = 0; i < paramList.length; i++) {
            String s = paramList[i];
            JLabel label = new JLabel(s);
            MyJTextField field = new MyJTextField(paramListDataType[i]);
            field.setText(sectorInfoList[i]);
            panel.add(label);
            panel.add(field);
            fields.add(field);
        }

        panel.add(getButton(sector, fields));
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Создать кнопку и добавить обработчик на неё
     *
     * @param sector -- экземпляр MyOMSector
     * @param fields -- список полей
     * @return {@link JButton}
     * @see JButton
     */
    private static JButton getButton(MyOMSector sector, List<MyJTextField> fields) {
        JButton button = new JButton("Сохранить");
        button.addActionListener(e -> {
            sector.setName(fields.get(0).getText());
            sector.setCenterX(fields.get(1).ckeckInput() ? Double.parseDouble(fields.get(1).getText()) : sector.getCenterX());
            sector.setCenterY(fields.get(2).ckeckInput() ? Double.parseDouble(fields.get(2).getText()) : sector.getCenterY());
            sector.setRadiusX(fields.get(3).ckeckInput() ? Double.parseDouble(fields.get(3).getText()) : sector.getRadiusX());
            sector.setRadiusY(fields.get(4).ckeckInput() ? Double.parseDouble(fields.get(4).getText()) : sector.getRadiusY());
            sector.setRotateAngle(fields.get(5).ckeckInput() ? Double.parseDouble(fields.get(5).getText()) : sector.getRotateAngle());
            sector.setCenter(new LatLonPoint.Double(sector.getCenterX(), sector.getCenterY()));
            sector.setRenderType(1);
            sector.setAxis(sector.getRadiusX(), sector.getRadiusY(), Length.NM);
            sector.setRotationAngle(sector.getRotateAngle());
            sector.setFillPaint(Color.orange);
        });
        return button;
    }
}

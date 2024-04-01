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

public class MyOMSectorEditFrame implements EditFrame {
    private final String[] paramList = {"Name", "center X", "center Y", "X-Radius", "Y-Radius", "Rotate Angle"};
    private final String[] paramListDataType = {"String", "double", "double", "double", "double", "double"};

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

    private static JButton getButton(MyOMSector sector, List<MyJTextField> fields) {
        JButton button = new JButton("Сохранить");
        button.addActionListener(e -> {
            sector.name = fields.get(0).getText();
            sector.centerX = fields.get(1).ckeckInput() ? Double.parseDouble(fields.get(1).getText()) : sector.centerX;
            sector.centerY = fields.get(2).ckeckInput() ? Double.parseDouble(fields.get(2).getText()) : sector.centerY;
            sector.radiusX = fields.get(3).ckeckInput() ? Double.parseDouble(fields.get(3).getText()) : sector.radiusX;
            sector.radiusY = fields.get(4).ckeckInput() ? Double.parseDouble(fields.get(4).getText()) : sector.radiusY;
            sector.rotateAngle = fields.get(5).ckeckInput() ? Double.parseDouble(fields.get(5).getText()) : sector.rotateAngle;
            sector.setCenter(new LatLonPoint.Double(sector.centerX, sector.centerY));
            sector.setRenderType(1);
            sector.setAxis(sector.radiusX, sector.radiusY, Length.NM);
            sector.setRotationAngle(sector.rotateAngle);
            sector.setFillPaint(Color.orange);
        });
        return button;
    }
}

package editPanel;

import com.bbn.openmap.omGraphics.OMGraphic;
import myOMGraphicTools.MyOMPoly;
import tools.MyJTextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *   Окно для редактирования MyOMPoly
 *
 */
public class MyOMPolyEditFrame implements EditFrame {
    /**
     * Список названия параметров MyOMPoly
     *
     */
    private final String[] paramList = {"Name", "X_", "Y_"};
    /**
     * Список формата параметров MyOMPoly
     *
     */
    private final String[] paramListDataType = {"String", "int", "int"};

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
        MyOMPoly poly = (MyOMPoly) g;

        List<MyJTextField> fields = new ArrayList<>();
        JLabel labelName = new JLabel(paramList[0]);
        MyJTextField inputName = new MyJTextField(paramListDataType[0]);
        fields.add(inputName);
        inputName.setText(poly.name);
        panel.add(labelName);
        panel.add(inputName);


        int[] xs = poly.getXs();
        int[] ys = poly.getYs();

        for (int i = 0; i < xs.length; i++) {
            JLabel labelX = new JLabel(paramList[1] + i);
            MyJTextField inputX = new MyJTextField(paramListDataType[1]);
            JLabel labelY = new JLabel(paramList[2] + i);
            MyJTextField inputY = new MyJTextField(paramListDataType[2]);
            inputX.setText(String.valueOf(xs[i]));
            inputY.setText(String.valueOf(ys[i]));
            panel.add(labelX);
            panel.add(inputX);
            panel.add(labelY);
            panel.add(inputY);
            fields.add(inputX);
            fields.add(inputY);
        }
        panel.add(getButton(poly, fields));
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Создать кнопку и добавить обработчик на неё
     *
     * @param poly -- экземпляр MyOMPoly
     * @param fields -- список полей
     * @return {@link JButton}
     * @see JButton
     */
    private static JButton getButton(MyOMPoly poly, List<MyJTextField> fields) {
        JButton button = new JButton("Сохранить");
        button.addActionListener(e -> {
            poly.name = fields.get(0).getText();
            int[] xs = poly.getXs();
            int[] ys = poly.getYs();
            for (int i = 0; i < xs.length; i++) {
                xs[i] = fields.get(i * 2 + 1).ckeckInput() ? Integer.parseInt(fields.get(i * 2 + 1).getText()) : xs[i];
                ys[i] = fields.get(i * 2 + 2).ckeckInput() ? Integer.parseInt(fields.get(i * 2 + 2).getText()) : ys[i];
            }
            poly.xs = xs;
            poly.ys = ys;
            poly.setText(poly.name);
            poly.setLocation(poly.getXs(), poly.getYs());
            poly.setRenderType(3);
            poly.setLocateAtCenter(true);
            poly.setLinePaint(Color.red);
        });
        return button;
    }
}

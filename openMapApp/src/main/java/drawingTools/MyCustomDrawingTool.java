package drawingTools;

import com.bbn.openmap.omGraphics.OMGraphic;
import com.bbn.openmap.tools.drawing.DrawingToolRequestor;
import com.bbn.openmap.tools.drawing.OMDrawingTool;
import editPanel.EditFrame;
import editPanel.MyOMPointEditFrame;
import editPanel.MyOMPolyEditFrame;
import editPanel.MyOMSectorEditFrame;
import myOMGraphicTools.MyOMPoint;
import myOMGraphicTools.MyOMPoly;
import myOMGraphicTools.MyOMSector;

import java.awt.*;

/**
 *  Класс для управления редактированием объектов
 *
 */
public class MyCustomDrawingTool extends OMDrawingTool {

    public MyCustomDrawingTool() {
        super();
        setBehaviorMask(OMDrawingTool.QUICK_CHANGE_BEHAVIOR_MASK);
    }

    /**
     * Метод вызывается после нажатия на объект (редактирование объекта)
     *
     * @param g -- экземпляр OMGraphic
     * @param requestor -- экземпляр DrawingToolRequestor
     * @param showGUI boolean
     * @return {@link OMGraphic}
     * @see OMGraphic
     */
    @Override
    public OMGraphic edit(OMGraphic g, DrawingToolRequestor requestor, boolean showGUI) {
        g.setFillPaint(Color.CYAN);
        EditFrame editFrame;
        if (g instanceof MyOMPoint) {
            editFrame = new MyOMPointEditFrame();
            editFrame.editFrame(g);
        } else if (g instanceof MyOMPoly) {
            editFrame = new MyOMPolyEditFrame();
            editFrame.editFrame(g);
        } else if (g instanceof MyOMSector) {
            editFrame = new MyOMSectorEditFrame();
            editFrame.editFrame(g);
        }
        return super.edit(g, requestor, showGUI);
    }
}
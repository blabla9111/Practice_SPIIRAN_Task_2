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

public class MyCustomDrawingTool extends OMDrawingTool {

    public MyCustomDrawingTool() {
        super();
        setBehaviorMask(OMDrawingTool.QUICK_CHANGE_BEHAVIOR_MASK);
    }

    @Override
    public OMGraphic edit(OMGraphic g, DrawingToolRequestor requestor, boolean showGUI) {
//        System.out.println("edit");
        g.setFillPaint(Color.CYAN);
        EditFrame editFrame;
        if (g instanceof MyOMPoint) {
            editFrame = new MyOMPointEditFrame();
            editFrame.editFrame(g);
        } else if (g instanceof MyOMPoly) {
//            System.out.println("_________"+g.getClass());
            editFrame = new MyOMPolyEditFrame();
            editFrame.editFrame(g);
        } else if (g instanceof MyOMSector) {
//            System.out.println("_________"+g.getClass());
            editFrame = new MyOMSectorEditFrame();
            editFrame.editFrame(g);
        }
//        System.out.println(g.getClass());
        return super.edit(g, requestor, showGUI);
    }
}
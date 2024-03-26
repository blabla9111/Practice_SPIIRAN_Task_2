import com.bbn.openmap.tools.drawing.OMDrawingTool;

import javax.swing.*;

public class MyDrawingTool extends OMDrawingTool {

    public MyDrawingTool() {
        super();
        setBehaviorMask(OMDrawingTool.QUICK_CHANGE_BEHAVIOR_MASK);
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
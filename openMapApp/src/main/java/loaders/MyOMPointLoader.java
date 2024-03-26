package loaders;

import com.bbn.openmap.omGraphics.*;
import com.bbn.openmap.tools.drawing.AbstractToolLoader;
import com.bbn.openmap.tools.drawing.EditClassWrapper;
import com.bbn.openmap.tools.drawing.EditToolLoader;
import editable.MyEditableOMPoint;
import myTools.MyOMPoint;

public class MyOMPointLoader extends AbstractToolLoader implements EditToolLoader {

    protected String graphicClassName ="myTools.MyOMPoint";
    @Override
    public void init() {
        EditClassWrapper ecw = new EditClassWrapper(graphicClassName, "com.bbn.openmap.omGraphics.EditableOMPoint", "editablepoint.gif", i18n.get(MyOMPointLoader.class,
                "myompoint",
                "DinaraMyPoint"));

        addEditClassWrapper(ecw);
    }

    public MyOMPointLoader(){
        init();
    }


    public EditableOMGraphic getEditableGraphic(String classname,
                                                GraphicAttributes ga) {
        if (classname.intern() == graphicClassName) {
            return new MyEditableOMPoint(ga);
        }
        return null;
    }

    public EditableOMGraphic getEditableGraphic(OMGraphic graphic) {
        if (graphic instanceof MyOMPoint) {
            return new MyEditableOMPoint((MyOMPoint) graphic);
        }
        return null;
    }
}

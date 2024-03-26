package editable;

import com.bbn.openmap.omGraphics.*;
import com.bbn.openmap.omGraphics.editable.PointStateMachine;
import com.bbn.openmap.proj.Projection;
import com.bbn.openmap.util.Debug;
import myTools.MyOMPoint;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MyEditableOMPoint extends EditableOMGraphic {

    protected GrabPoint gpc;
    protected OffsetGrabPoint gpo;
    protected MyOMPoint point;

    protected int lastRenderType = -1;

    public void init() {
        Debug.message("eomg", "EditableOMPoint.init()");
        this.setCanGrabGraphic(false);
//        this.setStateMachine(new PointStateMachine(this));
        this.gPoints = new GrabPoint[2];
    }
    public MyEditableOMPoint(MyOMPoint omc) {
        this.setGraphic(omc);
    }

    public MyEditableOMPoint(GraphicAttributes ga) {
        this.createGraphic(ga);
    }

    public MyEditableOMPoint() {
        this.createGraphic((GraphicAttributes)null);
    }
    @Override
    public void setGraphic(OMGraphic omGraphic) {

    }

    @Override
    public void createGraphic(GraphicAttributes ga) {
        this.init();
        this.stateMachine.setUndefined();
        int renderType = 0;
        if (ga != null) {
            renderType = ga.getRenderType();
        }

        if (Debug.debugging("eomg")) {
            Debug.output("EditableOMPoint.createGraphic(): rendertype = " + renderType);
        }

        switch (renderType) {
            case 1:
                this.point = new MyOMPoint(90.0, 180);
                break;
            case 3:
                this.point = new MyOMPoint(90.0, -180.0);
                break;
            default:
                this.point = new MyOMPoint(-1, -1);
        }

        if (ga != null) {
            ga.setTo(this.point);
        }

        this.assertGrabPoints();
    }

    @Override
    public OMGraphic getGraphic() {
        return null;
    }

    @Override
    public void setGrabPoints() {

    }

    @Override
    public void move(MouseEvent mouseEvent) {

    }

    @Override
    public boolean generate(Projection projection) {
        return false;
    }

    @Override
    public void regenerate(Projection projection) {

    }

    @Override
    public void render(Graphics graphics) {

    }

    public void assertGrabPoints() {
        int rt = this.getGraphic().getRenderType();
        if (rt != this.lastRenderType) {
            this.clearGrabPoints();
            this.lastRenderType = rt;
        }

        if (this.gpc == null) {
            this.gpc = new GrabPoint(-1, -1);
            this.gPoints[0] = this.gpc;
        }

        if (this.gpo == null) {
            this.gpo = new OffsetGrabPoint(-1, -1);
            this.gPoints[1] = this.gpo;
            this.gpo.addGrabPoint(this.gpc);
        }

    }

    protected void clearGrabPoints() {
        this.gpc = null;
        this.gpo = null;
        this.gPoints[0] = this.gpc;
        this.gPoints[1] = this.gpo;
    }
}

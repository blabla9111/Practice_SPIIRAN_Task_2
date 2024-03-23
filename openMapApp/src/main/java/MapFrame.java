import com.bbn.openmap.MapBean;
import com.bbn.openmap.PropertyHandler;
import com.bbn.openmap.gui.BasicMapPanel;
import com.bbn.openmap.gui.MapPanel;
import com.bbn.openmap.layer.shape.ShapeLayer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapFrame extends javax.swing.JFrame {

    static MapPanel mapPanel = new BasicMapPanel();
    static MapBean mapBean = mapPanel.getMapBean();

    private void initMap() {
        PropertyHandler propertyHandler = null;
        try {
            propertyHandler = new PropertyHandler.Builder().setPropertiesFile("src/main/resources/openmap.properties").build();
        } catch (IOException ex) {
            Logger.getLogger(MapFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ShapeLayer shapeLayer = new ShapeLayer();
        if (propertyHandler != null) {
            shapeLayer.setProperties(propertyHandler.getProperties());
        }

        // Add the political layer to the map
        mapBean.add(shapeLayer);
    }

    /**
     * Creates new form MapFrame
     */
    public MapFrame() {
        super("Simple Map");
        initMap();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        // Content suppressed

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(mapBean, java.awt.BorderLayout.CENTER);

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MapFrame().setVisible(true);
            }
        });
    }
}
//import com.bbn.openmap.*;
//import com.bbn.openmap.event.OMMouseMode;
//import com.bbn.openmap.gui.*;
//import com.bbn.openmap.layer.GraticuleLayer;
//import com.bbn.openmap.layer.shape.ShapeLayer;
//import com.bbn.openmap.proj.coords.LatLonPoint;
//
//import java.io.IOException;
//import java.util.concurrent.CompletableFuture;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class MapFrame extends OpenMapFrame {
//
//    static MapPanel mapPanel = new BasicMapPanel();
//    static MapBean mapBean = mapPanel.getMapBean();
//
//    private void initMap() {
//        try {
//            // Get the default MapHandler the BasicMapPanel created.
//            MapHandler mapHandler = mapPanel.getMapHandler();
//            // Set the map's center
//            mapPanel.getMapBean().setCenter(new LatLonPoint.Double(38.0, 24.5));
//            // Set the map's scale 1:120 million
//            mapPanel.getMapBean().setScale(120000000f);
//            /*
//             * Create and add a LayerHandler to the MapHandler. The LayerHandler
//             * manages Layers, whether they are part of the map or not.
//             * layer.setVisible(true) will add it to the map. The LayerHandler
//             * has methods to do this, too. The LayerHandler will find the
//             * MapBean in the MapHandler.
//             */
//            mapHandler.add(new LayerHandler());
//
//            CompletableFuture.supplyAsync(this::getShapeLayer)
//                    .thenAcceptAsync(
//                            shapeLayer -> {
//                                // Add the political layer to the map
////                                mapHandler.add(shapeLayer);
////                                mapHandler.add(new GraticuleLayer());
////                                mapHandler.add(new MouseDelegator());
////                                // Add OMMouseMode, which handles how the map reacts to mouse
////                                // movements
////                                mapHandler.add(new OMMouseMode());
////                                mapHandler.add(new ScaleTextPanel[5]);
//
//                                MapFrame.this.revalidate();
//                            });
//            // Add the map to the frame
//            mapHandler.add(this);
//        } catch (MultipleSoloMapComponentException msmce) {
//            // The MapHandler is only allowed to have one of certain
//            // items. These items implement the SoloMapComponent
//            // interface. The MapHandler can have a policy that
//            // determines what to do when duplicate instances of the
//            // same type of object are added - replace or ignore.
//
//            // In this example, this will never happen, since we are
//            // controlling that one MapBean, LayerHandler,
//            // MouseDelegator, etc is being added to the MapHandler.
//        }
//    }
//
//    /**
//     * Creates new form MapFrame
//     */
//    public MapFrame() {
//        super("Simple Map");
//
//        initComponents();
//        initMap();
//    }
//
//    @SuppressWarnings("unchecked")
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">
//    private void initComponents() {
//        // Content suppressed
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        getContentPane().add(mapBean, java.awt.BorderLayout.CENTER);
//
//        pack();
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MapFrame().setVisible(true);
//            }
//        });
//    }
//
//    private ShapeLayer getShapeLayer() {
//        PropertyHandler propertyHandler = null;
//        try {
//            propertyHandler = new PropertyHandler.Builder().setPropertiesFile("src/main/resources/openmap.properties").setPropertyPrefix("shapePolitical").build();
//        } catch (IOException ex) {
//            Logger.getLogger(MapFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //ShapeLayer:
//        ShapeLayer shapeLayer = new ShapeLayer();
//        if (propertyHandler != null) {
//            shapeLayer.setProperties(propertyHandler.getPropertyPrefix(), propertyHandler.getProperties(propertyHandler.getPropertyPrefix()));
//        }
//        return shapeLayer;
//    }
//}
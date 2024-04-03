import com.bbn.openmap.Environment;
import com.bbn.openmap.MapHandler;
import com.bbn.openmap.PropertyHandler;
import com.bbn.openmap.gui.MapPanel;
import com.bbn.openmap.gui.OpenMapFrame;
import com.bbn.openmap.gui.OverlayMapPanel;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpenMap {
    /**
     * Главная панель приложения
     */
    private MapPanel mapPanel;

    /**
     * Точка входа в приложение
     *
     * @param args
     */
    public static void main(String[] args) {
        OpenMap op = new OpenMap();


        SwingUtilities.invokeLater(() -> {
            OpenMap.create("./openmap.properties").showInFrame();
        });
    }

    /**
     * Настройка без обработчика
     */
    public OpenMap() {
        this((PropertyHandler) null);
    }

    /**
     * Настройка панели с обработчиком
     *
     * @param propertyHandler
     */
    private OpenMap(PropertyHandler propertyHandler) {
        configureMapPanel(propertyHandler);
    }

    /**
     * создание OpenMap через файл конфигурации
     *
     * @param propertiesFile path to {@code openmap.properties}
     * @return new {@code OpenMap} instance
     */
    public static OpenMap create(String propertiesFile) {
        return new OpenMap(configurePropertyHandler(propertiesFile));
    }

    /**
     * Создание обработчика через файл конфигурации
     *
     * @param propertiesFile path to {@code openmap.properties}
     * @return the respective {@code PropertyHandler} or an empty one if an
     * error occurs
     * @throws MalformedURLException, IOException
     */
    private static PropertyHandler configurePropertyHandler(String propertiesFile) {
        try {
            return new PropertyHandler.Builder().setPropertiesFile(propertiesFile).build();
        } catch (MalformedURLException murle) {
            Logger.getLogger(OpenMap.class.getName()).log(Level.WARNING, murle.getMessage(), murle);
        } catch (IOException ioe) {
            Logger.getLogger(OpenMap.class.getName()).log(Level.WARNING, ioe.getMessage(), ioe);
        }
        return new PropertyHandler();
    }

    /**
     * @return the MapHandler
     */
    public MapHandler getMapHandler() {
        return mapPanel.getMapHandler();
    }

    /**
     * Создание окна приложения
     */
    private void showInFrame() {
        MapHandler mapHandler = getMapHandler();
        OpenMapFrame omf = (OpenMapFrame) mapHandler.get(OpenMapFrame.class
        );
        if (omf == null) {
            omf = new OpenMapFrame(Environment.get(Environment.Title));
            PropertyHandler propertyHandler = (PropertyHandler) mapHandler.get(PropertyHandler.class
            );
            if (propertyHandler != null) {
                // Use the default property prefix for the default window
                // property settings.
                omf.setProperties("openmap", propertyHandler.getProperties());
            }
            getMapHandler().add(omf);
        }
        omf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        omf.setVisible(true);
    }

    /**
     * Создание главной панели приложения
     *
     * @param propertyHandler
     */
    private void configureMapPanel(PropertyHandler propertyHandler) {
        mapPanel = new OverlayMapPanel(propertyHandler, false);
    }
}
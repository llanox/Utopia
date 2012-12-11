package co.edu.udea.ludens.applet.gui;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.domain.Population;
import com.genuts.gameui.Sprite;
import java.awt.Image;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class IndicatorContainer {

    private HashMap<String, JComponent> indicatorMap = new HashMap<String, JComponent>();
    public static int SIZE_IMAGE_INDICATOR = 30;

    public void updateOrCreateFactorIndicator(List<Object> data, JComponent panel) {
        if (data == null) {

            return;
        }

        String[] columnNames = {"Nombre", "Capacidad", "Cobertura (%)", "Nivel"};
        JTable table = (JTable) panel;

        for (Object o : data) {
            updateLevelNotification((Element) o);
        }

        ElementTableModel model = new ElementTableModel(columnNames, data);
        table.setModel(model);
    }

    private void updateLevelNotification(Element el) {
        Image notification = MapDashboard.imagesMap.get(MapDashboard.FILE_PREFIX + el.getLevel());
        Sprite spNotification = MapDashboard.spritesMap.get(el.getIncrementable().getName());

        if (notification == null) {
            notification = MapDashboard.imagesMap.get(MapDashboard.FILE_PREFIX);
        }

        if (spNotification != null) {
            spNotification.setImage(notification);
        }
    }

    public void updateOrCreateMaterialIndicator(List<Object> data, JComponent panel) {
        if (data == null) {

            return;
        }

        String[] columnNames = {"Nombre", "Inventario", "Produccion", "Nivel"};
        JTable table = (JTable) panel;

        for (Object o : data) {
            updateLevelNotification((Element) o);
        }

        ElementTableModel model = new ElementTableModel(columnNames, data);
        table.setModel(model);
    }

    public void updateOrCreatePopulationIndicator(Population el, JPanel panel) {
        if (el == null) {

            return;
        }

        StringBuilder bf;
        JLabel indicator;

        indicator = (JLabel) indicatorMap.get(el.getIncrementable().getName());
        if (indicator == null) {
            indicator = new JLabel();
            Image image = MapDashboard.imagesMap.get(el.getIncrementable().getName());

            Image resizedImage = image.getScaledInstance(SIZE_IMAGE_INDICATOR, SIZE_IMAGE_INDICATOR, Image.SCALE_DEFAULT);
            indicator.setIcon(new ImageIcon(resizedImage));
            indicatorMap.put(el.getIncrementable().getName(), indicator);
            panel.add(indicator);
        }

        bf = new StringBuilder();
        bf.append(" ");
        bf.append(el.getIncrementable().getName());
        bf.append(" ");
        bf.append(" total: ");
        bf.append(el.getQuantity());
        bf.append(" ");
        indicator.setText(bf.toString());
    }

    void updateOrCreateMessageEvents(List<Object> data, JPanel indicatorsMarquee) {
        if (data == null) {

            return;
        }

        for (Object o : data) {
            MessageEvent event = (MessageEvent) o;
            System.out.println(" " + event.getMsg());
        }
    }
}
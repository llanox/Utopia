/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ludens.applet.gui;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.LevelConstraint;
import co.edu.udea.ludens.enums.EnumDataType;
import java.awt.Color;
import java.awt.Image;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author juanga
 */
class LevelConstraintsContainer {
    public static final String NO_AVAIBLE_DATA = "NO DISPONIBLE";

    public HashMap<String, PanelElement> panelUpLevelElement = new HashMap<String, PanelElement>();
    public HashMap<String, ItemResource> actualResources = new HashMap<String, ItemResource>();
    public HashMap<String, ItemResource> requiredResources = new HashMap<String, ItemResource>();
    public HashMap<String, Element> mapElements = new HashMap<String, Element>();
    public HashMap<String, Element> mapMaterials = new HashMap<String, Element>();
    public static int SIZE_IMAGE_RESOURCE = 30;
    public static final String MSG_UP_LEVEL ="¿Subir al nivel %s?";
    public static final String MSG_UP_LEVEL_TIME ="Debe esperar %s segundos.";

    public PanelElement createUpLevelContainer(String selectedElement) {

        PanelElement panel = panelUpLevelElement.get(selectedElement);
        System.out.println("Selected element " + selectedElement);

        if (panel == null) {

            Image image = MapDashboard.imagesMap.get(selectedElement);
            panel = new PanelElement();
            ImageIcon icon = new ImageIcon(image);
            panel.getLbIcon().setIcon(icon);
            panel.getLbResourceName().setText(selectedElement);

            JPanel actual = panel.getPnlActual();
            actual.setLayout(new MigLayout("wrap 1"));

            JPanel required = panel.getPnlRequired();
            required.setLayout(new MigLayout("wrap 1"));




            panelUpLevelElement.put(selectedElement, panel);
        }



        updateLevelConstraints(selectedElement);

        return panel;
    }

    private void updateLevelConstraints(String selectedElement) {
        PanelElement panel = panelUpLevelElement.get(selectedElement);
        JPanel actual = panel.getPnlActual();
        actual.removeAll();
        JPanel required = panel.getPnlRequired();
        required.removeAll();

        Element element = mapElements.get(selectedElement);

        int level = element.getLevel();
        level++;
        String msgLevel = String.format(MSG_UP_LEVEL, level);
        panel.getLbAskuplevel().setText(msgLevel);

        Integer time = element.getActualUpgradingTime();

        if(time==null)
            time = element.getInitialUpgradingTime();
        if(time==null)
            time=0;

        msgLevel = String.format(MSG_UP_LEVEL_TIME, time);
        panel.getLbLevelProduction().setText(msgLevel);

        Image image = MapDashboard.imagesMap.get(MapDashboard.FILE_PREFIX+level);
        panel.getLbAskuplevel().setIcon(new ImageIcon(image));
      

        List<LevelConstraint> constraints = element.getLevelConstraints().get(level + "");

        Collections.sort(constraints, new KeyComparator());


        if (constraints != null) {
            for (LevelConstraint ctr : constraints) {
                updatePnlResources(ctr.getElementName(), ctr.getQuantity(), required, requiredResources);

            }
        }

        Map<String,Element> mapa  = sortMapByKey(mapMaterials);

        for (Object key : mapa.keySet()) {
            Element el = mapa.get(key);
            updatePnlResources(el.getIncrementable().getName(), el.getQuantity(), actual, actualResources);
        }

        if (constraints == null || constraints.isEmpty()) {

            required.add(new JLabel(NO_AVAIBLE_DATA));
            return;
        }




    }

    private void updatePnlResources(String elementName, int quantity, JPanel panel, HashMap<String, ItemResource> itemResources) {

        ItemResource pnlRsrc = itemResources.get(elementName);

        if (pnlRsrc == null) {
            pnlRsrc = new ItemResource();
            Image image = MapDashboard.imagesMap.get(elementName);
            Image resizedImage = image.getScaledInstance(SIZE_IMAGE_RESOURCE, SIZE_IMAGE_RESOURCE, Image.SCALE_DEFAULT);
            pnlRsrc.getResourceImage().setIcon(new ImageIcon(resizedImage));
            pnlRsrc.getResourceName().setText(elementName);

            itemResources.put(elementName, pnlRsrc);



        }
        pnlRsrc.getResourceQuantity().setText(quantity + "");
        pnlRsrc.getResourceQuantity().setForeground(Color.black);
        if (panel != null) {
            panel.add(pnlRsrc);
        }

        validateRequeriments();

    }

    public void updateElements(List<Object> elements, EnumDataType dataType) {


        for (Object o : elements) {
            Element el = (Element) o;
            mapElements.put(el.getIncrementable().getName(), el);

        }

        if (EnumDataType.MATERIAL == dataType) {

            for (Object o : elements) {
                Element el = (Element) o;
                mapMaterials.put(el.getIncrementable().getName(), el);
                updatePnlResources(el.getIncrementable().getName(), el.getQuantity(), null, actualResources);
            }


        }

    }

    private void validateRequeriments() {

        for (Object key : actualResources.keySet()) {

            ItemResource actual = actualResources.get(key);
            ItemResource required = requiredResources.get(key);

          
            if (actual == null || required == null) {
                return;
            }

            String actValue = actual.getResourceQuantity().getText();
            String reqValue = required.getResourceQuantity().getText();

            if (actValue == null || actValue.isEmpty() || reqValue == null || reqValue.isEmpty()) {
                return;
            }

            int actualValue = Integer.parseInt(actValue);
            int requiredValue = Integer.parseInt(reqValue);

            if (actualValue < requiredValue) {
                actual.getResourceQuantity().setForeground(Color.red);

            }


        }

    }


    private Map<String, Element> sortMapByKey(HashMap<String, Element> aItems) {
        TreeMap<String, Element> result = new TreeMap<String, Element>(String.CASE_INSENSITIVE_ORDER);
        result.putAll(aItems);

        return result;
    }

   public  class KeyComparator implements Comparator<LevelConstraint>{

        @Override
        public int compare(LevelConstraint o1, LevelConstraint o2) {

            return o1.getElementName().compareToIgnoreCase(o2.getElementName());

        }




}

}



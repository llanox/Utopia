/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ludens.applet.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author juanga
 */
public class ItemResource extends JPanel {

    private JLabel resourceImage;
    private JLabel resourceName;
    private JLabel resourceQuantity;

    public ItemResource() {
        this.setLayout(new MigLayout("wrap3"));

        resourceImage = new JLabel();
        resourceName = new JLabel();
        resourceQuantity = new JLabel();

        add(resourceImage);
        add(resourceName);
        add(resourceQuantity);
    }

    /**
     * @return the resourceImage
     */
    public JLabel getResourceImage() {
        return resourceImage;
    }

    /**
     * @param resourceImage the resourceImage to set
     */
    public void setResourceImage(JLabel resourceImage) {
        this.resourceImage = resourceImage;
    }

    /**
     * @return the resourceName
     */
    public JLabel getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName the resourceName to set
     */
    public void setResourceName(JLabel resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return the resourceQuantity
     */
    public JLabel getResourceQuantity() {
        return resourceQuantity;
    }

    /**
     * @param resourceQuantity the resourceQuantity to set
     */
    public void setResourceQuantity(JLabel resourceQuantity) {
        this.resourceQuantity = resourceQuantity;
    }
}

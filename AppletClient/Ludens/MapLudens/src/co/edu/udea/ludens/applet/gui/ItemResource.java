package co.edu.udea.ludens.applet.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

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

    public JLabel getResourceImage() {

        return (this.resourceImage);
    }

    public void setResourceImage(JLabel resourceImage) {
        this.resourceImage = resourceImage;
    }

    public JLabel getResourceName() {

        return (this.resourceName);
    }

    public void setResourceName(JLabel resourceName) {
        this.resourceName = resourceName;
    }

    public JLabel getResourceQuantity() {

        return (this.resourceQuantity);
    }

    public void setResourceQuantity(JLabel resourceQuantity) {
        this.resourceQuantity = resourceQuantity;
    }
}
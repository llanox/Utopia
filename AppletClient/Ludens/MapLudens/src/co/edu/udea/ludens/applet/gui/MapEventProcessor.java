/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ludens.applet.gui;

import co.edu.udea.ludens.applet.listeners.MapEventListener;
import co.edu.udea.ludens.applet.restful.UtopiaRestClient;
import com.genuts.gameui.PlayField;
import com.genuts.gameui.Sprite;
import com.genuts.gameui.SpriteCollisionManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author juanga
 */
public class MapEventProcessor implements MouseListener, ActionListener, WindowListener {

    public static final String CANCEL = " Cancelar ";
    public static final String UP_LEVEL = " Subir ";
    private PlayField playfield = null;
    private JDialog dialog;
    private JButton btnUpLevel = new JButton(UP_LEVEL);
    private JButton btnCancel = new JButton(CANCEL);
    private UtopiaRestClient restClient;
    private String actualElement = "";
    private List<MapEventListener> mapEventListeners = new ArrayList<MapEventListener>();
    private LevelConstraintsContainer panelLevelContainer;

    public MapEventProcessor(PlayField playfield) {
        this.playfield = playfield;
        this.btnUpLevel.addActionListener(this);
        this.btnCancel.addActionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        Sprite sprite = ((SpriteCollisionManager) playfield.getCollisionManager()).getSpriteAt(playfield.getXOffset() + e.getX(), playfield.getYOffset() + e.getY());


        if (sprite != null && sprite instanceof SpriteUtopia) {


            actualElement = ((SpriteUtopia) sprite).getName();

            if (actualElement == null) {
                return;
            }

            PanelElement panel = panelLevelContainer.createUpLevelContainer(actualElement);

            JOptionPane joptionPane = new JOptionPane();
            joptionPane.setMessage(panel);
            joptionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
            joptionPane.setOptionType(0);
            btnUpLevel.setEnabled(true);
            
            joptionPane.setOptions(new Object[]{btnUpLevel, btnCancel});

            dialog = joptionPane.createDialog(null, "Utopia");
            dialog.setLocation(playfield.getXOffset() + e.getX(), playfield.getYOffset() + e.getY());
            dialog.setVisible(true);
            dialog.setDefaultCloseOperation(0);
            dialog.addWindowListener(this);




        } else {

            if (dialog != null) {
                this.dialog.setVisible(false);
            }

        }

        notifyEvent();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        Object o = e.getSource();

        if (o == btnUpLevel) {
            upLevel(actualElement);
        }

        dialog.setVisible(false);
        notifyEvent();



    }

    private void upLevel(final String element) {

        Runnable restRequest = new Runnable() {

            public void run() {

                restClient.upLevel(element);


            }
        };
        SwingUtilities.invokeLater(restRequest);


    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
        dialog.setVisible(false);
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowClosing(WindowEvent paramWindowEvent) {
        dialog.setVisible(false);
    }

    public void windowDeactivated(WindowEvent e) {
    }

    /**
     * @return the restClient
     */
    public UtopiaRestClient getRestClient() {
        return restClient;
    }

    /**
     * @param restClient the restClient to set
     */
    public void setRestClient(UtopiaRestClient restClient) {
        this.restClient = restClient;
    }

    public void addMapEventListener(MapEventListener listener) {
        this.mapEventListeners.add(listener);
    }

    public void removeMapEventListener(MapEventListener listener) {
        mapEventListeners.remove(listener);
    }

    public void notifyEvent() {

        for (MapEventListener listener : mapEventListeners) {
            listener.eventHappening();
        }

    }

    public void setLevelContainer(LevelConstraintsContainer levelContainer) {
        this.panelLevelContainer = levelContainer;
    }
}

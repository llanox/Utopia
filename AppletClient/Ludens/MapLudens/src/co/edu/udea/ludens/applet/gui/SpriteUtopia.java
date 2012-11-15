package co.edu.udea.ludens.applet.gui;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import com.genuts.gameui.Sprite;
import java.awt.Image;

/**
 *
 * @author juanga
 */
public class SpriteUtopia extends Sprite {

    private String name;

    public SpriteUtopia(Image image) {
        super(image);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}

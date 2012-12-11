package co.edu.udea.ludens.applet.gui;

import com.genuts.gameui.Sprite;
import java.awt.Image;

public class SpriteUtopia extends Sprite {

    private String name;

    public SpriteUtopia(Image image) {
        super(image);
    }

    public String getName() {
        
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
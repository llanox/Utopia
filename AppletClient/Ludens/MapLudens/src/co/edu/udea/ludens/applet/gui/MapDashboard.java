package co.edu.udea.ludens.applet.gui;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import co.edu.udea.ludens.applet.listeners.ElementListener;
import co.edu.udea.ludens.applet.listeners.MapEventListener;
import co.edu.udea.ludens.applet.restful.UtopiaRestClient;
import co.edu.udea.ludens.applet.util.ElementEvent;
import co.edu.udea.ludens.applet.util.LudensConstants;
import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.domain.Population;
import co.edu.udea.ludens.enums.EnumDataType;
import co.edu.udea.ludens.util.MessageListener;
import com.genuts.gameui.PlayField;
import com.genuts.gameui.Sprite;
import com.genuts.gameui.SpriteCollisionManager;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.util.HashMap;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MapDashboard extends JApplet implements Updatable, ElementListener, MessageListener, MapEventListener {

    public static final int LEVEL_MAXIMUM = 16;
    public static final String FILE_PREFIX = "not_";
    public static final String RESOURCES_PATH = "/co/edu/udea/ludens/applet/resources/";
    public static HashMap<String, Image> imagesMap = new HashMap<String, Image>();
    public static HashMap<String, Sprite> spritesMap = new HashMap<String, Sprite>();
    private PlayField playfield = null;
    private MapDashboardPanel mapPanel = new MapDashboardPanel();
    private JPanel indicatorsMarquee = new JPanel();
    private IndicatorContainer container = new IndicatorContainer();
    private LevelConstraintsContainer levelContainer = new LevelConstraintsContainer();
    private UtopiaRestClient client;
    private boolean stopped = false;
    private Thread worker;

    @Override
    public synchronized void init() {
        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        setLayout(null);
        mapPanel.getMap().setLayout(null);

        String login = this.getParameter(LudensConstants.LOGIN);
        String urlBase = this.getParameter(LudensConstants.URL_BASE);

        if (login == null || urlBase == null) {
            JOptionPane.showMessageDialog(this, LudensConstants.ERROR_MESSAGE_CONNECTION);
        }

        if (login == null) {
            login = "Gabo";
        }
        if (urlBase == null) {
            urlBase = "http://localhost:8080/Utopia";
        }

        client = new UtopiaRestClient(login, urlBase);
        client.addElementListener(this);

        // Loading images        
        Image tileMap = getImage(getClass().getResource(RESOURCES_PATH + "map.png"));
        Image tileForniture = getImage(getClass().getResource(RESOURCES_PATH + "forniture.png"));
        Image tileFactory = getImage(getClass().getResource(RESOURCES_PATH + "factory.png"));
        Image tileEducation = getImage(getClass().getResource(RESOURCES_PATH + "education.png"));
        Image tileHealth = getImage(getClass().getResource(RESOURCES_PATH + "hospital.png"));
        Image tileWood = getImage(getClass().getResource(RESOURCES_PATH + "wood.png"));
        Image tileWater = getImage(getClass().getResource(RESOURCES_PATH + "water.png"));
        Image tileMud = getImage(getClass().getResource(RESOURCES_PATH + "mud.png"));
        Image tileIron = getImage(getClass().getResource(RESOURCES_PATH + "mine.png"));
        Image tileFood = getImage(getClass().getResource(RESOURCES_PATH + "food.png"));
        Image tilePeople = getImage(getClass().getResource(RESOURCES_PATH + "people.png"));

        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(tileMap, 0);
        tracker.addImage(tileForniture, 0);
        tracker.addImage(tileFactory, 0);
        tracker.addImage(tileEducation, 0);
        tracker.addImage(tileHealth, 0);
        tracker.addImage(tileWood, 0);
        tracker.addImage(tileWater, 0);
        tracker.addImage(tileMud, 0);
        tracker.addImage(tileIron, 0);
        tracker.addImage(tileFood, 0);
        tracker.addImage(tilePeople, 0);

        //Default image
        Image tileNotifications;

        for (int i = 0; i <= LEVEL_MAXIMUM; i++) {
            tileNotifications = getImage(getClass().getResource(RESOURCES_PATH + FILE_PREFIX + i + ".png"));
            tracker.addImage(tileNotifications, 0);
            imagesMap.put(FILE_PREFIX + i, tileNotifications);
        }

        try {
            tracker.waitForID(0);
        } catch (InterruptedException e) {
            System.out.println("Loading interrupted");
        }

        int mapWidth = tileMap.getWidth(null);
        int mapHeight = tileMap.getHeight(null);

        mapPanel.setSize(new Dimension(mapWidth + 400, mapHeight + 600));

        int tileWidth = 0;
        int tileHeight = 0;
        Point point = null;
        SpriteUtopia spTile = null;

        // Creates the playfield
        SpriteCollisionManager collisionManager = new SpriteCollisionManager(10);
        playfield = new PlayField(collisionManager, mapWidth + 100, mapHeight + 600);
        playfield.setBackground(new java.awt.Color(64, 64, 64));
        playfield.setBackgroundImage(tileMap);

        spTile = new SpriteUtopia(tileForniture);
        Sprite spNotification = new Sprite(imagesMap.get(FILE_PREFIX + 0));
        //Elemento 1 x=18 y=98
        spTile.setPosition(18, 98);

        tileWidth = (spTile.getWidth()) / 2;
        tileHeight = (spTile.getHeight()) / 2;
        point = spTile.getPosition();
        spNotification.setPosition(point.x + tileWidth, point.y + tileHeight);

        spTile.setName("Desarrollo Urbano");
        imagesMap.put(spTile.getName(), tileForniture);

        playfield.addSprite(spTile);
        playfield.addSprite(spNotification);

        spritesMap.put(spTile.getName(), spNotification);

        spTile = new SpriteUtopia(tileFactory);
        spNotification = new Sprite(imagesMap.get(FILE_PREFIX + 0));
        //Elemento 2 x=115 y=38
        spTile.setPosition(115, 38);

        tileWidth = (spTile.getWidth()) / 2;
        tileHeight = (spTile.getHeight()) / 2;
        point = spTile.getPosition();
        spNotification.setPosition(point.x + tileWidth, point.y + tileHeight);

        spTile.setName("Industria");
        imagesMap.put(spTile.getName(), tileFactory);

        playfield.addSprite(spTile);
        playfield.addSprite(spNotification);

        spritesMap.put(spTile.getName(), spNotification);

        spTile = new SpriteUtopia(tileEducation);
        spNotification = new Sprite(imagesMap.get(FILE_PREFIX + 0));

        //Elemento 3 x=115 y=158
        spTile.setPosition(115, 158);

        tileWidth = (spTile.getWidth()) / 2;
        tileHeight = (spTile.getHeight()) / 2;
        point = spTile.getPosition();
        spNotification.setPosition(point.x + tileWidth, point.y + tileHeight);

        spTile.setName("Educacion");
        imagesMap.put(spTile.getName(), tileEducation);

        playfield.addSprite(spTile);
        playfield.addSprite(spNotification);

        spritesMap.put(spTile.getName(), spNotification);

        spTile = new SpriteUtopia(tileHealth);
        spNotification = new Sprite(imagesMap.get(FILE_PREFIX + 0));

        //Elemento 4 x=205 y=98
        spTile.setPosition(205, 98);
        tileWidth = (spTile.getWidth()) / 2;
        tileHeight = (spTile.getHeight()) / 2;
        point = spTile.getPosition();
        spNotification.setPosition(point.x + tileWidth, point.y + tileHeight);

        spTile.setName("Salud");
        imagesMap.put(spTile.getName(), tileHealth);

        playfield.addSprite(spTile);
        playfield.addSprite(spNotification);

        spritesMap.put(spTile.getName(), spNotification);

        spTile = new SpriteUtopia(tileWood);
        spNotification = new Sprite(imagesMap.get(FILE_PREFIX + 0));

        //Elemento 5 x=300 y=90
        spTile.setPosition(300, 90);
        tileWidth = (spTile.getWidth()) / 2;
        tileHeight = (spTile.getHeight()) / 2;
        point = spTile.getPosition();
        spNotification.setPosition(point.x + tileWidth, point.y + tileHeight);

        spTile.setName("Madera");
        imagesMap.put(spTile.getName(), tileWood);

        playfield.addSprite(spTile);
        playfield.addSprite(spNotification);

        spritesMap.put(spTile.getName(), spNotification);

        spTile = new SpriteUtopia(tileWater);
        spNotification = new Sprite(imagesMap.get(FILE_PREFIX + 0));
        //Elemento 6 x=348 y=0
        spTile.setPosition(348, 0);
        tileWidth = (spTile.getWidth()) / 2;
        tileHeight = (spTile.getHeight()) / 2;
        point = spTile.getPosition();
        spNotification.setPosition(point.x + tileWidth, point.y + tileHeight);

        spTile.setName("Agua");
        imagesMap.put(spTile.getName(), tileWater);

        playfield.addSprite(spTile);
        playfield.addSprite(spNotification);

        spritesMap.put(spTile.getName(), spNotification);

        //********

        spTile = new SpriteUtopia(tileMud);
        spNotification = new Sprite(imagesMap.get(FILE_PREFIX + 0));

        //Elemento 7 x=420 y=146
        spTile.setPosition(420, 146);
        tileWidth = (spTile.getWidth()) / 2;
        tileHeight = (spTile.getHeight()) / 2;
        point = spTile.getPosition();
        spNotification.setPosition(point.x + tileWidth, point.y + tileHeight);

        spTile.setName("Barro");
        imagesMap.put(spTile.getName(), tileMud);

        playfield.addSprite(spTile);
        playfield.addSprite(spNotification);

        spritesMap.put(spTile.getName(), spNotification);

        spTile = new SpriteUtopia(tileIron);
        spNotification = new Sprite(imagesMap.get(FILE_PREFIX + 0));

        //Elemento 8 x=220 y=25
        spTile.setPosition(220, 25);
        tileWidth = (spTile.getWidth()) / 2;
        tileHeight = (spTile.getHeight()) / 2;
        point = spTile.getPosition();
        spNotification.setPosition(point.x + tileWidth, point.y + tileHeight);

        spTile.setName("Hierro");
        imagesMap.put(spTile.getName(), tileIron);

        playfield.addSprite(spTile);
        playfield.addSprite(spNotification);

        spritesMap.put(spTile.getName(), spNotification);

        spTile = new SpriteUtopia(tileFood);
        spNotification = new Sprite(imagesMap.get(FILE_PREFIX + 0));

        spTile.setPosition(398, 5);
        tileWidth = (spTile.getWidth()) / 2;
        tileHeight = (spTile.getHeight()) / 2;
        point = spTile.getPosition();
        spNotification.setPosition(point.x + tileWidth, point.y + tileHeight);

        spTile.setName("Alimento");
        imagesMap.put(spTile.getName(), tileFood);

        playfield.addSprite(spTile);
        playfield.addSprite(spNotification);

        spritesMap.put(spTile.getName(), spNotification);

        MapEventProcessor processor = new MapEventProcessor(playfield);
        processor.setLevelContainer(levelContainer);

        playfield.addMouseListener(processor);

        processor.setRestClient(client);
        processor.addMapEventListener(this);

        // Displays the playfield

        mapPanel.getMap().add(playfield);
        mapPanel.setSize(playfield.getSize());
        mapPanel.getPnlIndicators().add(indicatorsMarquee);

        System.out.println("Starting");
        imagesMap.put("Poblacion", tilePeople);

        add(mapPanel);
        worker = new Thread(new DataUpdater(this, 1000));
        worker.setDaemon(true);
        worker.start();
    }

    @Override
    public void start() {
        // Activate the playfield
        playfield.setPause(false);
        mapPanel.setVisible(true);
        stopped = false;
        System.out.println("Starting-Resumming");
    }

    @Override
    public void stop() {
        if (playfield != null) {
            playfield.stop();
        }
        mapPanel.setVisible(false);
        System.out.println("Stopping");
        stopped = true;

    }

    @Override
    public synchronized void changeElements(ElementEvent event) {


        if (EnumDataType.FACTOR == event.getDataType()) {

            System.out.println("updating factors");
            container.updateOrCreateFactorIndicator(event.getResults(), mapPanel.getTbFactors());
            levelContainer.updateElements(event.getResults(), event.getDataType());

        } else if (EnumDataType.MATERIAL == event.getDataType()) {


            System.out.println("updating materials");
            container.updateOrCreateMaterialIndicator(event.getResults(), mapPanel.getTbMaterials());
            levelContainer.updateElements(event.getResults(), event.getDataType());


        } else if (EnumDataType.POPULATION == event.getDataType()) {
            System.out.println("updating population");

            Object el = event.getResults().get(0);
            container.updateOrCreatePopulationIndicator((Population) el, indicatorsMarquee);

        } else if (EnumDataType.NOTIFICATIONS == event.getDataType()) {

            System.out.println("updating notification");
            container.updateOrCreateMessageEvents(event.getResults(), indicatorsMarquee);

        }



    }

    public synchronized void notifyMsg(MessageEvent event) {
    }

    public synchronized void eventHappening() {
        System.out.println("eventHappening");
//        factorsMarquee.resumeScrolling();
//        materialsMarquee.resumeScrolling();
    }

    public synchronized void updatingProcess() {
        System.out.println("Updating process ....");
        client.fetchAllElements();
    }

    public boolean isStopped() {
        return stopped;
    }

    /**
     * @param stopped the stopped to set
     */
    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}

package co.edu.udea.ludens.domain;

public class GraphicIncrementable implements Updateable {

    private Long id;
    private int level;
    private int coorX;
    private int coorY;
    private String urlImage;

    public void setId(Long id) {
        this.id = id;
    }

    @Override()
    public Long getId() {

        return (this.id);
    }

    public GraphicIncrementable() {
        super();
        this.urlImage = "";
    }

    public GraphicIncrementable(int level, int coorX, int coorY, String urlImage) {
        super();
        this.level = level;
        this.coorX = coorX;
        this.coorY = coorY;
        this.urlImage = urlImage;
    }

    public int getLevel() {

        return (this.level);
    }

    public int getCoorX() {

        return (this.coorX);
    }

    public int getCoorY() {

        return (this.coorY);
    }

    public String getUrlImage() {

        return (this.urlImage);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCoorX(int coorX) {
        this.coorX = coorX;
    }

    public void setCoorY(int coorY) {
        this.coorY = coorY;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override()
    public void updateWith(Object o) {
        GraphicIncrementable graphic = (GraphicIncrementable) o;

        this.coorX = graphic.coorX;
        this.level = graphic.level;
        this.urlImage = graphic.urlImage;
        this.coorY = graphic.coorY;
    }
}

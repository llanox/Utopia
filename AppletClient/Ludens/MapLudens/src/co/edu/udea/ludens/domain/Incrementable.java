package co.edu.udea.ludens.domain;

import co.edu.udea.ludens.applet.util.LudensConstants;
import co.edu.udea.ludens.enums.EnumElementType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Incrementable implements Serializable, Updateable {

    private static final long serialVersionUID = 1739542395L;
    private Long id;
    private long initialUpgradingTime;
    private int initialValue;
    private int levelIncrementDelayRate;
    private int productionIncrementRate;
    private EnumElementType type;
    private Game game;
    private List<IncrementableConstraint> constraints = new ArrayList<IncrementableConstraint>();
    private String description;
    private String imageUrl = LudensConstants.NO_IMAGE_FILE;
    private String name;

    public Incrementable() {
    }

    @Override()
    public Long getId() {

        return (this.id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getInitialUpgradingTime() {

        return (this.initialUpgradingTime);
    }

    public void setInitialUpgradingTime(long initialUpgradingTime) {
        this.initialUpgradingTime = initialUpgradingTime;
    }

    public int getInitialValue() {

        return (this.initialValue);
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    public int getLevelIncrementDelayRate() {

        return (this.levelIncrementDelayRate);
    }

    public void setLevelIncrementDelayRate(int levelIncrementDelayRate) {
        this.levelIncrementDelayRate = levelIncrementDelayRate;
    }

    public int getProductionIncrementRate() {

        return (this.productionIncrementRate);
    }

    public void setProductionIncrementRate(int productionIncrementRate) {
        this.productionIncrementRate = productionIncrementRate;
    }

    public EnumElementType getType() {

        return (this.type);
    }

    public void setType(EnumElementType type) {
        this.type = type;
    }

    public Game getGame() {

        return (this.game);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<IncrementableConstraint> getConstraints() {

        return (this.constraints);
    }

    public void setConstraints(List<IncrementableConstraint> constraints) {
        this.constraints = constraints;
    }

    public String getDescription() {

        return (this.description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {

        return (this.imageUrl);
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {

        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override()
    public void updateWith(Object o) {
        Incrementable inc = (Incrementable) o;

        this.name = inc.name;
        this.description = inc.description;
        this.imageUrl = inc.imageUrl;
        this.type = inc.type;
        this.levelIncrementDelayRate = inc.levelIncrementDelayRate;
        this.productionIncrementRate = inc.productionIncrementRate;
        this.initialUpgradingTime = inc.initialUpgradingTime;
        this.initialValue = inc.initialValue;
        this.constraints = inc.constraints;
        this.game = inc.game;
    }
}
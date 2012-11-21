package co.edu.udea.ludens.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Element implements Serializable, Updateable {

    private static final long serialVersionUID = 1739544495L;
    private Long id;
    private int calculatedValue;
    private int level;
    private int progressLevel;
    private int quantity;
    private long actualUpgradingTime;
    private Incrementable incrementable;
    private List<Integer> changeEvents = new ArrayList<Integer>();
    private List<IncrementableConstraint> levelConstraints;
    private List<IncrementableConstraint> levelIncrements;
    private Player player;

    public Element() {
    }

    public int getLevelIncrementDelayRate() {

        return (this.incrementable.getLevelIncrementDelayRate());
    }

    public void setLevelIncrementDelayRate(int levelIncrementDelayRate) {
        this.incrementable.setLevelIncrementDelayRate(levelIncrementDelayRate);
    }

    public int getProductionIncrementRate() {

        return (this.incrementable.getProductionIncrementRate());
    }

    public void setProductionIncrementRate(int productionIncrementRate) {
        this.incrementable.setProductionIncrementRate(productionIncrementRate);
    }

    @Override()
    public Long getId() {

        return (this.id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCalculatedValue() {

        return (this.calculatedValue);
    }

    public void setCalculatedValue(int calculatedValue) {
        this.calculatedValue = calculatedValue;
    }

    public int getLevel() {

        return (this.level);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getProgressLevel() {

        return (this.progressLevel);
    }

    public void setProgressLevel(int progressLevel) {
        this.progressLevel = progressLevel;
    }

    public int getQuantity() {

        return (this.quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getActualUpgradingTime() {

        return (this.actualUpgradingTime);
    }

    public void setActualUpgradingTime(long actualUpgradingTime) {
        this.actualUpgradingTime = actualUpgradingTime;
    }

    public Incrementable getIncrementable() {

        return (this.incrementable);
    }

    public void setIncrementable(Incrementable incrementable) {
        this.incrementable = incrementable;
    }

    public List<Integer> getChangeEvents() {

        return (this.changeEvents);
    }

    public void setChangeEvents(List<Integer> changeEvents) {
        this.changeEvents = changeEvents;
    }

    public List<IncrementableConstraint> getLevelConstraints() {

        return (this.levelConstraints);
    }

    public void setLevelConstraints(
            List<IncrementableConstraint> levelConstraints) {
        this.levelConstraints = levelConstraints;
    }

    public List<IncrementableConstraint> getLevelIncrements() {

        return (this.levelIncrements);
    }

    public void setLevelIncrements(List<IncrementableConstraint> levelIncrements) {
        this.levelIncrements = levelIncrements;
    }

    public Player getPlayer() {

        return (this.player);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public long getInitialUpgradingTime() {

        return (this.incrementable.getInitialValue());
    }

    public void setInitialUpgradingTime(long initialUpgradingTime) {
        this.incrementable.setInitialUpgradingTime(initialUpgradingTime);
    }

    @Override()
    public void updateWith(Object o) {
        Element newElement = (Element) o;

        this.calculatedValue = newElement.calculatedValue;
        // this.initialValue = newElement.initialValue;
        this.progressLevel = newElement.progressLevel;
        this.quantity = newElement.quantity;
        this.changeEvents = newElement.changeEvents;
        this.incrementable = newElement.incrementable;
        this.actualUpgradingTime = newElement.actualUpgradingTime;
    }
}
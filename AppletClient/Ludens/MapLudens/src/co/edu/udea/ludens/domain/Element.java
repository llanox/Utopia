package co.edu.udea.ludens.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Element implements Updateable {

    private Long id;
    private int calculatedValue;
    private int initialValue;
    private int level;
    private int quantity;
    private List<Integer> changeEvents = new ArrayList<Integer>();
    private Incrementable incrementable;
    private HashMap<String, List<LevelConstraint>> levelIncrements = new HashMap<String, List<LevelConstraint>>();
    private HashMap<String, List<LevelConstraint>> levelConstraints = new HashMap<String, List<LevelConstraint>>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLevelIncrements(HashMap<String, List<LevelConstraint>> levelIncrements) {

        this.levelIncrements = levelIncrements;
    }

    public HashMap<String, List<LevelConstraint>> getLevelIncrements() {

        return levelIncrements;
    }

    public void setLevelconstraints(HashMap<String, List<LevelConstraint>> levelConstraints) {

        this.levelConstraints = new HashMap<String, List<LevelConstraint>>(levelConstraints);
    }

    public HashMap<String, List<LevelConstraint>> getLevelConstraints() {

        return levelConstraints;
    }

    public Element() {

        incrementable = new Incrementable();
    }

    public void copy(Element that) {

        setLevelIncrements(new HashMap<String, List<LevelConstraint>>(that.getLevelIncrements()));
        setLevelconstraints(new HashMap<String, List<LevelConstraint>>(that.getLevelConstraints()));

    }

    public int getLevel() {
        return level;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLevelIncrementDelayRate() {

        return incrementable.getLevelIncrementDelayRate();
    }

    public int getProductionIncrementRate() {

        return incrementable.getProductionIncrementRate();

    }

    public int getInitialValue() {
        return initialValue;
    }

    public int getInitialUpgradingTime() {

        return incrementable.getInitialUpgradingTime();
    }

    public int getCalculatedValue() {
        return calculatedValue;
    }

    public int getActualUpgradingTime() {

        if (incrementable == null) {
            return -1;
        }

        return incrementable.getActualUpgradingTime();
    }

    public List<Integer> getChangeEvents() {
        return changeEvents;
    }

    public Incrementable getIncrementable() {
        return incrementable;
    }

    public void setLevel(int level) {

        this.level = level;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public void setLevelIncrementDelayRate(int levelIncrementDelayRate) {

        this.incrementable.setLevelIncrementDelayRate(levelIncrementDelayRate);
    }

    public void setProductionIncrementRate(int productionIncrementRate) {

        this.incrementable.setProductionIncrementRate(productionIncrementRate);
    }

    public void setInitialValue(int initialValue) {

        this.initialValue = initialValue;
    }

    public void setInitialUpgradingTime(int initialUpgradingTime) {

        this.incrementable.setInitialUpgradingTime(initialUpgradingTime);
    }

    public void setCalculatedValue(int calculatedValue) {

        this.calculatedValue = calculatedValue;
    }

    public void setActualUpgradingTime(int actualUpgradingTime) {

        this.incrementable.setActualUpgradingTime(actualUpgradingTime);
    }

    public void setChangeEvents(List<Integer> changeEvents) {

        this.changeEvents = changeEvents;
    }

    public void setIncrementable(Incrementable incrementable) {

        this.incrementable = incrementable;
    }

    public void setLevelConstraints(HashMap<String, List<LevelConstraint>> levelConstraints) {

        this.levelConstraints = new HashMap<String, List<LevelConstraint>>(levelConstraints);
    }

    @Override
    public void updateWith(Object o) {
        Element newElement = (Element) o;
        this.calculatedValue = newElement.calculatedValue;
        this.initialValue = newElement.initialValue;
        this.level = newElement.level;
        this.quantity = newElement.quantity;
        this.changeEvents = newElement.changeEvents;
        this.incrementable = newElement.incrementable;

    }
}

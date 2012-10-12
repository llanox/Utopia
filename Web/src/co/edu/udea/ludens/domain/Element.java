package co.edu.udea.ludens.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "elements")
public class Element implements Serializable, Updateable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "calculated_value")
    private int calculatedValue;
    @Column(name = "progress_level")
    private int progressLevel;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "actual_upgrading_time")
    private long actualUpgradingTime;
    @ManyToOne
    private Incrementable incrementable;
    @Transient
    private List<Integer> changeEvents = new ArrayList();
    @OneToMany(mappedBy = "constrainedElement")
    private List<IncrementableConstraint> levelConstraints;
    @OneToMany(mappedBy = "constrainedElement")
    private List<IncrementableConstraint> levelIncrements;
    @ManyToOne
    private Player player;

    public Element() {
    }

    @Override
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

    public void setLevelConstraints(List<IncrementableConstraint> levelConstraints) {
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

    @Override
    public void updateWith(Object o) {
        Element newElement = (Element) o;

        this.calculatedValue = newElement.calculatedValue;
        //this.initialValue = newElement.initialValue;
        this.progressLevel = newElement.progressLevel;
        this.quantity = newElement.quantity;
        this.changeEvents = newElement.changeEvents;
        this.incrementable = newElement.incrementable;
        this.actualUpgradingTime = newElement.actualUpgradingTime;
    }
}
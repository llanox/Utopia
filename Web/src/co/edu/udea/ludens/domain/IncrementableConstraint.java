package co.edu.udea.ludens.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "incrementables_constraints")
public class IncrementableConstraint implements Serializable, Updateable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "increment_rate")
    private int incrementRate;
    @Column(name = "initial_value")
    private int initialValue;
    @ManyToOne
    private Element constrainedElement;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Incrementable restrictedIncrementable;
    @Column(name = "constrained_level")
    private Integer constrainedLevel;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "element_name")
    private String elementName;

    public IncrementableConstraint() {
    }

    @Override
    public Long getId() {

        return (this.id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getConstrainedLevel() {

        return (this.constrainedLevel);
    }

    public void setConstrainedLevel(Integer constrainedLevel) {
        this.constrainedLevel = constrainedLevel;
    }

    public Element getConstrainedElement() {

        return (this.constrainedElement);
    }

    public void setConstrainedElement(Element constrainedElement) {
        this.constrainedElement = constrainedElement;
    }

    public Incrementable getRestrictedIncrementable() {

        return (this.restrictedIncrementable);
    }

    public void setRestrictedIncrementable(Incrementable restrictedIncrementable) {
        this.restrictedIncrementable = restrictedIncrementable;
    }

    public int getInitialValue() {

        return (this.initialValue);
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    public int getIncrementRate() {

        return (this.incrementRate);
    }

    public void setIncrementRate(int incrementRate) {
        this.incrementRate = incrementRate;
    }

    public Integer getQuantity() {

        return (this.quantity);
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getElementName() {

        return (this.elementName);
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public void updateWith(Object o) {
        IncrementableConstraint constraint = (IncrementableConstraint) o;

        this.quantity = constraint.quantity;
        this.restrictedIncrementable = constraint.restrictedIncrementable;
    }
}
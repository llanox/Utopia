package co.edu.udea.ludens.domain;

public class LevelConstraint implements Updateable {

    private Long id;
    private Integer level;
    private String elementName;
    private Integer quantity;

    public void setId(Long id) {
        this.id = id;
    }

    @Override()
    public Long getId() {

        return (this.id);
    }

    public void setLevel(Integer level) {

        this.level = level;
    }

    public Integer getLevel() {

        return (this.level);
    }

    public LevelConstraint() {
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementName() {

        return (this.elementName);
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {

        return (this.quantity);
    }

    @Override()
    public void updateWith(Object o) {
        LevelConstraint constraint = (LevelConstraint) o;

        this.elementName = constraint.elementName;
        this.level = constraint.level;
        this.quantity = constraint.quantity;
    }
}
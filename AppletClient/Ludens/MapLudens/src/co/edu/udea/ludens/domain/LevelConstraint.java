package co.edu.udea.ludens.domain;

public class LevelConstraint implements Updateable {

    private Long id;
    private Integer level;
    private String elementName;
    private Integer quantity;

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * @generated
     */
    public void setLevel(Integer level) {

        this.level = level;
    }

    /**
     * @generated
     */
    public Integer getLevel() {

        return this.level;
    }

    /**
     * @generated
     */
    public LevelConstraint() {
    }

    public void setElementName(String elementName) {

        this.elementName = elementName;
    }

    public String getElementName() {

        return elementName;
    }

    public void setQuantity(Integer quantity) {

        this.quantity = quantity;
    }

    public Integer getQuantity() {

        return quantity;
    }

    @Override
    public void updateWith(Object o) {
        LevelConstraint constraint = (LevelConstraint) o;
        this.elementName = constraint.elementName;
        this.level = constraint.level;
        this.quantity = constraint.quantity;



    }
}

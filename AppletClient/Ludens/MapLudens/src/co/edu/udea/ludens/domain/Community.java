package co.edu.udea.ludens.domain;

import java.util.HashMap;

public class Community implements Updateable {

    private Long id;
    private String name;
    private Integer length;
    private Integer width;
    private Integer corX;
    private Integer corY;
    private HashMap<String, Element> developmentFactors = new HashMap<String, Element>();
    private HashMap<String, Element> materials = new HashMap<String, Element>();
    private Population population;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return this.name;
    }

    public void setLength(Integer length) {

        this.length = length;
    }

    public Integer getLength() {

        return this.length;
    }

    public void setWidth(Integer width) {

        this.width = width;
    }

    public Integer getWidth() {

        return this.width;
    }

    public void setCorX(Integer corX) {

        this.corX = corX;
    }

    public Integer getCorX() {

        return this.corX;
    }

    public void setCorY(Integer corY) {

        this.corY = corY;
    }

    public Integer getCorY() {

        return this.corY;
    }

    public void setDevelopmentFactors(HashMap<String, Element> developmentFactors) {
        this.developmentFactors = developmentFactors;
    }

    public HashMap<String, Element> getDevelopmentFactors() {
        return developmentFactors;
    }

    public void setMaterials(HashMap<String, Element> materials) {

        this.materials = new HashMap<String, Element>(materials);
    }

    public HashMap<String, Element> getMaterials() {
        return materials;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public Population getPopulation() {
        return population;
    }

    public Community() {
    }

    @Override
    public void updateWith(Object o) {
        Community newCom = (Community) o;

        this.name = newCom.name;
        this.length = newCom.length;
        this.width = newCom.width;
        this.corX = newCom.corX;
        this.corY = newCom.corY;
        this.developmentFactors = newCom.developmentFactors;
        this.materials = newCom.materials;
        this.population = newCom.population;

    }
}

package co.edu.udea.ludens.domain;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable, Updateable {

    private static final long serialVersionUID = 1739543883L;
    private long id;
    private Element population;
    private User user;
    private Game game;
    private long startTime;
    private boolean producing;
    private List<Element> elements;
    private List<MessageEvent> events;
    private List<Element> developmentFactors;
    private List<Element> materials;

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Long getId() {

        return (this.id);
    }

    public void setPopulation(Element population) {
        this.population = population;
    }

    public Element getPopulation() {

        return (this.population);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {

        return (this.user);
    }

    public Player() {
    }

    public List<Element> getElements() {

        return (this.elements);
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public List<Element> getDevelopmentFactors() {

        return (this.developmentFactors);
    }

    public void setDevelopmentFactors(List<Element> developmentFactors) {
        this.developmentFactors = developmentFactors;
    }

    public List<MessageEvent> getEvents() {

        return (this.events);
    }

    public List<Element> getMaterials() {

        return (this.materials);
    }

    public void setMaterials(List<Element> materials) {
        this.materials = materials;
    }

    public void setEvents(List<MessageEvent> events) {
        this.events = events;
    }

    public void setProducing(boolean producing) {
        this.producing = producing;
    }

    public boolean isProducing() {

        return (this.producing);
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getStartTime() {

        return (this.startTime);
    }

    @Override
    public void updateWith(Object o) {
        Player player = (Player) o;

        this.user = player.user;
        this.startTime = player.startTime;
        this.producing = player.producing;
        this.events = player.events;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {

        return (this.game);
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
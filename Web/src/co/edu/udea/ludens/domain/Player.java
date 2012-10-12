package co.edu.udea.ludens.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.Unique;
import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player implements Serializable, Updateable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Unique
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Game game;
    @Column(name = "start_time")
    private long startTime;
    @Column(name = "producing")
    private boolean producing;
    @Column(name = "elements")
    @OneToMany(mappedBy = "player")
    private List<Element> elements;
    @OneToMany
    @OrderColumn(name = "order")
    @JoinColumn(name = "player_id", nullable = false)
    private List<MessageEvent> events = new ArrayList();

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Long getId() {

        return (this.id);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {

        return (this.user);
    }

    public Player() {
  
    }

    /**
     * @return the elements
     */
    public List<Element> getElements() {
        return elements;
    }

    /**
     * @param elements the elements to set
     */
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public List<MessageEvent> getEvents() {

        return (this.events);
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
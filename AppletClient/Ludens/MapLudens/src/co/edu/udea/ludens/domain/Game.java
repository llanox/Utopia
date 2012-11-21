package co.edu.udea.ludens.domain;

import co.edu.udea.ludens.enums.EnumGameStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game implements Serializable, Updateable {

    private static final long serialVersionUID = 1739544762L;
    private Long id;
    private long duration;
    private long lowerThreshold;
    private long productionTime;
    private long upperThreshold;
    private Date endTime;
    private Date startTime;
    private EnumGameStatus status = EnumGameStatus.NO_STARTED;
    private List<Incrementable> defaultIncrementables = new ArrayList<Incrementable>();
    private List<Player> players = new ArrayList<Player>();
    private List<UnexpectedEvent> unexpectedEvents = new ArrayList<UnexpectedEvent>();
    private String name;

    public Game() {
    }

    @Override()
    public Long getId() {

        return (this.id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDuration() {

        return (this.duration);
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getLowerThreshold() {

        return (this.lowerThreshold);
    }

    public void setLowerThreshold(long lowerThreshold) {
        this.lowerThreshold = lowerThreshold;
    }

    public long getProductionTime() {

        return (this.productionTime);
    }

    public void setProductionTime(long productionTime) {
        this.productionTime = productionTime;
    }

    public long getUpperThreshold() {

        return (this.upperThreshold);
    }

    public void setUpperThreshold(long upperThreshold) {
        this.upperThreshold = upperThreshold;
    }

    public Date getEndTime() {

        return (this.endTime);
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {

        return (this.startTime);
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public EnumGameStatus getStatus() {

        return (this.status);
    }

    public void setStatus(EnumGameStatus status) {
        this.status = status;
    }

    public List<Incrementable> getDefaultIncrementables() {

        return (this.defaultIncrementables);
    }

    public void setDefaultIncrementables(
            List<Incrementable> defaultIncrementables) {
        this.defaultIncrementables = defaultIncrementables;
    }

    public List<Player> getPlayers() {

        return (this.players);
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<UnexpectedEvent> getUnexpectedEvents() {

        return (this.unexpectedEvents);
    }

    public void setUnexpectedEvents(List<UnexpectedEvent> unexpectedEvents) {
        this.unexpectedEvents = unexpectedEvents;
    }

    public String getName() {

        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override()
    public void updateWith(Object o) {
        Game game = (Game) o;

        this.name = game.name;
        this.duration = game.duration;
        this.startTime = game.startTime;
        this.endTime = game.endTime;
        this.productionTime = game.productionTime;
        this.lowerThreshold = game.lowerThreshold;
        this.upperThreshold = game.upperThreshold;
        this.status = game.status;
        this.players = game.players;
        this.unexpectedEvents = game.unexpectedEvents;
        this.defaultIncrementables = game.defaultIncrementables;
    }

    @Override
    public String toString() {

        return ("id " + id + " name " + name + " duration " + duration
                + " startTime " + startTime + " endTime " + endTime
                + " productionTime " + productionTime + " lowerThreshold "
                + lowerThreshold + " upperThreshold " + upperThreshold
                + " status " + status);
    }
}
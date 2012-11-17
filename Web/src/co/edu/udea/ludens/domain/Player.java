package co.edu.udea.ludens.domain;

import java.io.Serializable;
import java.util.List;
import javax.jdo.annotations.Unique;
import javax.persistence.*;

@Entity()
@Table(name = "players")
public class Player implements Serializable, Updateable {

	@Transient()
	private static final long serialVersionUID = 1739543883L;

	@Id()
	@GeneratedValue()
	@Column(name = "id")
	private long id;
	@Column(name = "population")
	private Element population;
	@Unique
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	private User user;
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
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
	private List<MessageEvent> events;
    @Column(name = "development_factors")
    @OneToMany(mappedBy = "player")
    private List<Element> developmentFactors;
    @Column(name = "materials")
    @OneToMany(mappedBy = "player")
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
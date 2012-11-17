package co.edu.udea.ludens.domain;

import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.util.LudensConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity()
@Table(name = "incrementables")
public class Incrementable implements Serializable, Updateable {

	@Transient()
	private static final long serialVersionUID = 1739542395L;

	@Id()
	@GeneratedValue()
	private Long id;
	@Column(name = "initial_upgrading_time")
	private long initialUpgradingTime;
	@Column(name = "initial_value")
	private int initialValue;
	@Column(name = "level_increment_delay_rate")
	private int levelIncrementDelayRate;
	@Column(name = "production_increment_rate")
	private int productionIncrementRate;
	@Column(name = "type")
	private EnumElementType type;
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	private Game game;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "restrictedIncrementable", cascade = { CascadeType.MERGE })
	@Column(name = "constraints")
	private List<IncrementableConstraint> constraints = new ArrayList<IncrementableConstraint>();
	@Column(name = "description")
	private String description;
	@Column(name = "image_url")
	private String imageUrl = LudensConstants.NO_IMAGE_FILE;
	@Column(name = "name")
	private String name;

	public Incrementable() {
	}

	@Override
	public Long getId() {

		return (this.id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getInitialUpgradingTime() {

		return (this.initialUpgradingTime);
	}

	public void setInitialUpgradingTime(long initialUpgradingTime) {
		this.initialUpgradingTime = initialUpgradingTime;
	}

	public int getInitialValue() {

		return (this.initialValue);
	}

	public void setInitialValue(int initialValue) {
		this.initialValue = initialValue;
	}

	public int getLevelIncrementDelayRate() {

		return (this.levelIncrementDelayRate);
	}

	public void setLevelIncrementDelayRate(int levelIncrementDelayRate) {
		this.levelIncrementDelayRate = levelIncrementDelayRate;
	}

	public int getProductionIncrementRate() {

		return (this.productionIncrementRate);
	}

	public void setProductionIncrementRate(int productionIncrementRate) {
		this.productionIncrementRate = productionIncrementRate;
	}

	public EnumElementType getType() {

		return (this.type);
	}

	public void setType(EnumElementType type) {
		this.type = type;
	}

	public Game getGame() {

		return (this.game);
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<IncrementableConstraint> getConstraints() {

		return (this.constraints);
	}

	public void setConstraints(List<IncrementableConstraint> constraints) {
		this.constraints = constraints;
	}

	public String getDescription() {

		return (this.description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {

		return (this.imageUrl);
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {

		return (this.name);
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void updateWith(Object o) {
		Incrementable inc = (Incrementable) o;

		this.name = inc.name;
		this.description = inc.description;
		this.imageUrl = inc.imageUrl;
		this.type = inc.type;
		this.levelIncrementDelayRate = inc.levelIncrementDelayRate;
		this.productionIncrementRate = inc.productionIncrementRate;
		this.initialUpgradingTime = inc.initialUpgradingTime;
		this.initialValue = inc.initialValue;
		this.constraints = inc.constraints;
		this.game = inc.game;
	}
}
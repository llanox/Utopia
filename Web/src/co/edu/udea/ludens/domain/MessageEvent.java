package co.edu.udea.ludens.domain;

import co.edu.udea.ludens.enums.EnumEventType;
import co.edu.udea.ludens.enums.EnumMsgType;
import co.edu.udea.ludens.enums.EnumMsgs;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity()
@Table(name = "messages_events")
public class MessageEvent implements Comparable<MessageEvent>, Serializable,
		Updateable {

	@Transient()
	private static final long serialVersionUID = 1739542986L;

	@Id()
	@GeneratedValue()
	@Column(name = "id")
	private Long id;
	@Column(name = "msg")
	private String msg;
	@Column(name = "msg_type")
	private EnumMsgType msgType;
	@Column(name = "event_type")
	private EnumEventType eventType;
	@Column(name = "affected_element_1")
	private String affectedElement1;
	@Column(name = "affected_element_2")
	private String affectedElement2;
	@Column(name = "element_1_value")
	private String element1Value;
	@Column(name = "element_2_value")
	private String element2Value;
	@Column(name = "executor_name")
	private String executorName;
	@Column(name = "receiver_name")
	private String receiverName;
	@Transient
	private Object source;
	@Column(name = "game_name")
	private String gameName;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "exact_date")
	private Date exactDate;
	@Column(name = "game_elapsed_time")
	private long gameElapsedTime;
	@ManyToOne
	@JoinColumn(name = "player_id", insertable = false, updatable = false, nullable = false)
	private Player affectedPlayer;

	public MessageEvent() {
	}

	public MessageEvent(Object source) {
		this.source = source;
	}

	public MessageEvent(Object source, EnumMsgType msgType, String msg, long id) {
		this.source = source;
		this.msg = msg;
		this.msgType = msgType;
		this.id = id;
	}

	public MessageEvent(Object source, EnumMsgs enumMsg, long id) {
		this.source = source;
		this.msg = enumMsg.getMsg();
		this.msgType = enumMsg.getMsgType();
		this.id = id;
	}

	public MessageEvent(Object source, long id, EnumMsgs enumMsg,
			Object... parameters) {
		this.source = source;
		this.msg = String.format(enumMsg.getMsg(), parameters);
		this.msgType = enumMsg.getMsgType();
		this.id = id;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {

		return (this.msg);
	}

	public void setMsgType(EnumMsgType msgType) {
		this.msgType = msgType;
	}

	public EnumMsgType getMsgType() {

		return (this.msgType);
	}

	@Override
	public Long getId() {

		return (this.id);
	}

	public void setAffectedPlayer(Player affectedPlayer) {
		this.affectedPlayer = affectedPlayer;
	}

	public Player getAffectedPlayer() {

		return (this.affectedPlayer);
	}

	public void setEventType(EnumEventType eventType) {
		this.eventType = eventType;
	}

	public EnumEventType getEventType() {

		return (this.eventType);
	}

	public String getAffectedElement1() {

		return (this.affectedElement1);
	}

	public void setAffectedElement1(String affectedElement1) {
		this.affectedElement1 = affectedElement1;
	}

	public String getAffectedElement2() {

		return (this.affectedElement2);
	}

	public void setAffectedElement2(String affectedElement2) {
		this.affectedElement2 = affectedElement2;
	}

	public String getElement1Value() {

		return (this.element1Value);
	}

	public void setElement1Value(String element1Value) {
		this.element1Value = element1Value;
	}

	public String getElement2Value() {

		return (this.element2Value);
	}

	public void setElement2Value(String element2Value) {
		this.element2Value = element2Value;
	}

	public String getExecutorName() {

		return (this.executorName);
	}

	public void setExecutorName(String executorName) {
		this.executorName = executorName;
	}

	public String getReceiverName() {

		return (this.receiverName);
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Date getExactDate() {

		return (this.exactDate);
	}

	public void setExactDate(Date exactDate) {
		this.exactDate = exactDate;
	}

	public long getGameElapsedTime() {

		return (this.gameElapsedTime);
	}

	public void setGameElapsedTime(long gameElapsedTime) {
		this.gameElapsedTime = gameElapsedTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int compareTo(MessageEvent o) {
		long result = this.id - o.getId();

		return (int) result;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameName() {

		return (this.gameName);
	}

	@Override
	public void updateWith(Object o) {
		MessageEvent event = (MessageEvent) o;

		this.msg = event.msg;
		this.msgType = event.msgType;
		this.eventType = event.eventType;
		this.affectedElement1 = event.affectedElement1;
		this.affectedElement2 = event.affectedElement2;
		this.element1Value = event.element1Value;
		this.element2Value = event.element2Value;
		this.executorName = event.executorName;
		this.receiverName = event.receiverName;
		this.gameName = event.gameName;
		this.exactDate = event.exactDate;
		this.gameElapsedTime = event.gameElapsedTime;
		this.affectedPlayer = event.affectedPlayer;
	}

	public Object getSource() {

		return (this.source);
	}

	public void setSource(Object source) {
		this.source = source;
	}
}
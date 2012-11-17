package co.edu.udea.ludens.domain;

import co.edu.udea.ludens.enums.EnumEventType;
import co.edu.udea.ludens.enums.EnumMsgType;
import co.edu.udea.ludens.enums.EnumMsgs;
import java.io.Serializable;
import java.util.Date;

public class MessageEvent implements Comparable<MessageEvent>, Serializable,
        Updateable {

    private static final long serialVersionUID = 1739542986L;
    private Long id;
    private String msg;
    private EnumMsgType msgType;
    private EnumEventType eventType;
    private String affectedElement1;
    private String affectedElement2;
    private String element1Value;
    private String element2Value;
    private String executorName;
    private String receiverName;
    private Object source;
    private String gameName;
    private Date exactDate;
    private long gameElapsedTime;
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
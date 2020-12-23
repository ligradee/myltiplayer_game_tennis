package network.messages;

public abstract class AbstractMessage {
    protected boolean broadcast = false;

    public AbstractMessage() {
    }

    public boolean isBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean value) {
        this.broadcast = value;
    }

    public abstract String getContent();

    public abstract void setContent(String content);

    public String toString() {
        return this.getClass().getName()
                + (this.broadcast ? " (broadcast)" : "") + ": "
                + this.getContent();
    }

}

package network.messages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


public class MessageFactory {

    protected static final String HEADER_CHARACTER = ":";
    private static MessageFactory instance = null;

    @SuppressWarnings("rawtypes")
    protected HashMap<String, Class> messageTypeMap;

    @SuppressWarnings("rawtypes")
    private MessageFactory() {
        messageTypeMap = new HashMap<String, Class>();
        //messageTypeMap.put("PING", PingMessage.class);
        //messageTypeMap.put("PONG", PongMessage.class);
    }

    public static MessageFactory getInstance() {
        if (instance == null) {
            instance = new MessageFactory();
        }
        return instance;
    }

    public AbstractMessage deserialize(String message) {
        int headerIndex = message.indexOf(HEADER_CHARACTER);
        if (headerIndex == -1)
            return null;

        String[] messageParts = message.split(HEADER_CHARACTER);
        if (messageParts.length < 2)
            return null;

        String messageType = messageParts[0];
        if (messageTypeMap.containsKey(messageType)) {
            try {
                AbstractMessage instance = (AbstractMessage) messageTypeMap
                        .get(messageType).newInstance();
                if ("1".equals(messageParts[1])) {
                    instance.setBroadcast(true);
                }
                instance.setContent(messageParts[2]);
                return instance;
            } catch (Exception e) {
                System.out.println("Cannot create an instance of : "
                        + messageType);
            }
        }

        return null;
    }


    public String serialize(AbstractMessage message) {
        @SuppressWarnings("rawtypes")
        Iterator<Entry<String, Class>> iterator = messageTypeMap.entrySet()
                .iterator();
        @SuppressWarnings("rawtypes")
        Entry<String, Class> entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            if (entry.getValue().equals(message.getClass())) {
                return entry.getKey() + HEADER_CHARACTER
                        + (message.isBroadcast() ? '1' : '0')
                        + HEADER_CHARACTER + message.getContent();
            }
        }

        return null;
    }
}
package be.ordina.workshop.kafkaexercises.model;


import java.util.List;

public class WriteRequest {

    private List<String> messages;

    private String topic;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "WriteRequest{" +
                "messages=" + messages +
                ", topic='" + topic + '\'' +
                '}';
    }
}

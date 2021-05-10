package guru.springframework.springmvcrest.websocket;

public class Notification {

    public String text;

    public Notification() {
    }

    public Notification(String text) {
        this.text = text;
    }

    public String getText(){
        return this.text;
    }
}

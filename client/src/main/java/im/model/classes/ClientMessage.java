package im.model.classes;

public class ClientMessage {
    private long id;
    private String author;
    private String content;

    public ClientMessage(String a, String c) {
        author = a;
        content = c;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public long getId() {
        return id;
    }

    public String getStringId() {
        return String.format("%d",id);
    }
}

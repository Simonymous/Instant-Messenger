package im.model;

public class ClientMessage {
    private long id;
    private String author;
    private String content;

    public ClientMessage(long id, String a, String c) {
        this.id = id;
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
        return String.format("%d", id);
    }
}

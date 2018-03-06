package im.model.classes;

public class ClientMessage {
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
}

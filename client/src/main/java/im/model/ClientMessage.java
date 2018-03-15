package im.model;

/**
 * Class that represents a local chatMessage
 */
public class ClientMessage {
    private long id;
    private String author;
    private String content;

    /**
     * constructor to create a new message with given attributes
     *
     * @param id the id of the chat
     * @param a the author of the chat
     * @param c the content of the chat
     */
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
        return Long.toString(id);
    }
}

package im.model.interfaces;

import java.util.List;

/**
 * interface for User query response
 */
public interface UserQueryResponse {

    void setIds(List<String> ids);

    List<String> getIds();

}

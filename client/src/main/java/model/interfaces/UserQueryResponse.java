package model.interfaces;

import java.util.List;

/**
 * interface for User query response
 */
public interface UserQueryResponse {

    public void setIds(List<String> ids);

    public List<String> getIds();

}

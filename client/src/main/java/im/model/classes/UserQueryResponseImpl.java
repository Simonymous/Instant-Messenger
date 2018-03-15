package im.model.classes;

import im.model.interfaces.UserQueryResponse;

import java.util.List;

/**
 * class to represent a list of user ids
 */
public class UserQueryResponseImpl implements UserQueryResponse {

    private List<String> ids;

    /**
     * method to set list of user ids
     * @param ids (List<String> with user ids)
     */
    @Override
    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    /**
     * method to get list of user ids
     * @return ids (list of user ids)
     */
    @Override
    public List<String> getIds() {
        return ids;
    }

}

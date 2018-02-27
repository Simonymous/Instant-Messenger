package model.classes;

import model.interfaces.UserQueryResponse;

import java.util.List;

public class UserQueryResponseImpl implements UserQueryResponse {

    private List<String> ids;

    @Override
    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public List<String> getIds() {
        return ids;
    }

}

package michaeljansen.cput.ac.za.dinermenuitems.Services;

import java.util.List;

/**
 * Created by Michael on 25/09/2015.
 */
public interface Services<S, ID> {

    public S findById(ID id);

    public String save(S entity);

    public String update(S entity);

    public String delete(S entity);

    public List<S> findAll();

}

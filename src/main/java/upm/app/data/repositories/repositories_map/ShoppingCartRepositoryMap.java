package upm.app.data.repositories.repositories_map;

import upm.app.data.models.ShoppingCart;
import upm.app.data.repositories.ShoppingCartRepository;

public class ShoppingCartRepositoryMap extends GenericRepositoryMap<ShoppingCart> implements ShoppingCartRepository {

    @Override
    protected Integer getId(ShoppingCart entity) {
        return entity.getId();
    }

    @Override
    protected void setId(ShoppingCart entity, Integer id) {
        entity.setId(id);
    }
}

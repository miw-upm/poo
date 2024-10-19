package upm.app2023.data.repositories.repositories_map;

import upm.app2023.data.models.ShoppingCart;
import upm.app2023.data.repositories.ShoppingCartRepository;

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

package upm.app.data.repositories.version1;

import upm.app.data.models.ShoppingCart;

public class ShoppingCartRepositoryMap extends GenericRepositoryMap<ShoppingCart> {

    @Override
    protected Integer getId(ShoppingCart entity) {
        return entity.getId();
    }

    @Override
    protected void setId(ShoppingCart entity, Integer id) {
        entity.setId(id);
    }
}

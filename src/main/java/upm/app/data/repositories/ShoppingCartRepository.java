package upm.app.data.repositories;

import upm.app.data.models.ShoppingCart;

public class ShoppingCartRepository extends GenericRepository<ShoppingCart> {

    @Override
    public Integer getId(ShoppingCart entity) {
        return null;
    }

    @Override
    public void setId(ShoppingCart entity, Integer id) {

    }
}

package com.example.produtoisapi.ShopingCartManagement.repositories;

import com.example.produtoisapi.ShopingCartManagement.model.ShopingCart;
import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.userManagement.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ShopingCartRepository extends CrudRepository<ShopingCart,Long> {
    @Override
    Optional<ShopingCart> findById(Long idShopingCart);
    @Query("SELECT s FROM ShopingCart s WHERE s.user = :user ORDER BY s.date DESC")
    List<ShopingCart> findAllByUser(@Param("user") User user);

    @Modifying
    @Transactional
    @Query("DELETE FROM ShopingCart s WHERE s.user = :user")
    void deleteAllByUser(@Param("user") User user);

}

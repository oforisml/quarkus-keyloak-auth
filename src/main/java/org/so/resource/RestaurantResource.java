package org.so.resource;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.so.entity.Menu;
import org.so.entity.MenuItem;
import org.so.entity.Restaurant;

import java.math.BigDecimal;
import java.util.List;

@Path("/restaurant")
//@Authenticated
public class RestaurantResource {
    @GET
    @Path("/public/list")
//    @PermitAll
    public List<Restaurant> getRestaurants(){
        return Restaurant.listAll();
    }


    @GET
    @Path("/public/menu/{restaurantId}")
//    @PermitAll
    public Menu getMenu(@PathParam("restaurantId") Long restaurantId){
        Menu menu = Menu.find("restaurantId =?1 and active", restaurantId).firstResult();
        if(menu != null)
            menu.setMenuItems(MenuItem.find("menuId = ?1", menu.id).list());
        return menu;
    }

    @POST
    @Transactional
//    @RolesAllowed("admin")
    public Restaurant createRestaurant(Restaurant restaurant){
        restaurant.persist();
        return  restaurant;
    }

    @POST
    @Path("/menu")
    @Transactional
    public Menu createMenu(Menu menu){
        menu.persist();
        menu.getMenuItems().forEach(menuItem -> {
            menuItem.setMenuId(menu.id);
           menuItem.persist();
        });
        return menu;
    }

    @PUT
    @Path("/menu/item/{item}/{price}")
    @Transactional
    @SecurityRequirement(name = "Keycloak")
//    @RolesAllowed("owner")
    public MenuItem createMenuItem(@PathParam("item") Long itemId,
                                   @PathParam("price")BigDecimal price){
        MenuItem menuItem = MenuItem.findById(itemId);
        menuItem.setPrice(price);
        menuItem.persist();
        return menuItem;
    }
}

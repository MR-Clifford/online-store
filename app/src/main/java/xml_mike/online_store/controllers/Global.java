package xml_mike.online_store.controllers;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import xml_mike.online_store.models.CartItem;
import xml_mike.online_store.models.Product;

/**
 * Created by MClifford on 22/04/16.
 *
 */
public class Global extends Application {

    public static List<Product> products = new ArrayList<>();
    public static List<Product> wishlist = new ArrayList<>();
    public static List<CartItem> cart = new ArrayList<>();

}

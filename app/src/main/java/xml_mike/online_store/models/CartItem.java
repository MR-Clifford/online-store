package xml_mike.online_store.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MClifford on 22/04/16.
 *
 * Based on the add to cart response from the web API
 * NOTE: product was added to map the product to the product ID
 */
public class CartItem {
    public Product product;

    @SerializedName("cartId")
    public int cartId = -1;

    @SerializedName("productId")
    public int productId = -1;
}

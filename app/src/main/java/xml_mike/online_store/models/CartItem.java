package xml_mike.online_store.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MClifford on 22/04/16.
 */
public class CartItem {
    public Product product;

    @SerializedName("cartId")
    public int cartId = -1;

    @SerializedName("productId")
    public int productId = -1;
}

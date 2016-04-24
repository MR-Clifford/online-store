package xml_mike.online_store.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MClifford on 22/04/16.
 *
 * create class based on json response from server.
 */
public class Product {

    @SerializedName("productId")
    int productid = -1;

    @SerializedName("name")
    String name = "";
    @SerializedName("category")
    String category = "";

    @SerializedName("price")
    String price = "0.00";

    @SerializedName("oldPrice")
    String oldPrice = "0.00";

    @SerializedName("stock")
    int stock = 0;

    public Product(int productid, String name, String category, String price, String oldPrice, int stock) {
        this.productid = productid;
        this.name = name;
        this.category = category;
        this.price = price;
        this.oldPrice = oldPrice;
        this.stock = stock;
    }

    public int getProductid() {
        return productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

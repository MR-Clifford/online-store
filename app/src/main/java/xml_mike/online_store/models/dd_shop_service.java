package xml_mike.online_store.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by MClifford on 22/04/16.
 *
 * Based on API given for web store. These map all of the responses of the API including the Get, Post and Delete
 */
public interface dd_shop_service {

    @GET("products")
    Call<List<Product>> listProducts();

    @GET("products/{id}")
    Call<Product> getProduct( @Path("id") int id);

    @FormUrlEncoded
    @POST("cart")
    Call<CartItem> addToCart(@Field("productId") int id);

    @DELETE("cart/{id}")
    Call<Product> removeFromCart(@Path("id") int id );
}

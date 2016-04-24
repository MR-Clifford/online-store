package xml_mike.online_store.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xml_mike.online_store.R;
import xml_mike.online_store.models.CartItem;
import xml_mike.online_store.models.Product;
import xml_mike.online_store.models.dd_shop_service;
import xml_mike.online_store.view.PageAdapter;
import xml_mike.online_store.view.NotifyDataSetChanged;

public class MainActivity extends AppCompatActivity implements ProductFragment.ProductFragmentInteractionListener , CartFragment.CartFragmentInteractionListener, WishlistFragment.WishlistFragmentInteractionListener {

    public static final String BASE_URL = "http://private-anon-33bb3031e-ddshop.apiary-mock.com/";

    Retrofit retrofit;
    dd_shop_service web_shop_service;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    NotifyDataSetChanged wishlistFragment;
    NotifyDataSetChanged productFragment;
    NotifyDataSetChanged cartFragment;

    final Fragment[] fragments = new Fragment[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments[0] = new WishlistFragment();
        fragments[1] = new ProductFragment();
        fragments[2] = new CartFragment();

        //setup notification updates
        wishlistFragment = (NotifyDataSetChanged) fragments[0];
        productFragment = (NotifyDataSetChanged) fragments[1];
        cartFragment = (NotifyDataSetChanged) fragments[2];


        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new PageAdapter(this.getSupportFragmentManager(), fragments);
        mPager.setAdapter(mPagerAdapter);

        GsonConverterFactory.create();
        Gson gson = new GsonBuilder().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        web_shop_service = retrofit.create(dd_shop_service.class);

        Call<List<Product>> call =  web_shop_service.listProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Global.products.addAll(response.body());
                productFragment.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                    Log.e("Failure","Could not connect to online store");
            }
        });
    }

    @Override
    public void addToWishlist(Product product) {
        Global.wishlist.add(product);
        wishlistFragment.notifyDataSetChanged();
    }

    @Override
    public void addToCart(final Product product) {

        if(product.getStock() <= 0) {
            Toast.makeText(this, "Item not in stock",Toast.LENGTH_SHORT).show();
            return;
        }

        Call<CartItem> call = web_shop_service.addToCart(product.getProductid());

        call.enqueue(new Callback<CartItem>() {
            @Override
            public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                Toast.makeText(MainActivity.this,"Item Added to Cart",Toast.LENGTH_SHORT).show();
                CartItem cartItem = response.body();
                cartItem.product = product;
                Global.cart.add(cartItem);
                cartFragment.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CartItem> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void removeFromCart(final CartItem cartItem) {
        Call<Product> call = web_shop_service.removeFromCart(cartItem.product.getProductid());

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Toast.makeText(MainActivity.this,"Item removed from cart",Toast.LENGTH_SHORT).show();
                Global.cart.remove(cartItem);
                cartFragment.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Item was not removed",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void removeFromWishlist(Product product) {
        Global.wishlist.remove(product);
        wishlistFragment.notifyDataSetChanged();
    }
}

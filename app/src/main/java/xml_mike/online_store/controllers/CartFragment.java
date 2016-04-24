package xml_mike.online_store.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

import xml_mike.online_store.R;
import xml_mike.online_store.models.CartItem;
import xml_mike.online_store.view.CartProductRecyclerViewAdapter;
import xml_mike.online_store.view.NotifyDataSetChanged;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link CartFragmentInteractionListener}
 * interface.
 */
public class CartFragment extends Fragment implements NotifyDataSetChanged {

    private int mColumnCount = 1;
    private CartFragmentInteractionListener mListener;
    private CartProductRecyclerViewAdapter cartProductRecyclerViewAdapter;
    private TextView totalPriceView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CartFragment() {
    }

    @SuppressWarnings("unused")
    public static CartFragment newInstance(int columnCount) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            //mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list_cart, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        totalPriceView = (TextView) view.findViewById(R.id.total);
        cartProductRecyclerViewAdapter = new CartProductRecyclerViewAdapter(Global.cart, mListener);

        // Set the adapter

        Context context = recyclerView.getContext();

        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        recyclerView.setAdapter(cartProductRecyclerViewAdapter);

        totalPriceView.setText("Total: £ 0.00");

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CartFragmentInteractionListener) {
            mListener = (CartFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void notifyDataSetChanged() {
        if(cartProductRecyclerViewAdapter != null) {

            cartProductRecyclerViewAdapter.notifyDataSetChanged();
            updateTotal();
        }
    }

    public void updateTotal(){
        DecimalFormat format = new DecimalFormat("#.00");
        double totalPrice = 0;

        for(CartItem item : Global.cart){
            totalPrice += Double.parseDouble(item.product.getPrice());
        }

        totalPriceView.setText("Total: £ "+format.format(totalPrice));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface CartFragmentInteractionListener {
        void removeFromCart(CartItem cartItem);
    }


}

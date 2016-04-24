package xml_mike.online_store.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import xml_mike.online_store.R;
import xml_mike.online_store.controllers.CartFragment;
import xml_mike.online_store.models.CartItem;
import xml_mike.online_store.models.Product;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Product} and makes a call to the
 * specified {@link CartFragment.CartFragmentInteractionListener}.
 */
public class CartProductRecyclerViewAdapter extends RecyclerView.Adapter<CartProductRecyclerViewAdapter.ViewHolder> {

    private final List<CartItem> mValues;
    private final CartFragment.CartFragmentInteractionListener mListener;

    public CartProductRecyclerViewAdapter(List<CartItem> items, CartFragment.CartFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(""+mValues.get(position).product.getProductid());
        holder.mContentView.setText(mValues.get(position).product.getName());
        holder.mPriceView.setText("£"+mValues.get(position).product.getPrice());

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.removeFromCart(mValues.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mPriceView;
        public final Button removeButton;
        public CartItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mPriceView = (TextView) view.findViewById(R.id.price);
            removeButton = (Button) view.findViewById(R.id.remove_from_cart);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


}

package xml_mike.online_store.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import xml_mike.online_store.R;
import xml_mike.online_store.models.Product;

/**
 * {@link RecyclerView.Adapter} that can display a {@link xml_mike.online_store.models.Product} and makes a call to the
 * specified {@link xml_mike.online_store.presenter.WishlistFragment.WishlistFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class WishlistProductRecyclerViewAdapter extends RecyclerView.Adapter<WishlistProductRecyclerViewAdapter.ViewHolder> {

    private final List<Product> mValues;
    private final WishlistFragment.WishlistFragmentInteractionListener mListener;

    public WishlistProductRecyclerViewAdapter(List<Product> items, WishlistFragment.WishlistFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product_wishlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(""+mValues.get(position).getProductid());
        holder.mContentView.setText(mValues.get(position).getName());

        holder.mRemoveFromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.removeFromWishlist(mValues.get(position));
            }
        });

        holder.mAddToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.addToCart(mValues.get(position));
                mListener.removeFromWishlist(mValues.get(position));
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
        public final Button mRemoveFromButton;
        public final Button mAddToButton;
        public Product mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mRemoveFromButton = (Button) view.findViewById(R.id.remove_from_wishlist);
            mAddToButton = (Button) view.findViewById(R.id.add_to_cart);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

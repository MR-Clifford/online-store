package xml_mike.online_store.presenter;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xml_mike.online_store.R;
import xml_mike.online_store.models.Product;
import xml_mike.online_store.controllers.ProductFragment.ProductFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Product} and makes a call to the
 * specified {@link ProductFragmentInteractionListener}.
 */
public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    private final List<Product> mValues;
    private final ProductFragmentInteractionListener mListener;

    public ProductRecyclerViewAdapter(List<Product> items, ProductFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        String id = "" + mValues.get(position).getProductid();
        String stock = " x" + mValues.get(position).getStock();

        holder.mIdView.setText(id);
        holder.mStockView.setText(stock);

        holder.mContentView.setText(mValues.get(position).getName());
        holder.mCategoryView.setText(mValues.get(position).getCategory());
        holder.mPriceView.setText("£" + mValues.get(position).getPrice());

        if (mValues.get(position).getOldPrice() != null) {
            holder.mOldPriceView.setVisibility(View.VISIBLE);
            holder.mOldPriceView.setText("£" + mValues.get(position).getOldPrice());
        } else {
            holder.mOldPriceView.setVisibility(View.GONE);
        }

        holder.mView.findViewById(R.id.cart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.addToCart(mValues.get(position));
            }
        });

        holder.mView.findViewById(R.id.wishlist_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.addToWishlist(mValues.get(position));
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
        public final TextView mCategoryView;
        public final TextView mPriceView;
        public final TextView mOldPriceView;
        public final TextView mStockView;
        public Product mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mCategoryView = (TextView) view.findViewById(R.id.category);
            mPriceView = (TextView) view.findViewById(R.id.price);
            mOldPriceView = (TextView) view.findViewById(R.id.old_price);
            mOldPriceView.setPaintFlags(mOldPriceView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mStockView = (TextView) view.findViewById(R.id.stock);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

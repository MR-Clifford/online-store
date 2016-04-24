package xml_mike.online_store.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by MClifford on 22/04/16.
 */
public class PageAdapter extends FragmentStatePagerAdapter {

    Fragment[] pages = new Fragment[3];
    String[] pageTitles = new String[3];

    public PageAdapter(FragmentManager fm, Fragment[] frags) {
        super(fm);

        pages[0] = frags[0];
        pages[1] = frags[1];
        pages[2] = frags[2];

        pageTitles[0] = "Wishlist";
        pageTitles[1] = "Products";
        pageTitles[2] = "Cart";
    }

    @Override
    public Fragment getItem(int position) {
        return pages[position];
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}

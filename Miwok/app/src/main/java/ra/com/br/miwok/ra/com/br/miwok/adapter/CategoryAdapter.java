package ra.com.br.miwok.ra.com.br.miwok.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ra.com.br.miwok.R;
import ra.com.br.miwok.ra.com.br.miwok.fragment.WordFragment;

public class CategoryAdapter extends FragmentPagerAdapter {

    private static final int NUMBER_OF_VIEWS = 4;
    private Context context;

    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return WordFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: {
                return context.getString(R.string.numbers);
            }
            case 1: {
                return context.getString(R.string.family);
            }
            case 2: {
                return context.getString(R.string.colors);
            }
            case 3: {
                return context.getString(R.string.phrases);
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUMBER_OF_VIEWS;
    }
}

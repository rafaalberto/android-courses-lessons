package ra.com.br.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int NUMBER_OF_PAGES = 5;

    SimpleFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return new MondayFragment();
            }
            case 1: {
                return new TuesdayFragment();
            }
            case 2: {
                return new WednesdayFragment();
            }
            case 3: {
                return new ThursdayFragment();
            }
            case 4: {
                return new FridayFragment();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }
}

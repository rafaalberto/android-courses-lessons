package ra.com.br.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int NUMBER_OF_PAGES = 7;

    SimpleFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return new WeekFragment().newInstance(R.string.sunday);
            }
            case 1: {
                return new WeekFragment().newInstance(R.string.monday);
            }
            case 2: {
                return new WeekFragment().newInstance(R.string.tuesday);
            }
            case 3: {
                return new WeekFragment().newInstance(R.string.wednesday);
            }
            case 4: {
                return new WeekFragment().newInstance(R.string.thursday);
            }
            case 5: {
                return new WeekFragment().newInstance(R.string.friday);
            }
            case 6: {
                return new WeekFragment().newInstance(R.string.saturday);
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }
}

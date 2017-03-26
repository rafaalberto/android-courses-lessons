package ra.com.br.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeekFragment extends Fragment {

    private static final String DAY_OF_WEEK = "dayOfWeek";

    public static WeekFragment newInstance(int dayOfWeek) {

        Bundle args = new Bundle();

        WeekFragment fragment = new WeekFragment();
        args.putInt(DAY_OF_WEEK, dayOfWeek);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textViewDayOfWeek = (TextView) view.findViewById(R.id.text_view_day_of_week);
        textViewDayOfWeek.setText(getString(getArguments().getInt(DAY_OF_WEEK)));
    }
}

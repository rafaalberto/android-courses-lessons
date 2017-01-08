package ra.com.br.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private static final int ZERO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        loadList();
    }

    private void loadList() {
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti"));
        words.add(new Word("two", "otiiko"));
        words.add(new Word("three", "tolookosu  "));
        words.add(new Word("four", "oyyisa"));
        words.add(new Word("five", "massokka"));
        words.add(new Word("six", "temmokka"));
        words.add(new Word("seven", "kenekaku"));
        words.add(new Word("eight", "kawinta"));
        words.add(new Word("nine", "wo'e"));
        words.add(new Word("ten", "na'aacha"));

        WordAdapter wordAdapter = new WordAdapter(this, words);
        ListView listViewNumbers = (ListView) findViewById(R.id.list_view_numbers);
        listViewNumbers.setAdapter(wordAdapter);
    }
}

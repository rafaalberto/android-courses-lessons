package ra.com.br.miwok.ra.com.br.miwok.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import ra.com.br.miwok.R;
import ra.com.br.miwok.ra.com.br.miwok.adapter.WordAdapter;
import ra.com.br.miwok.ra.com.br.miwok.data.WordData;
import ra.com.br.miwok.ra.com.br.miwok.model.Word;
import ra.com.br.miwok.ra.com.br.miwok.utils.MediaPlayerUtils;

public class WordFragment extends Fragment {

    private static final String INDEX = "index";

    private MediaPlayer mediaPlayer;

    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                MediaPlayerUtils.releaseMediaPlayer(mediaPlayer, audioManager, onAudioFocusChangeListener);
            }
        }
    };

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            MediaPlayerUtils.releaseMediaPlayer(mediaPlayer, audioManager, onAudioFocusChangeListener);
        }
    };

    public static WordFragment newInstance(int index) {

        Bundle args = new Bundle();

        WordFragment fragment = new WordFragment();
        args.putInt(INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view_word, container, false);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        loadList(rootView);
        return rootView;
    }

    private void loadList(View rootView) {
        final WordAdapter wordAdapter = new WordAdapter(getActivity(), WordData.getList(getArguments().getInt(INDEX)), WordData.getColor(getArguments().getInt(INDEX)));
        ListView listViewWords = (ListView) rootView.findViewById(R.id.list_view_words);
        listViewWords.setAdapter(wordAdapter);

        listViewWords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = (Word) wordAdapter.getItem(position);
                MediaPlayerUtils.releaseMediaPlayer(mediaPlayer, audioManager, onAudioFocusChangeListener);

                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        MediaPlayerUtils.releaseMediaPlayer(mediaPlayer, audioManager, onAudioFocusChangeListener);
    }
}

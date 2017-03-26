package ra.com.br.miwok.ra.com.br.miwok.utils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.ImageView;

import ra.com.br.miwok.R;

public abstract class MediaPlayerUtils {

    public static void releaseMediaPlayer(MediaPlayer mediaPlayer, AudioManager audioManager,
                                          AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, ImageView imageViewMedia) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
            imageViewMedia.setImageResource(R.drawable.ic_play_arrow);
        }
    }
}

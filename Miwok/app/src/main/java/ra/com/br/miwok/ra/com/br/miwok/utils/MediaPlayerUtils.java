package ra.com.br.miwok.ra.com.br.miwok.utils;

import android.media.AudioManager;
import android.media.MediaPlayer;

public abstract class MediaPlayerUtils {

    public static void releaseMediaPlayer(MediaPlayer mediaPlayer, AudioManager audioManager,
                                          AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}

package ra.com.br.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;

public class MediaPlayerUtils {

    public static void releaseMediaPlayer(MediaPlayer mediaPlayer, AudioManager audioManager,
                                          AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}

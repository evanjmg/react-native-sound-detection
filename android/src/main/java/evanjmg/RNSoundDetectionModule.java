package evanjmg;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import android.media.MediaPlayer;
import android.media.AudioManager;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import android.util.Log;
import com.facebook.react.bridge.LifecycleEventListener;


public class RNSoundDetectionModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
  private MediaPlayer mediaPlayer;
  private final ReactApplicationContext reactContext;
  private static final String ON_PREPARED = "OnPreparedEvent";
  private static final Map<Integer, String> LEGACY_TRACK_CODES =
          Collections.unmodifiableMap(new HashMap<Integer, String>() {{
            put(0, "unknown");
            put(1, "video");
            put(2, "audio");
            put(3, "timedtext");
            put(4, "subtitle");
            put(5, "metadata");
          }});

  public RNSoundDetectionModule(ReactApplicationContext reactContext) {
    super(reactContext);

    this.reactContext = reactContext;
    this.reactContext.addLifecycleEventListener(this);
  }

  @ReactMethod
  public void getTracks(String path, final Callback cb) {
    try {
      this.mediaPlayer = new MediaPlayer();
      this.mediaPlayer.setDataSource(path);
      this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){


        @Override
        public void onPrepared(MediaPlayer mp) {

          try {
            MediaPlayer.TrackInfo[] tracks = mp.getTrackInfo();
            mp.release();

            String convertedTrackInfo = new String("[");
            for(int i = 0; i < tracks.length; i++){
              convertedTrackInfo += i != 0 ? "," : "";
              convertedTrackInfo += "{\"type\":\"" + LEGACY_TRACK_CODES.get(tracks[i].getTrackType()) + "\"}";
            }
            convertedTrackInfo += "]";
            cb.invoke(convertedTrackInfo, null);
          } catch (Exception e) {
            cb.invoke(e.toString(), null);
          }
        }
      });
      this.mediaPlayer.prepareAsync();
    } catch (IOException e) {
      this.onHostDestroy();
      cb.invoke(e.toString(), null);
    }
  }
  public void onHostResume() {}
  public void onHostPause() {
    this.onHostDestroy();
  }
  public void onHostDestroy() {
    if (this.mediaPlayer != null) {
      this.mediaPlayer.release();
      this.mediaPlayer = null;
    }
  }
  @Override
  public String getName() {
    return "RNSoundDetection";
  }
}

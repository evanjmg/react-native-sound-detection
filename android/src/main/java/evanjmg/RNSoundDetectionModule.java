
package evanjmg;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import android.media.MediaPlayer;
import android.media.AudioManager;
import java.io.IOException;
import com.google.gson.Gson;
import android.util.Log;

public class RNSoundDetectionModule extends ReactContextBaseJavaModule {
  private final MediaPlayer mediaPlayer;
  private final ReactApplicationContext reactContext;
  private static final String ON_PREPARED = "OnPreparedEvent";
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
              Gson gson = new Gson();

              cb.invoke(gson.toJson(tracks), null);
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
  public void onHostDestroy() {
    if (this.mediaPlayer) {
      this.mediaPlayer.release();
      this.mediaPlayer = null;
    }
  }
  @Override
  public String getName() {
    return "RNSoundDetection";
  }
}
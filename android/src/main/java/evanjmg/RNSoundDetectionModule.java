
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

  private final ReactApplicationContext reactContext;
  private static final String ON_PREPARED = "OnPreparedEvent";
  public RNSoundDetectionModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @ReactMethod
  public void getTracks(String path, final Callback cb) {
    try {
      MediaPlayer mediaPlayer = new MediaPlayer();
      mediaPlayer.setDataSource(path);
      mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){

      @Override
          public void onPrepared(MediaPlayer mp) {
            Log.i(ON_PREPARED, "prepared**************************************");
            try {
              MediaPlayer.TrackInfo[] tracks = mp.getTrackInfo();
              Gson gson = new Gson();
              cb.invoke(gson.toJson(tracks), null);
            } catch (Exception e) {
              cb.invoke(e.toString(), null);
            }

          }
      });
      mediaPlayer.prepareAsync();
    } catch (IOException e) {
      cb.invoke(e.toString(), null);
    }
  }
  @Override
  public String getName() {
    return "RNSoundDetection";
  }
}
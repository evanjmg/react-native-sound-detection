
import { NativeModules, Platform } from 'react-native';
const isiOS = Platform.OS === 'ios'
const { RNSoundDetection } = NativeModules;

const SoundDetection = isiOS ? RNSoundDetection : { 
  getTracks: (url) => {
    return new Promise((r) => {
      RNSoundDetection.getTracks(url, (tracks) => {
        r(tracks ?  JSON.parse(tracks) : []);
      })
    })
  } 
}
export default SoundDetection;

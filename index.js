
import { NativeModules, Platform } from 'react-native';
const isiOS = Platform.OS === 'ios'
const { RNSoundDetection } = NativeModules;

const getAudioTrack = (track) => {
  const audioTrack = track && track.mFormat && track.mFormat.mMap
  if (audioTrack) {
    return { ...audioTrack, type: audioTrack.mime.includes('audio') ? 'audio' : 'video' }
  }
  return null
}

const SoundDetection = isiOS ? RNSoundDetection : { getTracks: (url) => {
  return new Promise((r) => {
    RNSoundDetection.getTracks(url, (tracks) => {
      let result = tracks
      if (result) {
        result = JSON.parse(tracks).map(getAudioTrack)
      }
      r(result || [])
    })
  })
} }
export default SoundDetection;

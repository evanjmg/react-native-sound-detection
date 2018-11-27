
# react-native-sound-detection

This library simply detects whether audio tracks exist on a media file in Android React Native.

## Getting started

`$ npm install react-native-sound-detection --save`

### Mostly automatic installation

`$ react-native link react-native-sound-detection`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import evanjmg.RNSoundDetectionPackage;` to the imports at the top of the file
  - Add `new RNSoundDetectionPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-sound-detection'
  	project(':react-native-sound-detection').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-sound-detection/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-sound-detection')
  	```


## Usage
```javascript
import RNSoundDetection from 'react-native-sound-detection';
// use a remote or local url
RNSoundDetection.getAudioTracks('https://video.com.mp4', (audioTracks) => {
	console.log(audioTracks)
})
```

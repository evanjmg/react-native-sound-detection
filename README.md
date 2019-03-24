
# react-native-sound-detection

This library simply gets the audio/video tracks and their type. This is to detect whether they exist on a media file (Video/Mp3/etc) in Android and iOS React Native.

## Getting started

`$ npm install react-native-sound-detection --save`

### Mostly automatic installation

`$ react-native link react-native-sound-detection`

### Manual installation

#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-sound-detection` and add `RNSoundDetection.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNSoundDetection.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

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
const tracks = await RNSoundDetection.getTracks('https://video.com.mp4')
console.log(tracks)
// [{ type: 'video' | 'audio', lang: 'eng', mime?: 'mp4' }]
// MIME is only available on Android
```



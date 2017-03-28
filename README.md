# FLAC Loader
An asset loader for jmonkeyengine that uses JustFLAC (https://github.com/drogatkin/JustFLAC) to load flac files.

## Gradle
[https://jitpack.io/#riccardobl/jme3-plugin-flac-loader](https://jitpack.io/#riccardobl/jme3-plugin-flac-loader)

## Usage
```
  FLACLoader.init(assetManager);
  AudioNode test_flac = new AudioNode(assetManager,"test.flac",true);
```

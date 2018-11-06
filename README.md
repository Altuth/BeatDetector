# BeatDetector

Finds appropriate beatplacement to 192kbps mp3's, for use in the rhytm game osu.

1. Add the audio file to "src" with the name "audio.mp3".

2. Add the correct offset and bpm in main.

3. Run (might take a minute).

4. Copy all elements which were printed in the console, and add them under [HitObjects] in the .osu file

There might be some small misbeats, but it will give new and experienced mappers atleast a headstart. 

* If you're getting too high density, make multiplier lower, and vice versa, it shouldnt need to range from lower than 1.0 or higher than 1.05
* mp3 path can be changed in the class ReadFiles.java
* Video tutorial https://www.youtube.com/watch?v=mz2djzlxg2Q&feature=youtu.be

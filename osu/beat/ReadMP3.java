package osu.beat;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ReadMP3 {


    private ArrayList<Integer> bytes = new ArrayList<>();
    private AudioFormat decodedFormat;

    public ReadMP3() throws UnsupportedAudioFileException, IOException {

        String filename = new ReadFiles().getFile();
        File file = new File(filename);
        AudioInputStream in = AudioSystem.getAudioInputStream(file);
        AudioInputStream din = null;
        AudioFormat baseFormat = in.getFormat();
        AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                (float)44.1,
                16,
                2,
                626,
                (float)38.4615385,
                false);
        din = AudioSystem.getAudioInputStream(decodedFormat, in);
        this.decodedFormat = decodedFormat;

        int i = 0;
        while(true){
            int currentByte = din.read();
            if (currentByte == -1) {break;}
            bytes.add(i, currentByte);
            i++;
        }
        din.close();
        in.close();
    }

    public ArrayList<Integer> getBytes() {
        return bytes;
    }

    public AudioFormat getDecodedFormat() {
        return decodedFormat;
    }

}

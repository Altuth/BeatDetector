package osu.beat;

public class AnalyzeMP3 {


    //adds 4 bytes to offset[i], where each index represents 1hz, and 44100hz = 1sec, first 2 bytes are channel0,next 2 bytes is channel1
    public static double[][] calculate(ReadMP3 mp3) {

        double[][] offset  = new double[mp3.getBytes().size()/4][4];
        int counter = 0;
        for(int i = 0; i < mp3.getBytes().size()/4;i++) {
            for(int j = 0; j < 4; j++) {
                offset[i][j] = mp3.getBytes().get(counter);
                counter++;
            }
        }
        return offset;
    }
}

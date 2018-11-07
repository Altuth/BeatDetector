package osu.beat;

import org.jtransforms.dht.DoubleDHT_2D;
import org.jtransforms.fft.DoubleFFT_1D;


import javax.swing.*;

public class Main extends JFrame{

    //Simply add bpm and offset manually and things will work out
    public static final double bpm = 207;
    public static final double offset = 1190;
    
    public static final double multiplier = 1.02;
    
    //how many samples should be read at each offset, starting at the offset up towards offset + sampleSize
    public static final int sampleSize = 1024;

    //beats will be those with a value over the threshold
    public static final int globalThreshold = 2400;
    public static final int lowThreshold = 400;
    public static final int midThreshold = 100;
    public static final int highThreshold = 300;

    //calculations needed to represent the offset in array form
    public static final double increment = (double)((60000/bpm) / 4);
    public static final double arrayInc = (double)(44100 / (1000/increment));
    public static final double initOffset = (double)((offset/1000) * 44100);


    public static void main(String[] args){

        try {

            //adds all the bytes of the mp3 to the array audio
            double[][] audio = AnalyzeMP3.calculate(new ReadMP3());

            //visualize(audio);

            checkValue(audio);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //just adds fft calculations to array
    private static double[] fft(double[] array) {
        DoubleFFT_1D g = new DoubleFFT_1D(array.length);
        g.realInverse(array, true);

        return array;
    }

    //returns a fixed chunk of channel 1
    private static double[] chunk1(double[][] array, int offset) {

        int arrLength = 1024;

        double[] channel1 = new double[arrLength];

        //time is the equivalent ms in the array
        double time = (double)((offset / 1000) * 44100);
        int counter = 0;

        for(int i = 0; i < arrLength/2; i++) {
            for(int j = 0; j < 2; j++) {
                channel1[counter] = array[(int)time + i][j];
                counter++;
            }
        }
        return channel1;
    }

    //returns a fixed chunk of channel 2
    private static double[] chunk2(double[][] array, int offset) {

        int arrLength = 1024;
        double[] channel2 = new double[arrLength];
        //time is the equivalent ms in the array
        double time = (double)((offset / 1000) * 44100);
        int counter = 0;

        for(int i = 0; i < arrLength/2; i++) {
            for(int j = 2; j < 4; j++) {
                channel2[counter] = array[(int)time + i][j];
                counter++;
            }
        }
        return channel2;
    }

    //visualizes an array
    private static void visualize(double[][] array) throws InterruptedException {

        JFrame jf = new JFrame("graph");
        int height = 600, width = 1000;
        jf.setBounds(0,0,width, height);
        jf.setResizable(true);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        int counter = 0;
        for(int i = 0; i < array.length; i+=441) {

            //offset needs to be equal to ms
            int offset = counter;
            counter += 10;
            double[] p1 = fft(chunk1(array, offset));
            double[] p2 = fft(chunk2(array, offset));

            PaintGraph g1 = new PaintGraph(p1);
            PaintGraph g = new PaintGraph(p2);
            //Thread.sleep(10);
            jf.add(g1);
            jf.add(g);

            jf.revalidate();

        }

    }

    //checks if there is a beat at that given offset while incrementing accordingly
    private static void checkValue(double[][] array) {

        double counter = offset - increment;
        int[] scores = new int[array.length];
        int temp = 0;
        int tempCount = 0;

        for(int i = (int)initOffset; i < array.length; i += arrayInc) {

            //initializes the object p to get the fft transforms to
            double[][] p = fft2d(slice2d(array, i));
            IsBeat g = new IsBeat(p);

            scores[temp] = g.checkAll();
            if(tempCount == 16) {
                tempCount = 0;
                int average = 0;

                for(int e = temp-16; e < temp; e++) {
                    average += scores[e];
                }

                for(int h = temp-16; h < temp; h++) {

                    if((scores[h]*multiplier) > (average/16)) {
                        System.out.println("174,123," + (int)(Math.floor(counter)) + ",5,0,0:0:0:0:");
                        counter += increment;
                    }
                    else {
                        counter += increment;
                    }
                }
            }
            temp++;
            tempCount++;

        }
    }

    private static double[][] slice2d(double[][] array, double counter) {

        double[][] darray = new double[sampleSize][4];


        for(int i = 0; i < sampleSize; i++) {
            for(int j = 0; j < 4; j++) {
                darray[i][j] = array[((int)counter + i)][j];

            }
        }
        return darray;
    }

    private static double[][] fft2d(double[][] array) {
        DoubleDHT_2D g = new DoubleDHT_2D(array.length, 4);
        g.inverse(array, true);

        return array;
    }
}

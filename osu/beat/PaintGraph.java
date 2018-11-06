package osu.beat;

import javax.swing.*;
import java.awt.*;

public class PaintGraph extends JPanel {

    private double[] graph;


    public PaintGraph(double[] array) {

        this.graph = array;

        setupPanel();
    }

    private void setupPanel() {
        this.setBackground(Color.BLACK);


    }

    @Override
    protected void paintComponent(Graphics currentGraphics) {
        super.paintComponent(currentGraphics);
        Graphics2D graphics = (Graphics2D) currentGraphics;

        double v = Math.sqrt(Math.abs((getMinValue(graph))^2 + (getMaxValue(graph))^2));

        for(int i = 0; i < graph.length; i++) {
            double height = (graph[i]/v);

            graphics.setColor(Color.WHITE);
            graphics.drawRect(i+10,300,1,(int)height*4);

        }
    }

    private static int getMaxValue(double[] numbers){
        double maxValue = numbers[0];
        for(int i=1;i < numbers.length;i++){
            if(numbers[i] > maxValue){
                maxValue = numbers[i];
            }
        }
        return (int)maxValue;
    }
    private static int getMinValue(double[] numbers){
        double minValue = numbers[0];
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] < minValue){
                minValue = numbers[i];
            }
        }
        return (int)minValue;
    }
}

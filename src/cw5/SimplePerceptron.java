package cw5;

import java.util.Random;

public class SimplePerceptron {

    double[] weights;
    double threshold;

    public void Trainer(double[][] inputs, int[] outputs, double threshold, double lrate, int gen) {
        this.threshold = threshold;
        int ins = inputs[0].length;
        int outs = outputs.length;
        weights = new double[]{0.5,0.5,0.5};
        Random seed = new Random();

        int errorCount = 0;

        for (int i = 0; i < gen; i++) {
            for (int j = 0; j < outs; j++) {
                int output = Output(inputs[j]);
                int error = outputs[j] - output;
                errorCount += error;

                for(int k = 0; k < ins; k++) {
                    double delta = lrate * error * inputs[j][k];
                    weights[k] += delta;
                }
            }
            if(errorCount == 0) {
                break;
            }
        }
    }

    public int Output(double[] input) {
        double sum = 0.0;
        for(int i = 0; i < input.length; i++) {
            sum += input[i] * weights[i];
        }

        if(sum > threshold) {
            return 1;
        }
        return 0;
    }
}

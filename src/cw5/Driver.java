package cw5;

import java.util.List;

public class Driver {

    public static void main(String[] args){
        SimplePerceptron perceptron = new SimplePerceptron();

        // Simulate Perceptron for x1 ^ !x2
        double inputs[][] = {{1,1},{1,0},{0,1},{0,0}};
        int outputs[] = {0,1,0,0}; // False True False False

        perceptron.Trainer(inputs, outputs, 0.2, 0.5, 200);

        System.out.println(perceptron.Output(new double[]{0,0}));
    }
}

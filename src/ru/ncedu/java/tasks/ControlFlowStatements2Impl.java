package ru.ncedu.java.tasks;


import static java.lang.Math.*;

public class ControlFlowStatements2Impl implements ControlFlowStatements2 {
    public static void main(String args[]){

    }
    @Override
    public int getFunctionValue(int x) {
        if(x < -2 || x > 2){
            return 2*x;
        }
        else{
            return x*(-3);
        }
    }

    @Override
    public String decodeMark(int mark) {
            switch (mark) {
                case 1:
                    return "Fail";
                case 2:
                    return "Poor";
                case 3:
                    return "Satisfactory";
                case 4:
                    return "Good";
                case 5:
                    return "Excellent";
                default: return "Error";
            }
    }

    @Override
    public double[][] initArray() {
        double[][] array = new double[5][8];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                array[i][j] = pow(i,4) - sqrt(j);
            }
        }
        return array;
    }

    @Override
    public double getMaxValue(double[][] array) {
        double max = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if(array[i][j] > max){
                    max = array[i][j];
                }
            }
        }
        return max;
    }

    @Override
    public Sportsman calculateSportsman(float P) {
        Sportsman man = new Sportsman();
        float distance = 10;
        man.addDay(distance);
        P = P / 100;
        while(man.getTotalDistance() <= 200){
            man.addDay(distance + distance*P);
            distance += distance*P;
        }
        return man;
    }
}

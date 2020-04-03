package com.example.calculatrice;

public class Service {
    public static double operation (String arg1, String sign, String arg2) {

        if(arg2.equals(""))
            return Double.parseDouble(arg1);

        double result = 0;
        switch (sign) {
            case "+":
                result = Double.parseDouble(arg1) + Double.parseDouble(arg2);
                break;
            case "-":
                result = Double.parseDouble(arg1) - Double.parseDouble(arg2);
                break;
            case "x":
                result = Double.parseDouble(arg1) * Double.parseDouble(arg2);
                break;
            case "/":
                result = Double.parseDouble(arg1) / Double.parseDouble(arg2);
                break;
            case "%":
                result = Double.parseDouble(arg1) % Double.parseDouble(arg2);
                break;

        }
        return result;
    }

}

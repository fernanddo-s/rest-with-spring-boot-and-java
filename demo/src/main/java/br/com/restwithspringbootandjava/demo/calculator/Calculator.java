package br.com.restwithspringbootandjava.demo.calculator;

public class Calculator {

    private Calculator(){};
    public static Double sum(String numberOne, String numberTwo){
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    public static Double sub(String numberOne, String numberTwo) {
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    public static Double mul(String numberOne, String numberTwo){
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    public static Double div(String numberOne, String numberTwo){
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    public static Double media(String numberOne, String numberTwo){
        return (convertToDouble(numberOne) + convertToDouble(numberTwo))/2;
    }

    public static Double raiz(String numberOne){
        return Math.sqrt(convertToDouble(numberOne));
    }

    private static Double convertToDouble(String strNumber) {
        if(strNumber == null) return 0D;
        String number = strNumber.replaceAll(",",".");
        if(isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    private static boolean isNumeric(String strNumber) {
        if(strNumber == null) return false;
        String number = strNumber.replaceAll(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}

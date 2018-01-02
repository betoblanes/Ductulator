package com.example.beto.ductulatorman;

/**
 * Created by beto on 7/10/2017.
 */

public class Duct {
    //input
    private double max_duct_height_input, flow_rate_input, maxVelocity_input;
    //variables
    private static double ideal_square, rectangular_ductHeight, duct_cross_sectional_area, ideal_circ;

    public Duct(double flow_rate_input, double maxVelocity_input, double max_duct_height_input) {
        this.flow_rate_input = flow_rate_input;
        this.maxVelocity_input = maxVelocity_input;
        this.max_duct_height_input = max_duct_height_input;
        double op1 = flow_rate_input / 1000;
        duct_cross_sectional_area = op1 / maxVelocity_input;

        ideal_circ = 2000 * Math.pow((((flow_rate_input / 1000) / maxVelocity_input) / 3.14159), 0.5);
        //calculamos ideal square
        double a1 = Math.pow(ideal_circ / 1.265, 5) * 2;
        //=(2*(B9/1,265)^5)^0,2
        ideal_square = Math.pow(a1, 0.2);

    }

    //////->
    // ROUNDUP(valor,decimalesRedondeo) del excel
    public static double roundUp(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
        resultado = Math.round(resultado);
        resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
        return resultado;
    }

    //CEILING(decimal,múltiplo)
    public static double ceiling(double valorInicial, int múltiplo) {
        return Math.round(valorInicial / múltiplo) * múltiplo;
    }
    //////////<-


    public double rectangular_ductHeight() {
        double calc;
        double result;

        //=1000*ROUNDUP(IF(B6>B10;B10/1000;B6/1000);2)
        if (max_duct_height_input > ideal_square) {
            calc = ideal_square / 1000;
        } else {
            calc = max_duct_height_input / 1000;
        }//controlar igualtat

        //redondejar per dalt variable calc, 2 decimals
        calc = roundUp(calc, 2);
        result = calc * 1000;
        rectangular_ductHeight = result;
        return result;

    }

    public double rectangular_ductWidth() {
        double first_operation = rectangular_ductHeight / 1000000;
        //B8/1000
        double second_operation = duct_cross_sectional_area / first_operation;
        return ceiling(second_operation, 50);
    }


    public double circular_duct() {
        return ceiling(ideal_circ, 50);
    }

}

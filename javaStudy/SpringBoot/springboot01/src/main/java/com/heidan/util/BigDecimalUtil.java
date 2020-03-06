package com.heidan.util;



import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {

    private BigDecimalUtil(){
    }

    public static BigDecimal  add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.add(b2);
    }

    public static BigDecimal  sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.subtract(b2);
    }

    public static BigDecimal  mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.multiply(b2).setScale(2,BigDecimal.ROUND_HALF_UP);
    }



    public static String deToStr(BigDecimal bigDecimal){
        return bigDecimal.setScale(0, RoundingMode.HALF_UP).toString();
    }

}

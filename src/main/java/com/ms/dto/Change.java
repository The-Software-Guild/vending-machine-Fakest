package com.ms.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Change {
    enum Denomination{
        POUND(new BigDecimal("1.00")), FIFTY(new BigDecimal("0.50")), TWENTYFIVE(new BigDecimal("1.00")),
        TEN(new BigDecimal("1.00")), FIVE(new BigDecimal("1.00")), PENNY(new BigDecimal("1.00"));
        private final BigDecimal value;


        Denomination(BigDecimal bigDecimal) {
            this.value = bigDecimal;
        }
    };

    public static String getChange(BigDecimal wallet){
        BigDecimal change = wallet;
        String changeString = "";

        if(change.compareTo(new BigDecimal(0.00)) == 0){
            changeString = "No change required";
        } else {

            int[] currencyCount = new int[6];

            if (change.compareTo(Denomination.POUND.value) >= 0) {
                currencyCount[Denomination.valueOf("POUND").ordinal()] = change.divide(Denomination.POUND.value, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Denomination.POUND.value.multiply(new BigDecimal(currencyCount[0])));
            }
            if (change.compareTo(Denomination.FIFTY.value) >= 0) {
                currencyCount[Denomination.valueOf("FIFTY").ordinal()] = change.divide(Denomination.FIFTY.value, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Denomination.FIFTY.value.multiply(new BigDecimal(currencyCount[1])));
            }
            if (change.compareTo(Denomination.TWENTYFIVE.value) >= 0) {
                currencyCount[Denomination.valueOf("TWENTYFIVE").ordinal()] = change.divide(Denomination.TWENTYFIVE.value, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Denomination.TWENTYFIVE.value.multiply(new BigDecimal(currencyCount[2])));
            }
            if (change.compareTo(Denomination.TEN.value) >= 0) {
                currencyCount[Denomination.valueOf("TEN").ordinal()] = change.divide(Denomination.TEN.value, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Denomination.TEN.value.multiply(new BigDecimal(currencyCount[2])));
            }
            if (change.compareTo(Denomination.FIVE.value) >= 0) {
                currencyCount[Denomination.valueOf("FIVE").ordinal()] = change.divide(Denomination.FIVE.value, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Denomination.FIVE.value.multiply(new BigDecimal(currencyCount[2])));
            }
            if (change.compareTo(Denomination.PENNY.value) >= 0) {
                currencyCount[Denomination.valueOf("PENNY").ordinal()] = change.divide(Denomination.PENNY.value, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Denomination.PENNY.value.multiply(new BigDecimal(currencyCount[3])));
            }
            changeString = "Change:" + currencyCount[Denomination.valueOf("POUND").ordinal()] + " " + Denomination.POUND + " "
                    + currencyCount[Denomination.valueOf("FIFTY").ordinal()] + " " + Denomination.FIFTY + " "
                    + currencyCount[Denomination.valueOf("TWENTYFIVE").ordinal()] + " " + Denomination.TWENTYFIVE + " "
                    + currencyCount[Denomination.valueOf("TEN").ordinal()] + " " + Denomination.TEN + " "
                    + currencyCount[Denomination.valueOf("PENNY").ordinal()] + " " + Denomination.PENNY;


        }

        return changeString;
    }
}

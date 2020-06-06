package com.boca.grabswebservice.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DecimalFormatter {

    public String format(BigDecimal value){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        return decimalFormat.format(value);
    }
}

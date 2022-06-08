package com.jpmc.gbce.service;

import com.jpmc.gbce.dto.GBCEDataDTO;
import com.jpmc.gbce.exception.NoParValueFoundExecption;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public interface GBCEStockPriceCalculator {
    BigDecimal calculateDividendYield(GBCEDataDTO gbceDataDTO, BigDecimal stockPrice) throws NoParValueFoundExecption;

    BigDecimal calculatePERatio(int dividend, BigDecimal stockPrice);

    BigDecimal getGeometricMeanForAllStocks(List<BigDecimal> stockPrices);

    default BigDecimal calculateLastDividendForCommon(int lastDividend, BigDecimal price) {
        return BigDecimal.valueOf(lastDividend).divide(price, 5, RoundingMode.FLOOR);
    }

    default BigDecimal calculateLastDividendForPreferred(int fixedDividendInPercentage, int parValue, BigDecimal price) {
        BigDecimal fixedDividedWithParValue = BigDecimal.valueOf(parValue)
                .multiply(BigDecimal.valueOf(fixedDividendInPercentage).divide(BigDecimal.valueOf(100)));
        return fixedDividedWithParValue.divide(price, 5, RoundingMode.FLOOR);
    }

    default BigDecimal getPERatio(int dividend, BigDecimal price) {
        return price.divide(BigDecimal.valueOf(dividend), 5, RoundingMode.FLOOR);
    }
}

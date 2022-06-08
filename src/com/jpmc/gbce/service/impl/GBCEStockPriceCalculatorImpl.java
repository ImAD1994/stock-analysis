package com.jpmc.gbce.service.impl;

import com.jpmc.gbce.constant.StockSymbolType;
import com.jpmc.gbce.dto.GBCEDataDTO;
import com.jpmc.gbce.exception.NoParValueFoundExecption;
import com.jpmc.gbce.service.GBCEStockPriceCalculator;

import java.math.BigDecimal;
import java.util.List;

import static com.jpmc.gbce.constant.GBCEConstant.ROUNDING_MODE;
import static com.jpmc.gbce.constant.GBCEConstant.ROUND_OF_PRECISION;

public class GBCEStockPriceCalculatorImpl implements GBCEStockPriceCalculator {


    /**
     * calculate dividend yield for different stock symbol type
     * @param gbceDataDTO input for calculation
     * @param stockPrice stock price
     * @return dividend yield value
     * @throws NoParValueFoundExecption for missing parValue
     */
    @Override
    public BigDecimal calculateDividendYield(GBCEDataDTO gbceDataDTO, BigDecimal stockPrice) throws NoParValueFoundExecption {
        if (gbceDataDTO.getStockSymbolType().equals(StockSymbolType.COMMON)) {
            return calculateLastDividendForCommon(gbceDataDTO.getLastDividend(), stockPrice);
        }
        if (gbceDataDTO.getStockSymbolType().equals(StockSymbolType.PREFERRED) && gbceDataDTO.getParValue() != 0) {
            return calculateLastDividendForPreferred(gbceDataDTO.getFixedDividend(), gbceDataDTO.getParValue(), stockPrice);
        } else {
            throw new NoParValueFoundExecption("No Par value found for Preferred type");
        }
    }

    @Override
    public BigDecimal calculatePERatio(int dividend, BigDecimal stockPrice) {
        return getPERatio(dividend, stockPrice);
    }

    /**
     * calculate stock geometric mean for list of stocks prices
     * @param stockPrices list of stock prices
     * @return geo metric mean
     */
    @Override
    public BigDecimal getGeometricMeanForAllStocks(List<BigDecimal> stockPrices) {
        return nthRoot(stockPrices.size(), stockPrices.stream()
                .reduce(BigDecimal.ONE, BigDecimal::multiply), BigDecimal.valueOf(.1).movePointLeft(ROUND_OF_PRECISION));
    }

    /**
     *
     * @param n nth root
     * @param a number
     * @param p initial value to calcualte the root
     * @return nth root value
     */
    private BigDecimal nthRoot(final int n, final BigDecimal a, final BigDecimal p) {
        if (a.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("nth root can only be calculated for positive numbers");
        }
        if (a.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        BigDecimal xPrev = a;
        BigDecimal x = a.divide(new BigDecimal(n), ROUND_OF_PRECISION, ROUNDING_MODE);  // starting "guessed" value...
        while (x.subtract(xPrev).abs().compareTo(p) > 0) {
            xPrev = x;
            x = BigDecimal.valueOf(n - 1.0)
                    .multiply(x)
                    .add(a.divide(x.pow(n - 1), ROUND_OF_PRECISION, ROUNDING_MODE))
                    .divide(new BigDecimal(n), ROUND_OF_PRECISION, ROUNDING_MODE);
        }
        return x;
    }

    public static void main(String[] args) {
        GBCEStockPriceCalculatorImpl g=new GBCEStockPriceCalculatorImpl();
        System.out.println(g.nthRoot(3,new BigDecimal(125),BigDecimal.valueOf(.1).movePointLeft(ROUND_OF_PRECISION)));
    }
}

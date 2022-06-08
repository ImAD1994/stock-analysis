package com.jpmc.gbce.test;

import com.jpmc.gbce.constant.StockSymbolType;
import com.jpmc.gbce.dto.GBCEDataDTO;
import com.jpmc.gbce.exception.NoParValueFoundExecption;
import com.jpmc.gbce.service.GBCEStockPriceCalculator;
import com.jpmc.gbce.service.impl.GBCEStockPriceCalculatorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class GBCEStockPriceCalculatorTest {
    private GBCEStockPriceCalculator gbceStockPriceCalculator;

    @Before
    public void setUp(){
        gbceStockPriceCalculator = new GBCEStockPriceCalculatorImpl();
    }

    @Test
    public void calculateDividendYield_whenCommonTypeProvided() throws NoParValueFoundExecption {
        GBCEDataDTO gbceDataDTO = new GBCEDataDTO();
        gbceDataDTO.setStockSymbol("POP");
        gbceDataDTO.setStockSymbolType(StockSymbolType.COMMON);
        gbceDataDTO.setLastDividend(8);
        BigDecimal expectedValue = BigDecimal.valueOf(8).divide(BigDecimal.valueOf(123.34),5, RoundingMode.FLOOR);
        BigDecimal dividendYield = gbceStockPriceCalculator
                .calculateDividendYield(gbceDataDTO, new BigDecimal(123.34));
        Assert.assertEquals(expectedValue,dividendYield);
    }

    @Test
    public void calculateDividendYield_whenPreferredTypeProvided() throws NoParValueFoundExecption {
        GBCEDataDTO gbceDataDTO = new GBCEDataDTO();
        gbceDataDTO.setStockSymbol("GIN");
        gbceDataDTO.setStockSymbolType(StockSymbolType.PREFERRED);
        gbceDataDTO.setFixedDividend(2);
        gbceDataDTO.setParValue(100);
        BigDecimal expectedValue = BigDecimal.valueOf(100)
                .multiply(BigDecimal.valueOf(2))
                .divide(BigDecimal.valueOf(100),5,RoundingMode.FLOOR)
                .divide(BigDecimal.valueOf(123.34),5, RoundingMode.FLOOR);
        BigDecimal dividendYield = gbceStockPriceCalculator
                .calculateDividendYield(gbceDataDTO, new BigDecimal(123.34));
        Assert.assertEquals(expectedValue,dividendYield);
    }

    @Test(expected = NoParValueFoundExecption.class)
    public void throwNoParValueFoundException_whenPreferredTypeProvidedAndParValueAbsent() throws NoParValueFoundExecption {
        GBCEDataDTO gbceDataDTO = new GBCEDataDTO();
        gbceDataDTO.setStockSymbol("GIN");
        gbceDataDTO.setStockSymbolType(StockSymbolType.PREFERRED);
        gbceDataDTO.setFixedDividend(2);
        gbceStockPriceCalculator
                .calculateDividendYield(gbceDataDTO, new BigDecimal(123.34));
    }

    @Test
    public void calculatePE_whenInputProvided(){
        int dividend = 8;
        BigDecimal price = new BigDecimal(125.56);
        BigDecimal expectedValue = price.divide(BigDecimal.valueOf(dividend),5,RoundingMode.FLOOR);
        BigDecimal dividendYield = gbceStockPriceCalculator
                .calculatePERatio(8,price);
        Assert.assertEquals(expectedValue,dividendYield);
    }

    @Test
    public void calculateGeometricMean_whenRequested(){
        BigDecimal geometricMeanForAllStocks = gbceStockPriceCalculator.getGeometricMeanForAllStocks(Arrays.asList(
                new BigDecimal(5), new BigDecimal(5), new BigDecimal(5)
        ));
        Assert.assertEquals(new BigDecimal(5.00000).setScale(5,RoundingMode.FLOOR),geometricMeanForAllStocks);
    }

}

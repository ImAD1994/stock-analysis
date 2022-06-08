package com.jpmc.gbce.test;

import com.jpmc.gbce.constant.StockIndicator;
import com.jpmc.gbce.dto.TradeDTO;
import com.jpmc.gbce.service.GBCETrade;
import com.jpmc.gbce.service.TradeCache;
import com.jpmc.gbce.service.impl.GBCETradeImpl;
import com.jpmc.gbce.service.impl.TradeCacheImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class GBCETradeTest {

    private GBCETrade gbceTrade;

    @Before
    public void setUp(){
        TradeCache tradeCache = new TradeCacheImpl();
        gbceTrade = new GBCETradeImpl(tradeCache);
    }

    @Test
    public void saveTrade_whenTradeDetailsProvided(){
        TradeDTO tradeDTO = getTradeDTO("TEA",12,new BigDecimal(120),StockIndicator.BUY);
        long tradId = gbceTrade.saveTrade(tradeDTO);
        Assert.assertNotEquals(0,tradId);
    }

    @Test
    public void calculateVolumeWeightedStockPriceInLast15Min_whenRequested(){
        List<TradeDTO> tradeDTOList = new ArrayList<>();
        tradeDTOList.add(getTradeDTO("TEA",12,new BigDecimal(120),StockIndicator.BUY));
        tradeDTOList.add(getTradeDTO("POP",10,new BigDecimal(75),StockIndicator.BUY));
        tradeDTOList.add(getTradeDTO("ALE",100,new BigDecimal(123),StockIndicator.SELL));
        tradeDTOList.add(getTradeDTO("GIN",120,new BigDecimal(11),StockIndicator.BUY));
        tradeDTOList.add(getTradeDTO("JOE",24,new BigDecimal(1),StockIndicator.BUY));
        tradeDTOList.forEach(tradeDTO -> {
            gbceTrade.saveTrade(tradeDTO);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        });

        BigDecimal expectedValue = new BigDecimal((12*120)+(10*75)+(100*123)+(120*11)+(24*1)).divide(new BigDecimal(12+10+100+120+24),5,RoundingMode.FLOOR);
        BigDecimal vwsp = gbceTrade.calculateVolumeWeightedStockPriceInLast15Min();
        Assert.assertEquals(expectedValue,vwsp);
    }

    @Test
    public void return0AsVmsp_whenRequestedWithNoTradeForLast15min(){
        BigDecimal vwsp = gbceTrade.calculateVolumeWeightedStockPriceInLast15Min();
        Assert.assertEquals(BigDecimal.ZERO,vwsp);
    }


    private TradeDTO getTradeDTO(String tradeSymbol,int numberOfShares,BigDecimal tradePrice,StockIndicator indicator) {
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setTradePrice(tradePrice);
        tradeDTO.setBuyOrSell(indicator);
        tradeDTO.setNumberOfShare(numberOfShares);
        tradeDTO.setStockSymbol(tradeSymbol);
        return tradeDTO;
    }
}

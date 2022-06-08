package com.jpmc.gbce.util;

import com.jpmc.gbce.constant.StockIndicator;
import com.jpmc.gbce.dto.TradeDTO;
import com.jpmc.gbce.model.Trade;

import java.sql.Timestamp;

public class TradeUtil {

    public static long createTradeId(Trade trade){
        return trade.getTimestamp().getTime();
    }

    public static Trade setTradeDetails(TradeDTO tradeDTO){
        Trade trade = new Trade();
        trade.setTradePrice(tradeDTO.getTradePrice());
        trade.setBuy(tradeDTO.getBuyOrSell().equals(StockIndicator.BUY.toString())?Boolean.TRUE:Boolean.FALSE);
        trade.setNumberOfShare(tradeDTO.getNumberOfShare());
        trade.setTimestamp(new Timestamp(System.currentTimeMillis()));
        trade.setStockSymbol(tradeDTO.getStockSymbol());
        return trade;
    }
}

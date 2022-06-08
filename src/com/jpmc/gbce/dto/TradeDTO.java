package com.jpmc.gbce.dto;

import com.jpmc.gbce.constant.StockIndicator;

import java.math.BigDecimal;

public class TradeDTO {
    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    private String stockSymbol;
    private int numberOfShare;
    private BigDecimal tradePrice;

    public StockIndicator getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(StockIndicator buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    private StockIndicator buyOrSell;



    public int getNumberOfShare() {
        return numberOfShare;
    }

    public void setNumberOfShare(int numberOfShare) {
        this.numberOfShare = numberOfShare;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

}

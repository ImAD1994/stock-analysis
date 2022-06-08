package com.jpmc.gbce.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class Trade {
    private long tradeId;
    private String stockSymbol;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return tradeId == trade.tradeId && numberOfShare == trade.numberOfShare && isBuy == trade.isBuy && stockSymbol.equals(trade.stockSymbol) && tradePrice.equals(trade.tradePrice) && timestamp.equals(trade.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeId, stockSymbol, numberOfShare, tradePrice, isBuy, timestamp);
    }

    private int numberOfShare;
    private BigDecimal tradePrice;
    private boolean isBuy;
    private Timestamp timestamp;



    public long getTradeId() {
        return tradeId;
    }

    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


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

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

}

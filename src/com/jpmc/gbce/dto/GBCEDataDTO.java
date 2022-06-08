package com.jpmc.gbce.dto;

import com.jpmc.gbce.constant.StockSymbolType;

public class GBCEDataDTO {
    private String stockSymbol;
    private StockSymbolType stockSymbolType;
    private int lastDividend;
    private int fixedDividend;
    private int parValue;

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public StockSymbolType getStockSymbolType() {
        return stockSymbolType;
    }

    public void setStockSymbolType(StockSymbolType stockSymbolType) {
        this.stockSymbolType = stockSymbolType;
    }

    public int getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(int lastDividend) {
        this.lastDividend = lastDividend;
    }

    public int getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(int fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public int getParValue() {
        return parValue;
    }

    public void setParValue(int parValue) {
        this.parValue = parValue;
    }

}

package com.jpmc.gbce.service;

import com.jpmc.gbce.model.Trade;

import java.util.List;

public interface TradeCache {

    long saveTrade(Trade trade);
    List<Trade> getAllTradeInLast15min();
}

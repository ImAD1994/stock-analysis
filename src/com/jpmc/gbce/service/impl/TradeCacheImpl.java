package com.jpmc.gbce.service.impl;

import com.jpmc.gbce.model.Trade;
import com.jpmc.gbce.service.TradeCache;
import com.jpmc.gbce.util.TradeUtil;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TradeCacheImpl implements TradeCache {
    private static final Map<Long ,Trade> map = new ConcurrentHashMap<>();

    @Override
    public long saveTrade(Trade trade) {
        trade.setTradeId(TradeUtil.createTradeId(trade));
        map.put(trade.getTradeId(),trade);
        return trade.getTradeId();
    }

    @Override
    public List<Trade> getAllTradeInLast15min() {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
         return map.values()
                .stream()
                .filter(trade -> trade.getTimestamp().getTime()<=currentTimeStamp.getTime())
                .collect(Collectors.toList());
    }

}

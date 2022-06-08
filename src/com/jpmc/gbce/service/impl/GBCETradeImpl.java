package com.jpmc.gbce.service.impl;

import com.jpmc.gbce.dto.TradeDTO;
import com.jpmc.gbce.dto.VolumeWeightedStockPriceDTO;
import com.jpmc.gbce.service.GBCETrade;
import com.jpmc.gbce.service.TradeCache;
import com.jpmc.gbce.util.TradeUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class GBCETradeImpl implements GBCETrade {

    private final TradeCache tradeCache;

    public GBCETradeImpl(TradeCache tradeCache) {
        this.tradeCache = tradeCache;
    }


    @Override
    public long saveTrade(TradeDTO tradeDTO) {
        return tradeCache.saveTrade(TradeUtil.setTradeDetails(tradeDTO));
    }

    /**
     * @return volume weighted stock price for the trades recorded in last 15min
     */
    @Override
    public BigDecimal calculateVolumeWeightedStockPriceInLast15Min() {
        List<VolumeWeightedStockPriceDTO> tradePriceWithQuantityList = tradeCache.getAllTradeInLast15min()
                .stream()
                .map(trade -> new VolumeWeightedStockPriceDTO(trade.getTradePrice()
                    .multiply(BigDecimal.valueOf(trade.getNumberOfShare())),trade.getNumberOfShare()))
                .collect(Collectors.toList());
        if(tradePriceWithQuantityList.size()==0){
            return BigDecimal.ZERO;
        }
        return calculateVolumeWeightedStockPrice(tradePriceWithQuantityList.
                stream()
                .map(VolumeWeightedStockPriceDTO::getTradePriceWithQuantity).collect(Collectors.toList()),
                tradePriceWithQuantityList
                        .stream()
                        .map(price -> price.getQuantity()).collect(Collectors.toList()));
    }
}

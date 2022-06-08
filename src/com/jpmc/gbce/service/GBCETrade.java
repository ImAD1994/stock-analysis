package com.jpmc.gbce.service;

import com.jpmc.gbce.dto.TradeDTO;

import java.math.BigDecimal;
import java.util.List;

import static com.jpmc.gbce.constant.GBCEConstant.ROUNDING_MODE;
import static com.jpmc.gbce.constant.GBCEConstant.ROUND_OF_PRECISION;

public interface GBCETrade {
    long saveTrade(TradeDTO tradeDTO);

    BigDecimal calculateVolumeWeightedStockPriceInLast15Min();

    default BigDecimal calculateVolumeWeightedStockPrice(List<BigDecimal> tradedPriceWithQuantity, List<Integer> quantities) {
        return tradedPriceWithQuantity.stream().reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(quantities
                        .stream()
                        .reduce(0, (subTotal, quantity) -> subTotal + quantity)),ROUND_OF_PRECISION , ROUNDING_MODE);
    }
}

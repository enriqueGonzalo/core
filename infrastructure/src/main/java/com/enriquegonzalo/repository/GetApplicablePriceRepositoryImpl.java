package com.enriquegonzalo.repository;

import com.enriquegonzalo.entity.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetApplicablePriceRepositoryImpl implements GetApplicablePriceRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Price> findPrice(Integer productId, Long brandId, LocalDateTime applicationDate) {

        String sql = "SELECT * FROM PRICES " +
                "WHERE PRODUCT_ID = ? AND BRAND_ID = ? " +
                "AND START_DATE <= ? AND END_DATE >= ? " +
                "ORDER BY PRIORITY DESC " +
                "LIMIT 1";

        try {
            Price price = jdbcTemplate.queryForObject(sql, new Object[]{productId, brandId, applicationDate, applicationDate}, (rs, rowNum) -> {
                Price p = new Price();
                p.setBrandId(rs.getLong("BRAND_ID"));
                p.setStartDate(rs.getTimestamp("START_DATE").toLocalDateTime());
                p.setEndDate(rs.getTimestamp("END_DATE").toLocalDateTime());
                p.setPriceList(rs.getInt("PRICE_LIST"));
                p.setProductId(rs.getInt("PRODUCT_ID"));
                p.setPriority(rs.getInt("PRIORITY"));
                p.setPrice(rs.getBigDecimal("PRICE"));
                p.setCurrency(rs.getString("CURR"));
                return p;
            });

            return Optional.ofNullable(price);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }
}


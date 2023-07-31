package com.epam.esm.util;

public final class CustomQuery {

    public static final String GET_POPULAR_ORDER="SET @customer_id_current = ?;"+
            " set @variable = (SELECT gcht.tag_id"+
            " FROM gift_certificate_has_tag gcht"+
            " join order_item oi on gcht.gift_certificate_id = oi.gift_certificate_id"+
            " join `order` o on o.id = oi.order_id"+
            " WHERE customer_id = @customer_id_current"+
            "         GROUP BY gcht.tag_id"+
            " ORDER BY count(*) DESC"+
            " limit 1);"+
            " SELECT DISTINCT o.*"+
            " from `order` o"+
            " join order_item oi on o.id = oi.order_id"+
            " join gift_certificate_has_tag gcht on oi.gift_certificate_id = gcht.gift_certificate_id"+
            " WHERE customer_id = @customer_id_current"+
            "         and gcht.tag_id = @variable"+
            " ORDER BY o.cost DESC"+
            " limit 1";
}

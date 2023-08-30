package com.epam.esm.util;

public final class CustomQuery {
    public static final String GET_POPULAR_ORDER =
            "    set @customer_id_current=?;\n" +
                    "SELECT distinct o.*\n" +
                    "from `order` o\n" +
                    "join order_item oi on o.id = oi.order_id\n" +
                    "    join gift_certificate_has_tag gcht on oi.gift_certificate_id = gcht.gift_certificate_id\n" +
                    "join (SELECT  gcht.tag_id\n" +
                    "       FROM gift_certificate_has_tag gcht\n" +
                    "                join order_item oi on gcht.gift_certificate_id = oi.gift_certificate_id\n" +
                    "                join `order` o on o.id = oi.order_id\n" +
                    "       WHERE customer_id = @customer_id_current\n" +
                    "       GROUP BY gcht.tag_id\n" +
                    "       ORDER BY count(*) DESC\n" +
                    "       limit 1) sub On gcht.tag_id = sub.tag_id\n" +
                    "WHERE customer_id = ?\n" +
                    "ORDER BY o.cost DESC\n" +
                    "limit 1;";
    public static final String FIND_ALL_GIFT_CERTIFICATE_BY_TAGS_ID_AND_SIZE = "select gc.* from gift_certificate gc\n" +
            "    join gift_certificate_has_tag gcht on gc.id = gcht.gift_certificate_id\n" +
            "where gcht.tag_id in :tags group by gc.id having count(gc.id) = :tagCount";


    private CustomQuery() {
    }
}

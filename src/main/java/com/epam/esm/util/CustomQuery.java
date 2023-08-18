package com.epam.esm.util;

public final class CustomQuery {
    public static final String GET_POPULAR_ORDER=
        "SELECT distinct o.* " +
        "from `order` o " +
        "join order_item oi on o.id = oi.order_id\n" +
        "    join gift_certificate_tag gct on oi.gift_certificate_id = gct.gift_certificate_id\n" +
        "join (SELECT  gct.tag_id\n" +
        "       FROM gift_certificate_tag gct\n" +
        "                join order_item oi on gct.gift_certificate_id = oi.gift_certificate_id\n" +
        "                join `order` o on o.id = oi.order_id\n" +
        "       WHERE customer_id = :customerId\n" +
        "       GROUP BY gct.tag_id\n" +
        "       ORDER BY count(*) DESC\n" +
        "       limit 1) sub On gct.tag_id = sub.tag_id\n" +
        "WHERE customer_id =:customerId\n" +
        "ORDER BY o.cost DESC\n" +
        "limit 1";


  public static final String FIND_ALL_GIFT_CERTIFICATE_BY_TAGS_ID_AND_SIZE  = "select gc.* from gift_certificate gc\n" +
            "    join gift_certificate_has_tag gcht on gc.id = gcht.gift_certificate_id\n" +
            "where gcht.tag_id in :tagsId group by gc.id having count(gc.id) = :tagsCount";
}

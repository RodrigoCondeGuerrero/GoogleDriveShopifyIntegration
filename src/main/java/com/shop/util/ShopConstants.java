package com.shop.util;

import java.util.HashMap;

public final class ShopConstants
{
    public static final HashMap<String, String> COLLECTIONS;
    public static final HashMap<String, String> PRODUCT_CODES_TYPES;

    static
    {
        COLLECTIONS = new HashMap<>();
        PRODUCT_CODES_TYPES = new HashMap<>();

        COLLECTIONS.put("YOUR_PRODUCT_TYPE_DESCRIPTION_1", "YOUR_COLLECTION_ID_1");
        COLLECTIONS.put("YOUR_PRODUCT_TYPE_DESCRIPTION_2", "YOUR_COLLECTION_ID_1");

/*      Here an example of 2 products types, each one with 2 different kind of items.
        For example YOUR_PRODUCT_TYPE_CODE:
            * GB -> for Gameboards
            * ET -> for Electronic toys
        And YOUR_PRODUCT_TYPE_DESCRIPTION;
            * Chess
            * Cards
            * PlayStation
            * XBOX
*/
        PRODUCT_CODES_TYPES.put("YOUR_PRODUCT_TYPE_CODE_1", "YOUR_PRODUCT_TYPE_DESCRIPTION_1");
        PRODUCT_CODES_TYPES.put("YOUR_PRODUCT_TYPE_CODE_1", "YOUR_PRODUCT_TYPE_DESCRIPTION_2");
        PRODUCT_CODES_TYPES.put("YOUR_PRODUCT_TYPE_CODE_2", "YOUR_PRODUCT_TYPE_DESCRIPTION_1");
        PRODUCT_CODES_TYPES.put("YOUR_PRODUCT_TYPE_CODE_2", "YOUR_PRODUCT_TYPE_DESCRIPTION_2");
    }
}

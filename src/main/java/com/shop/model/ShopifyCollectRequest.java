package com.shop.model;

public class ShopifyCollectRequest
{
    public Collect collect;

    public ShopifyCollectRequest()
    {
    }

    public Collect getCollect()
    {
        return collect;
    }

    public void setCollect(Collect collect)
    {
        this.collect = collect;
    }

    public static class Collect
    {
        public String product_id;
        public String collection_id;

        public Collect()
        {
        }

        public Collect(String product_id, String collection_id)
        {
            this.product_id = product_id;
            this.collection_id = collection_id;
        }

        public String getProduct_id()
        {
            return product_id;
        }

        public void setProduct_id(String product_id)
        {
            this.product_id = product_id;
        }

        public String getCollection_id()
        {
            return collection_id;
        }

        public void setCollection_id(String collection_id)
        {
            this.collection_id = collection_id;
        }
    }
}

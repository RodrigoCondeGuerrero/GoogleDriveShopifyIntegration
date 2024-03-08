package com.shop.model;

public class ShopifyProductCreation
{
    Product product;

    public ShopifyProductCreation()
    {
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public static class Product
    {
        public String title;
        public String body_html;
        public String vendor;
        public String product_type;
        public String status;

        public Product()
        {
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public String getBody_html()
        {
            return body_html;
        }

        public void setBody_html(String body_html)
        {
            this.body_html = body_html;
        }

        public String getVendor()
        {
            return vendor;
        }

        public void setVendor(String vendor)
        {
            this.vendor = vendor;
        }

        public String getProduct_type()
        {
            return product_type;
        }

        public void setProduct_type(String product_type)
        {
            this.product_type = product_type;
        }

        public String getStatus()
        {
            return status;
        }

        public void setStatus(String status)
        {
            this.status = status;
        }
    }
}
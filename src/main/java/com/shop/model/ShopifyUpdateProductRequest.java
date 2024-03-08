package com.shop.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShopifyUpdateProductRequest
{
    Product product;

    public ShopifyUpdateProductRequest()
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
        private String id;
        private String title;
        private String body_html;
        private ArrayList<Variant> variants;
        private ArrayList<Image> images;
        private String tags;

        public Product()
        {
        }

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
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

        public ArrayList<Variant> getVariants()
        {
            return variants;
        }

        public void setVariants(ArrayList<Variant> variants)
        {
            this.variants = variants;
        }

        public ArrayList<Image> getImages()
        {
            return images;
        }

        public void setImages(ArrayList<Image> images)
        {
            this.images = images;
        }

        public String getTags()
        {
            return tags;
        }

        public void setTags(String tags)
        {
            this.tags = tags;
        }
    }

    public static class Variant
    {
        public BigDecimal price;
        public String sku;
        public double weight;
        public String weight_unit;
        public int inventory_quantity;

        public Variant()
        {
        }

        public BigDecimal getPrice()
        {
            return price;
        }

        public void setPrice(BigDecimal price)
        {
            this.price = price;
        }

        public String getSku()
        {
            return sku;
        }

        public void setSku(String sku)
        {
            this.sku = sku;
        }

        public double getWeight()
        {
            return weight;
        }

        public void setWeight(double weight)
        {
            this.weight = weight;
        }

        public String getWeight_unit()
        {
            return weight_unit;
        }

        public void setWeight_unit(String weight_unit)
        {
            this.weight_unit = weight_unit;
        }

        public int getInventory_quantity()
        {
            return inventory_quantity;
        }

        public void setInventory_quantity(int inventory_quantity)
        {
            this.inventory_quantity = inventory_quantity;
        }
    }

    public static class Image
    {
        public String src;
        public int position;
        public String alt;

        public Image()
        {
        }

        public String getSrc()
        {
            return src;
        }

        public void setSrc(String src)
        {
            this.src = src;
        }

        public int getPosition()
        {
            return position;
        }

        public void setPosition(int position)
        {
            this.position = position;
        }

        public String getAlt()
        {
            return alt;
        }

        public void setAlt(String alt)
        {
            this.alt = alt;
        }
    }
}

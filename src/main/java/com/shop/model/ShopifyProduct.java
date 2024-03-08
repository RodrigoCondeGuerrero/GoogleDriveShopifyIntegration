package com.shop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class ShopifyProduct
{
    private String id;
    private String title;
    private String body_html;
    private String vendor;
    private String product_type;
    private Date created_at;
    private String handle;
    private Date updated_at;
    private Date published_at;
    private String template_suffix;
    private String published_scope;
    private String tags;
    private String status;
    private String admin_graphql_api_id;
    private ArrayList<Variant> variants;
    private ArrayList<Option> options;
    private ArrayList<Image> images;
    private Image image;

    public ShopifyProduct()
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

    public Date getCreated_at()
    {
        return created_at;
    }

    public void setCreated_at(Date created_at)
    {
        this.created_at = created_at;
    }

    public String getHandle()
    {
        return handle;
    }

    public void setHandle(String handle)
    {
        this.handle = handle;
    }

    public Date getUpdated_at()
    {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at)
    {
        this.updated_at = updated_at;
    }

    public Date getPublished_at()
    {
        return published_at;
    }

    public void setPublished_at(Date published_at)
    {
        this.published_at = published_at;
    }

    public String getTemplate_suffix()
    {
        return template_suffix;
    }

    public void setTemplate_suffix(String template_suffix)
    {
        this.template_suffix = template_suffix;
    }

    public String getPublished_scope()
    {
        return published_scope;
    }

    public void setPublished_scope(String published_scope)
    {
        this.published_scope = published_scope;
    }

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getAdmin_graphql_api_id()
    {
        return admin_graphql_api_id;
    }

    public void setAdmin_graphql_api_id(String admin_graphql_api_id)
    {
        this.admin_graphql_api_id = admin_graphql_api_id;
    }

    public ArrayList<Variant> getVariants()
    {
        return variants;
    }

    public void setVariants(ArrayList<Variant> variants)
    {
        this.variants = variants;
    }

    public ArrayList<Option> getOptions()
    {
        return options;
    }

    public void setOptions(ArrayList<Option> options)
    {
        this.options = options;
    }

    public ArrayList<Image> getImages()
    {
        return images;
    }

    public void setImages(ArrayList<Image> images)
    {
        this.images = images;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public static class Image
    {
        public String id;
        public String product_id;
        public int position;
        public Date created_at;
        public Date updated_at;
        public String alt;
        public int width;
        public int height;
        public String src;
        public ArrayList<String> variant_ids;
        public String admin_graphql_api_id;

        public Image()
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

        public String getProduct_id()
        {
            return product_id;
        }

        public void setProduct_id(String product_id)
        {
            this.product_id = product_id;
        }

        public int getPosition()
        {
            return position;
        }

        public void setPosition(int position)
        {
            this.position = position;
        }

        public Date getCreated_at()
        {
            return created_at;
        }

        public void setCreated_at(Date created_at)
        {
            this.created_at = created_at;
        }

        public Date getUpdated_at()
        {
            return updated_at;
        }

        public void setUpdated_at(Date updated_at)
        {
            this.updated_at = updated_at;
        }

        public String getAlt()
        {
            return alt;
        }

        public void setAlt(String alt)
        {
            this.alt = alt;
        }

        public int getWidth()
        {
            return width;
        }

        public void setWidth(int width)
        {
            this.width = width;
        }

        public int getHeight()
        {
            return height;
        }

        public void setHeight(int height)
        {
            this.height = height;
        }

        public String getSrc()
        {
            return src;
        }

        public void setSrc(String src)
        {
            this.src = src;
        }

        public ArrayList<String> getVariant_ids()
        {
            return variant_ids;
        }

        public void setVariant_ids(ArrayList<String> variant_ids)
        {
            this.variant_ids = variant_ids;
        }

        public String getAdmin_graphql_api_id()
        {
            return admin_graphql_api_id;
        }

        public void setAdmin_graphql_api_id(String admin_graphql_api_id)
        {
            this.admin_graphql_api_id = admin_graphql_api_id;
        }
    }

    public static class Variant
    {
        private String id;
        private String product_id;
        private String title;
        private BigDecimal price;
        private String sku;
        private int position;
        private String inventory_policy;
        private String compare_at_price;
        private String fulfillment_service;
        private String inventory_management;
        private String option1;
        private String option2;
        private String option3;
        private Date created_at;
        private Date updated_at;
        private boolean taxable;
        private String barcode;
        private double grams;
        private String image_id;
        private double weight;
        private String weight_unit;
        private String inventory_item_id;
        private int inventory_quantity;
        private int old_inventory_quantity;
        private ArrayList<PresentmentPrice> presentment_prices;
        private boolean requires_shipping;
        private String admin_graphql_api_id;

        public Variant()
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

        public String getProduct_id()
        {
            return product_id;
        }

        public void setProduct_id(String product_id)
        {
            this.product_id = product_id;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
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

        public int getPosition()
        {
            return position;
        }

        public void setPosition(int position)
        {
            this.position = position;
        }

        public String getInventory_policy()
        {
            return inventory_policy;
        }

        public void setInventory_policy(String inventory_policy)
        {
            this.inventory_policy = inventory_policy;
        }

        public String getCompare_at_price()
        {
            return compare_at_price;
        }

        public void setCompare_at_price(String compare_at_price)
        {
            this.compare_at_price = compare_at_price;
        }

        public String getFulfillment_service()
        {
            return fulfillment_service;
        }

        public void setFulfillment_service(String fulfillment_service)
        {
            this.fulfillment_service = fulfillment_service;
        }

        public String getInventory_management()
        {
            return inventory_management;
        }

        public void setInventory_management(String inventory_management)
        {
            this.inventory_management = inventory_management;
        }

        public String getOption1()
        {
            return option1;
        }

        public void setOption1(String option1)
        {
            this.option1 = option1;
        }

        public String getOption2()
        {
            return option2;
        }

        public void setOption2(String option2)
        {
            this.option2 = option2;
        }

        public String getOption3()
        {
            return option3;
        }

        public void setOption3(String option3)
        {
            this.option3 = option3;
        }

        public Date getCreated_at()
        {
            return created_at;
        }

        public void setCreated_at(Date created_at)
        {
            this.created_at = created_at;
        }

        public Date getUpdated_at()
        {
            return updated_at;
        }

        public void setUpdated_at(Date updated_at)
        {
            this.updated_at = updated_at;
        }

        public boolean isTaxable()
        {
            return taxable;
        }

        public void setTaxable(boolean taxable)
        {
            this.taxable = taxable;
        }

        public String getBarcode()
        {
            return barcode;
        }

        public void setBarcode(String barcode)
        {
            this.barcode = barcode;
        }

        public double getGrams()
        {
            return grams;
        }

        public void setGrams(int grams)
        {
            this.grams = grams;
        }

        public String getImage_id()
        {
            return image_id;
        }

        public void setImage_id(String image_id)
        {
            this.image_id = image_id;
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

        public String getInventory_item_id()
        {
            return inventory_item_id;
        }

        public void setInventory_item_id(String inventory_item_id)
        {
            this.inventory_item_id = inventory_item_id;
        }

        public int getInventory_quantity()
        {
            return inventory_quantity;
        }

        public void setInventory_quantity(int inventory_quantity)
        {
            this.inventory_quantity = inventory_quantity;
        }

        public int getOld_inventory_quantity()
        {
            return old_inventory_quantity;
        }

        public void setOld_inventory_quantity(int old_inventory_quantity)
        {
            this.old_inventory_quantity = old_inventory_quantity;
        }

        public ArrayList<PresentmentPrice> getPresentment_prices()
        {
            return presentment_prices;
        }

        public void setPresentment_prices(ArrayList<PresentmentPrice> presentment_prices)
        {
            this.presentment_prices = presentment_prices;
        }

        public boolean isRequires_shipping()
        {
            return requires_shipping;
        }

        public void setRequires_shipping(boolean requires_shipping)
        {
            this.requires_shipping = requires_shipping;
        }

        public String getAdmin_graphql_api_id()
        {
            return admin_graphql_api_id;
        }

        public void setAdmin_graphql_api_id(String admin_graphql_api_id)
        {
            this.admin_graphql_api_id = admin_graphql_api_id;
        }

        public class PresentmentPrice
        {
            private Price price;
            private String compare_at_price;

            public PresentmentPrice()
            {
            }

            public Price getPrice()
            {
                return price;
            }

            public void setPrice(Price price)
            {
                this.price = price;
            }

            public String getCompare_at_price()
            {
                return compare_at_price;
            }

            public void setCompare_at_price(String compare_at_price)
            {
                this.compare_at_price = compare_at_price;
            }

            public static class Price
            {
                private BigDecimal amount;
                private String currency_code;

                public Price()
                {
                }

                public BigDecimal getAmount()
                {
                    return amount;
                }

                public void setAmount(BigDecimal amount)
                {
                    this.amount = amount;
                }

                public String getCurrency_code()
                {
                    return currency_code;
                }

                public void setCurrency_code(String currency_code)
                {
                    this.currency_code = currency_code;
                }
            }
        }
    }

    public static class Option
    {
        private String id;
        private String product_id;
        private String name;
        private int position;
        private ArrayList<String> values;

        public Option()
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

        public String getProduct_id()
        {
            return product_id;
        }

        public void setProduct_id(String product_id)
        {
            this.product_id = product_id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public int getPosition()
        {
            return position;
        }

        public void setPosition(int position)
        {
            this.position = position;
        }

        public ArrayList<String> getValues()
        {
            return values;
        }

        public void setValues(ArrayList<String> values)
        {
            this.values = values;
        }
    }
}
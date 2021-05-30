package org.mentor.repository;

public class Product {
    private Integer id;
    private String desc;
    private Double price;
    private Integer stored;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStored() {
        return stored;
    }

    public void setStored(Integer stored) {
        this.stored = stored;
    }
}

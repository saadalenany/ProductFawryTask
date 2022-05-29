package com.saad.spring.fawry.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name="code")
    private String code;

    @Column(name="name_ar")
    private String nameAr;

    @Column(name="name_en")
    private String nameEn;

    @Column(name="price")
    private Double price;

    @Column(name="category_ar")
    private String categoryAr;

    @Column(name="category_en")
    private String categoryEn;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="active")
    private Boolean active;

    public Product() {
    }

    public Product(String code, String nameAr, String nameEn, Double price, String categoryAr, String categoryEn, Integer quantity, Boolean active) {
        this.code = code;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.price = price;
        this.categoryAr = categoryAr;
        this.categoryEn = categoryEn;
        this.quantity = quantity;
        this.active = active;
    }

    public Product(String id, String code, String nameAr, String nameEn, Double price, String categoryAr, String categoryEn, Integer quantity, Boolean active) {
        this.id = id;
        this.code = code;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.price = price;
        this.categoryAr = categoryAr;
        this.categoryEn = categoryEn;
        this.quantity = quantity;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryAr() {
        return categoryAr;
    }

    public void setCategoryAr(String categoryAr) {
        this.categoryAr = categoryAr;
    }

    public String getCategoryEn() {
        return categoryEn;
    }

    public void setCategoryEn(String categoryEn) {
        this.categoryEn = categoryEn;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", price=" + price +
                ", categoryAr='" + categoryAr + '\'' +
                ", categoryEn='" + categoryEn + '\'' +
                ", quantity=" + quantity +
                ", active=" + active +
                '}';
    }
}

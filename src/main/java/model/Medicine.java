package model;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "medicine_new", indexes = @Index(name = "idx_name", columnList = "name"))
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "is_discontinued")
    private Boolean isDiscontinued;

    @Column(name = "manufacturer_name", length = 255)
    private String manufacturerName;

    @Column(name = "type", length = 255)
    private String type;

    @Column(name = "pack_size_label", length = 255)
    private String packSizeLabel;

    @Column(name = "short_composition1", length = 255)
    private String shortComposition1;

    @Column(name = "short_composition2", length = 255)
    private String shortComposition2;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsDiscontinued() {
        return isDiscontinued;
    }

    public void setIsDiscontinued(Boolean isDiscontinued) {
        this.isDiscontinued = isDiscontinued;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPackSizeLabel() {
        return packSizeLabel;
    }

    public void setPackSizeLabel(String packSizeLabel) {
        this.packSizeLabel = packSizeLabel;
    }

    public String getShortComposition1() {
        return shortComposition1;
    }

    public void setShortComposition1(String shortComposition1) {
        this.shortComposition1 = shortComposition1;
    }

    public String getShortComposition2() {
        return shortComposition2;
    }

    public void setShortComposition2(String shortComposition2) {
        this.shortComposition2 = shortComposition2;
    }
}

package com.jt.common.po;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_cart")
public class TbCart extends BasePojo {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long userId;
  private Long itemId;
  private String itemTitle;
  private String itemImage;
  private Long itemPrice;
  private Long num;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public String getItemTitle() {
    return itemTitle;
  }

  public void setItemTitle(String itemTitle) {
    this.itemTitle = itemTitle;
  }

  public String getItemImage() {
    return itemImage;
  }

  public void setItemImage(String itemImage) {
    this.itemImage = itemImage;
  }

  public Long getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(Long itemPrice) {
    this.itemPrice = itemPrice;
  }

  public Long getNum() {
    return num;
  }

  public void setNum(Long num) {
    this.num = num;
  }

  @Override
  public String toString() {
    return "TbCart{" +
            "id=" + id +
            ", userId=" + userId +
            ", itemId=" + itemId +
            ", itemTitle='" + itemTitle + '\'' +
            ", itemImage='" + itemImage + '\'' +
            ", itemPrice=" + itemPrice +
            ", num=" + num +
            '}';
  }
}

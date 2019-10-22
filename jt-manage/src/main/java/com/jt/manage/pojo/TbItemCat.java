package com.jt.manage.pojo;

import com.jt.common.po.BasePojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_item_cat")
public class TbItemCat extends BasePojo {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;       //商品分类id号
  private Long parentId; //商品分级父级ID
  private String name;   //商品分类名称
  private Integer status;   //商品分类状态 1正常 2 删除
  private Integer sortOrder; //排序号
  private Boolean isParent;  //是否为父级

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
  }

  public Boolean getParent() {
    return isParent;
  }

  public void setParent(Boolean parent) {
    isParent = parent;
  }
}

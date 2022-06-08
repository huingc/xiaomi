package com.huing.pojo.vo;

/**
 * 多条件查询（查询条件类）
 * @author huing
 * @create 2022-06-08 15:54
 */
public class ProductInfoVo {

    //商品名称
    private String pname;
    //类型
    private Integer typeid;
    //最低价
    private Integer lprice;
    //最高价
    private Integer hprice;
    //设置页码
    private Integer page;

    @Override
    public String toString() {
        return "ProductInfoVo{" +
                "pname='" + pname + '\'' +
                ", typeid=" + typeid +
                ", lprice=" + lprice +
                ", hprice=" + hprice +
                ", page=" + page +
                '}';
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getLprice() {
        return lprice;
    }

    public void setLprice(Integer lprice) {
        this.lprice = lprice;
    }

    public Integer getHprice() {
        return hprice;
    }

    public void setHprice(Integer hprice) {
        this.hprice = hprice;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ProductInfoVo() {
    }

    public ProductInfoVo(String pname, Integer typeid, Integer lprice, Integer hprice, Integer page) {
        this.pname = pname;
        this.typeid = typeid;
        this.lprice = lprice;
        this.hprice = hprice;
        this.page = page;
    }
}

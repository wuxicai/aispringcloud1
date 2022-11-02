package com.southwind.domain;



import javax.persistence.*;

@Entity
@Table(name = "cst_linkman")
public class LinkMan {

    @ManyToOne(targetEntity = Customer.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lkm_id")
    private long lkmId;
    @Column(name = "lkm_name")
    private String lkmName;
    @Column(name = "lkm_phone")
    private String limPhone;

    public long getLkmId() {
        return lkmId;
    }

    public void setLkmId(long lkmId) {
        this.lkmId = lkmId;
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLimPhone() {
        return limPhone;
    }

    public void setLimPhone(String limPhone) {
        this.limPhone = limPhone;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "lkmId=" + lkmId +
                ", lkmName='" + lkmName + '\'' +
                ", limPhone='" + limPhone + '\'' +
                '}';
    }
}

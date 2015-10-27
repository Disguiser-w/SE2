package edu.nju.express.po;

import java.io.Serializable;

public class CustomerPO implements Serializable {

    private static final long serialVersionUID = 2044100062155888758L;

    private int customerId;
    private String name;
    private String address;
    private String company;
    private String telephone;
    private String mobile;

    public CustomerPO(String name, String address, String company, String telephone, String mobile) {
        this.name = name;
        this.address = address;
        this.company = company;
        this.telephone = telephone;
        this.mobile = mobile;
    }

    public void setId(int id) {
        this.customerId = id;
    }

    public int getId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCompany() {
        return company;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CustomerPO)) {
            return false;
        }

        CustomerPO another = (CustomerPO) o;
        return this.customerId == another.customerId;
    }

    @Override
    public int hashCode() {
        return new Integer(customerId).hashCode();
    }

}

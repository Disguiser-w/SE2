package edu.nju.express.vo;

import java.io.Serializable;

public class CustomerVO implements Serializable {
    private static final long serialVersionUID = -8880507608383302113L;

    public final String name;
    public final String address;
    public final String company;
    public final String telephone;
    public final String mobile;

    public CustomerVO(String name, String address, String company, String telephone, String mobile) {
        this.name = name;
        this.address = address;
        this.company = company;
        this.telephone = telephone;
        this.mobile = mobile;
    }

}

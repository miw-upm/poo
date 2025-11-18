package upm.app2.data.models;

import upm.app2.data.models.exceptions.InvalidAttributeException;

public class User extends Entity {
    public static final int MAX_MOBILE = 999_999_999;
    public static final int MIN_MOBILE = 100_000_000;
    private Integer mobile;
    private String name;
    private String address;

    public User(Integer mobile, String name, String address) {
        this.setMobile(mobile);
        this.name = name;
        this.address = address;
    }

    public User() {
        this(null, null, null);
    }

    public Integer getMobile() {
        return this.mobile;
    }

    public void setMobile(Integer mobile) {
        if (mobile > MAX_MOBILE || mobile < MIN_MOBILE) {
            throw new InvalidAttributeException("El móvil debe tener 9 digitos y debe ser positivo: " + mobile);
        }
        this.mobile = mobile;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "mobile=" + mobile +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                "} " + super.toString();
    }
}

package upm.appentrega2.data.models;

import java.util.Objects;

public class User {
    private Integer id;
    private Integer mobile;
    private String name;
    private String address;

    public User(Integer mobile, String name, String address) {
        this.setMobile(mobile);
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        if (mobile > 999_999_999 || mobile < 100_000_000) {
            throw new RuntimeException("El móvil debe tener 9 digitos y debe ser positivo: " + mobile);
        }
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mobile=" + mobile +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object user) {
        return this == user || user != null && getClass() == user.getClass() && (this.id.equals(((User) user).id));
    }
}

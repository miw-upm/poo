package upm.appentrega3.data.models;

public class User extends Entity {
    private Integer mobile;
    private Rol rol;
    private String address;
    private String password;
    private String name;

    public User(Integer mobile, String password, String name, String address) {
        this.setMobile(mobile);
        this.rol = Rol.CUSTOMER;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        if (mobile > 999_999_999 || mobile < 100_000_000) {
            throw new InvalidAttributeException("El móvil debe tener 9 digitos y debe ser positivo: " + mobile);
        }
        this.mobile = mobile;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "mobile=" + mobile +
                ", rol=" + rol +
                ", address='" + address + '\'' +
                ", password='***'" +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
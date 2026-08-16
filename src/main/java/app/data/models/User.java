package app.data.models;
import java.util.Objects;

public class User {
    private static final int MIN_AGE = 0;
    private Long id;       // El servicio-repositorio asigna la id
    private String email;  // Obligatorio y único
    private String name;   // Obligatorio
    private Integer age;   // Opcional
    private Role role;     // Opcional, por defecto Role.OPERATOR

    public User(String email, String name) {
        this(email, name, null, null);
    }

    public User(String email, String name, Integer age, Role role) {
        this.setEmail(email);
        this.setName(name);
        this.setAge(age);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email inválido: " + email);
        }
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name es obligatorio");
        }
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        if (age != null && age < MIN_AGE) {
            throw new IllegalArgumentException("edad negativa: " + age);
        }
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        // TODO: igualdad por id, se genera automaticamente por IntelliJ o IA
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public String toString() {
        return "User{id=" + this.id + ", email=" + this.email + ", name=" + this.name + ", age=" + this.age + ", role=" + this.role + '}';
    }
}

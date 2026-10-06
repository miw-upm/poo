package es.upm.poo.data.model;

import java.util.Objects;

public class User {
    private static final int MIN_AGE = 0;
    private static final Boolean DEFAULT_ACTIVE = true;

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private Boolean active;

    public User(String name, String email, Integer age, Boolean active) {
        this.setEmail(email);
        this.setName(name);
        this.setAge(age);
        this.setActive(active);
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
        if (email != null && !email.contains("@")) {
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

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = Objects.requireNonNullElse(active, DEFAULT_ACTIVE);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof User user)) {
            return false;
        }
        return this.id != null && this.id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public String toString() {
        return "User{id=" + this.id + ", name=" + this.name + ", email=" + this.email  + ", age=" + this.age + ", active=" + this.active + '}';
    }
}

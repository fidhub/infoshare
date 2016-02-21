package infoshare.domain;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/06/24.
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String description;
    private String state;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    private Role() {

    }

    private Role(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.state = builder.state;
    }

    public static class Builder{
        private String id;
        private String name;
        private String description;
        private String state;

        public Builder state(String value) {
            this.state = value;
            return this;
        }

        public Builder id(String value){
            this.id = value;
            return this;
        }
        public Builder roleName(String value){
            this.name = value;
            return this;
        }
        public Builder description(String value){
            this.description = value;
            return this;
        }

        public Builder copy(Role value) {
            this.id = value.id;
            this.name = value.name;
            this.description = value.description;
            this.state = value.state;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (getId() != null ? !getId().equals(role.getId()) : role.getId() != null) return false;
        if (getName() != null ? !getName().equals(role.getName()) : role.getName() != null)
            return false;
        return !(getDescription() != null ? !getDescription().equals(role.getDescription()) : role.getDescription() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}

package icom.khiloyaimokhai.springsecurityjwt.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by Ikhiloya Imokhai on 2019-10-14.
 */
@Entity
@Table(name = "AUTHORITY")
public class Authority {

    @Id
    @Column(name = "NAME", length = 50)
    @NotNull
    private String name;

    public Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return name == authority.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "name=" + name +
                '}';
    }
}

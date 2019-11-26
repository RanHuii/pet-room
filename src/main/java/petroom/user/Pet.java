package petroom.user;

import petroom.model.NamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "pets")
public class Pet extends NamedEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotEmpty
    private User user;

    @Column(columnDefinition = "integer default 0")
    @NotEmpty
    private Integer age;

    @Column(columnDefinition = "String default male")
    @NotEmpty
    private String sex;


    public void setAge(Integer age) { this.age = age; }

    public Integer getAge() { return age; }

    public void setSex(String sex) { this.sex = sex; }

    public String getSex() { return sex; }

    public User getUser() { return user; }

    public void setUser(User user){ this.user = user; }
}

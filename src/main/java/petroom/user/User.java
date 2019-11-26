package petroom.user;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import petroom.model.Person;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.*;


@Entity
@Table(name = "users")
public class User extends Person {

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "userName")
    @NotEmpty
    private String username;

    @Column(name = "password", columnDefinition = "String default 0000")
    @NotEmpty
    private String password;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Pet> pets;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Pet> getPetsInternal() {
        if (this.pets == null) {
            this.pets = new HashSet<>();
        }
        return this.pets;
    }

    public void setPetsInternal(Set<Pet> pets) {
        this.pets = pets;
    }

    public List<Pet> getPets() {
        List<Pet> sortedPets = new ArrayList<>(getPetsInternal());
        PropertyComparator.sort(sortedPets, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedPets);
    }

    public void addPet(Pet pet) {
        if (this.isNew(pet)) {
            getPetsInternal().add((pet));
        }
        pet.setUser(this);
    }


    public boolean isNew(Pet pet) {
        for (Pet petty : pets) {
            if (pet.getId() == petty.getId())
                return false;
        }
        return true;
    }
}

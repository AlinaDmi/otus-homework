package crm.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client implements Cloneable {

    @Id
    @SequenceGenerator(name = "client_gen", sequenceName = "client_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true)
    private List<Phone> phones;

    public Client(String name) {
        this.id = null;
        this.name = name;
    }

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Client(Long id, String name, Address address, List<Phone> phones) {
        this.name = name;
        this.id = id;
        this.address = address != null ? address.copy() : address;
        this.phones = new ArrayList<>(phones);
        initializePhonesClientRelationship();
        initializeAddressClientRelationship();
    }


    // Конструкторы, геттеры, сеттеры и другие методы

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    @SuppressWarnings({"java:S2975", "java:S1182"})
    public Client clone() {
        Client clonedClient = new Client(this.id, this.name, this.address, new ArrayList<>());

        // Клонируем телефоны и устанавливаем ссылку на новый объект Client
        for (Phone phone : this.phones) {
            Phone clonedPhone = new Phone(phone.getId(), phone.getNumber());
            clonedPhone.setClient(clonedClient);
            clonedClient.getPhones().add(clonedPhone);
        }

        return clonedClient;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name='" + name + '\'' + ", address=" + address + ", phones=" + phones + '}';
    }

    private void initializePhonesClientRelationship() {
        if (phones != null) {
            phones.forEach(phone -> phone.setClient(this));
        }
    }

    private void initializeAddressClientRelationship() {
        if (address != null) {
            address.setClient(this);
        }
    }
}

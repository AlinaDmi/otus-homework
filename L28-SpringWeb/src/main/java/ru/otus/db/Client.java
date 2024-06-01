package ru.otus.db;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("client")
@Getter
@Setter
public class Client {

    @Id
    private Long id;

    @Nonnull
    private final String name;

    @MappedCollection(idColumn = "client_id")
    private Address address;


    public Client(String name, Address address) {
        this(null, name, address);
    }

    @PersistenceCreator
    public Client(Long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

        return new Client(this.id, this.name, this.address);
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name='" + name + '\'' + ", address=" + address + '}';
    }

    private void initializeAddressClientRelationship() {
        if (address != null) {
            address.setClientId(this.id);
        }
    }
}

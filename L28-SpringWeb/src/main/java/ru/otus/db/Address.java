package ru.otus.db;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table("address")
@Getter
@Setter
public class Address {
    @Id
    private Long clientId;

    @Nonnull
    private String street;

    public Address(Long clientId, String street) {
        this.clientId = clientId;
        this.street = street;
    }

    public Address copy() {
        return new Address(this.clientId, this.street);
    }
}

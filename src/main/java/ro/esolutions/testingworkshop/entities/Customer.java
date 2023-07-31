package ro.esolutions.testingworkshop.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        LOYAL,
        DISLOYAL, 
        NEW,
        VETERAN
    }

}

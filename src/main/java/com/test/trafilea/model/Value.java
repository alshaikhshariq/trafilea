package com.test.trafilea.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Map;

@Entity
@Table(name="'value'")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ElementCollection
    @CollectionTable(name = "value_attributes", joinColumns = @JoinColumn(name = "value_id"))
    @MapKeyColumn(name = "attribute_key")
    @Column(name = "attribute_value")
    Map<Integer, String> values;
}

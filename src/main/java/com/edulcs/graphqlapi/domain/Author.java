package com.edulcs.graphqlapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    // @TODO GraphQL can not lazily load relational data !!!
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonManagedReference(value="auth-books")
    private Set<Book> books = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("authors")
    @JsonBackReference(value="pub-author")
    private Publisher publisher;

    /**
     * No-arg constructor
     */
    public Author() {
        super();
    }

    /**
     * Constructor that takes name of it
     *
     * @param name
     */
    public Author(@NotNull String name) {
        super();
        this.name = name;
    }

}

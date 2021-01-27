package com.edulcs.graphqlapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "i_sbn", nullable = false)
    private String ISBN;

    @ManyToOne
    @JsonIgnoreProperties("books")
    @JsonBackReference(value="auth-books")
    private Author author;

    @ManyToOne
    @JsonIgnoreProperties("books")
    @JsonBackReference(value="pub-books")
    private Publisher publisher;

    public Book() {
        super();
    }

    public Book(@NotNull String title, @NotNull String ISBN) {
        super();
        this.title = title;
        this.ISBN = ISBN;
    }

}

package com.maiviet.redisdemo.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Cache(region = "books", usage = CacheConcurrencyStrategy.READ_WRITE) // hibernate cache
@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity extends AuditModel {

    @Id
    @GeneratedValue(generator = "book_generator")
    @SequenceGenerator(
            name = "book_generator",
            sequenceName = "book_sequence",
            initialValue = 1000
    )
    @Column(columnDefinition = "id")
    private Long id;

    @Column(columnDefinition = "title")
    private String title;
}

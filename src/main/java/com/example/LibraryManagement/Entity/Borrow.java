package com.example.LibraryManagement.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnoreProperties({"borrows"})
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"borrows"})
    private Customer customer;

    private LocalDate issueDate;

    private LocalDate returnDate;

    private boolean returned;
}
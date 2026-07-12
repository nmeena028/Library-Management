package com.example.LibraryManagement.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"borrows"})
    private User user;

    private LocalDate issueDate;

    private LocalDate returnDate;

    private boolean returned;
}
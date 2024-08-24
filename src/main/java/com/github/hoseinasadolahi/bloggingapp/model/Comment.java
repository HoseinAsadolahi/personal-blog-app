package com.github.hoseinasadolahi.bloggingapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;
}

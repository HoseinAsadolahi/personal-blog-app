package com.github.hoseinasadolahi.bloggingapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "articles_seq")
    @SequenceGenerator(name = "articles_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private long id;

    @Length(min = 1, max = 50)
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private LocalDate publishedDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    @Cascade(CascadeType.REMOVE)
    private Set<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "article_likes",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "user_email"))
    @Cascade(CascadeType.REMOVE)
    private Set<User> likes;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
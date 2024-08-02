package com.example.blog.rest.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor // allows no need for empty constructor
@AllArgsConstructor // allows no need for auto-generated constructor
@Entity
@Table(
    name="posts",
    uniqueConstraints = {
            @UniqueConstraint(
                    columnNames = "title"
            )
    }
)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // set to long to allow this to be auto-incremented

    @NotEmpty(message = "Title cannot be empty or null.")
    @Size(min = 4, message = "Title should be at least 4 characters long.")
    @Column(name="title", nullable = false)
    private String title;

    @NotEmpty(message = "Description cannot be empty or null.")
    @Size(min = 10, message = "Description should be at least 10 characters long.")
    @Column(name = "description", nullable = false)
    private String description;

    @NotEmpty(message = "Content cannot be empty or null.")
    @Size(min = 10, message = "Content should be at least 10 characters long.")
    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL, // for deletion
            orphanRemoval = true, // auto-delete if post deletes
            fetch = FetchType.LAZY
    )

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

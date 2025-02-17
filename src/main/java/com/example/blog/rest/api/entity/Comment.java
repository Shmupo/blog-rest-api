package com.example.blog.rest.api.entity;

import com.example.blog.rest.api.repository.PostRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "comments"
)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name cannot be empty or null.")
    @Size(min = 3, message = "Name should be at least 3 characters long.")
    private String name;

    @NotEmpty(message = "Email cannot be empty or null.")
    @Size(min = 10, message = "Email should be at least 10 characters long.")
    private String email;

    @NotEmpty(message = "Body cannot be empty or null.")
    @Size(min = 10, message = "Body should be at least 10 characters long.")
    private String body;

    @ManyToOne(
            fetch = FetchType.LAZY // only fetch the comment, not the post with it
    )
    @JoinColumn( // keep ref to post id, foreign key
            name = "post_id",
            nullable = false
    )
    @JsonBackReference
    private Post post;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                ", post=" + post +
                '}';
    }
}

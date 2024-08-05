package com.example.blog.rest.api.payload;

import com.example.blog.rest.api.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponse {
    private List<Post> content;
    private int pageNo;
    private int pageSize;
    private long totalPages;
    private long totalElements;
    private boolean last;
}

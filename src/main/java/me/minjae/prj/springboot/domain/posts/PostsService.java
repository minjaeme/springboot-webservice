package me.minjae.prj.springboot.domain.posts;

import lombok.RequiredArgsConstructor;
import me.minjae.prj.springboot.web.dto.PostsResponseDto;
import me.minjae.prj.springboot.web.dto.PostsSaveRequestDto;
import me.minjae.prj.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRespository postsRespository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRespository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRespository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRespository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id" + id));

        return new PostsResponseDto(entity);
    }

}

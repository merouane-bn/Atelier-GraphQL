package ma.xproce.graphqlproduct.web;

import ma.xproce.graphqlproduct.dao.Creator;
import ma.xproce.graphqlproduct.dao.CreatorRequest;
import ma.xproce.graphqlproduct.dao.Video;
import ma.xproce.graphqlproduct.dao.VideoRequest;
import ma.xproce.graphqlproduct.dtos.CreatorDTO;
import ma.xproce.graphqlproduct.dtos.VideoDTO;
import ma.xproce.graphqlproduct.mappers.VideoMapper;
import ma.xproce.graphqlproduct.repositories.CreatorRepository;
import ma.xproce.graphqlproduct.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VideoGraphQlController {
    @Autowired
    private  CreatorRepository creatorRepository;
    @Autowired
    private  VideoRepository videoRepository;

 
    @QueryMapping
    public List<VideoDTO> videoList() {
        return videoRepository.findAll().stream()
                .map(VideoMapper::toVideoDTO)
                .collect(Collectors.toList());
    }

    @QueryMapping
    public CreatorDTO creatorById(@Argument Long id) {
        Creator creator = creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
        return VideoMapper.toCreatorDTO(creator);
    }

    @MutationMapping
    public CreatorDTO saveCreator(@Argument CreatorRequest creatorRequest) {
        Creator creator = new Creator();
        creator.setName(creatorRequest.getName());
        creator.setEmail(creatorRequest.getEmail());
        Creator savedCreator = creatorRepository.save(creator);
        return VideoMapper.toCreatorDTO(savedCreator);
    }

    @MutationMapping
    public VideoDTO saveVideo(@Argument("video") VideoRequest videoRequest) {
        Video video = new Video();
        video.setName(videoRequest.getName());
        video.setUrl(videoRequest.getUrl());
        video.setDescription(videoRequest.getDescription());
        video.setDatePublication(videoRequest.getDatePublication());

        // Set the creator for the video
        Creator creator = new Creator();
        creator.setName(videoRequest.getCreator().getName());
        creator.setEmail(videoRequest.getCreator().getEmail());
        creatorRepository.save(creator);
        video.setCreator(creator);

        Video savedVideo = videoRepository.save(video);
        return VideoMapper.toVideoDTO(savedVideo);
    }
}

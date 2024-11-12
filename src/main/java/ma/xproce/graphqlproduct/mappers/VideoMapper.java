package ma.xproce.graphqlproduct.mappers;

import ma.xproce.graphqlproduct.dao.Creator;
import ma.xproce.graphqlproduct.dao.Video;
import ma.xproce.graphqlproduct.dtos.CreatorDTO;
import ma.xproce.graphqlproduct.dtos.VideoDTO;

public class VideoMapper {

    public static VideoDTO toVideoDTO(Video video) {
        if (video == null) {
            return null;
        }

        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setId(video.getId());
        videoDTO.setName(video.getName());
        videoDTO.setUrl(video.getUrl());
        videoDTO.setDescription(video.getDescription());
        videoDTO.setDatePublication(video.getDatePublication());

        if (video.getCreator() != null) {
            videoDTO.setCreator(toCreatorDTO(video.getCreator()));
        }

        return videoDTO;
    }

    public static CreatorDTO toCreatorDTO(Creator creator) {
        if (creator == null) {
            return null;
        }

        CreatorDTO creatorDTO = new CreatorDTO();
        creatorDTO.setId(creator.getId());
        creatorDTO.setName(creator.getName());
        creatorDTO.setEmail(creator.getEmail());
        return creatorDTO;
    }

    public static Creator toCreatorEntity(CreatorDTO creatorDTO) {
        if (creatorDTO == null) {
            return null;
        }

        Creator creator = new Creator();
        creator.setId(creatorDTO.getId());
        creator.setName(creatorDTO.getName());
        creator.setEmail(creatorDTO.getEmail());
        return creator;
    }
}

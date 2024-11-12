package ma.xproce.graphqlproduct.repositories;


import ma.xproce.graphqlproduct.dao.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video,Long> {
}

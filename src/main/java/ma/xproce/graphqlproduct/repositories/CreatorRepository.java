package ma.xproce.graphqlproduct.repositories;

import ma.xproce.graphqlproduct.dao.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator,Long> {
}

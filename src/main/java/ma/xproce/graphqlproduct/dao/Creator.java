package ma.xproce.graphqlproduct.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Creator {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "creator")
    private List<Video> videos;
}

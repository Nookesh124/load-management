package management.load.repositories;

import management.load.entities.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<Userinfo,Integer> {
    Optional<Userinfo> findByName(String username);
}

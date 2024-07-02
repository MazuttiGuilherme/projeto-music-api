package projeto.music_api.musicApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.music_api.musicApi.model.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
}

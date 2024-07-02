package projeto.music_api.musicApi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.music_api.musicApi.model.Music;
import projeto.music_api.musicApi.repository.MusicRepository;

import java.util.List;
import java.util.Optional;

@RestController("/api/musics")
public class MusicController {

    @Autowired
    private MusicRepository musicRepository;

    @GetMapping
    public List<Music> getAllMusics() {
        return musicRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> getBookById(@PathVariable Long id) {
        Optional<Music> music = musicRepository.findById(id);
        return music.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Music createMusic(@RequestBody Music music) {
        return musicRepository.save(music);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Music> updateMusic(@PathVariable Long id, @RequestBody Music musicDetails) {
        Optional<Music> music = musicRepository.findById(id);
        if (music.isPresent()) {
            Music updatedMusic = music.get();
            updatedMusic.setTitle(musicDetails.getTitle());
            updatedMusic.setAuthor(musicDetails.getAuthor());
            return ResponseEntity.ok(musicRepository.save(updatedMusic));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable Long id) {
        if (musicRepository.existsById(id)) {
            musicRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

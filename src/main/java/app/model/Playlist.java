package app.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private final String name;
    private final boolean personal;
    private final List<Song> songs = new ArrayList<>();

    public Playlist(String name, boolean personal) {
        this.name = name;
        this.personal = personal;
    }

    public String getName() { return name; }
    public boolean isPersonal() { return personal; }
    public List<Song> getSongs() { return songs; }

    public void addSong(Song song) {
        songs.add(song);
    }

    @Override
    public String toString() {
        return name + (personal ? " (Personal)" : "");
    }
}

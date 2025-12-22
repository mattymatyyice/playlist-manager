package app.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private int id;
    private String name;
    private boolean personal;
    private List<Song> songs = new ArrayList<>();

    public Playlist(int id, String name, boolean personal) {
        this.id = id;
        this.name = name;
        this.personal = personal;
    }

    public String getName() {
        return name;
    }

    public boolean isPersonal() {
        return personal;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public List<Song> getSongs() {
        return songs;
    }

    @Override
    public String toString() {
        return name + (personal ? " [Personal]" : "");
    }
}

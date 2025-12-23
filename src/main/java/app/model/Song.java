package app.model;

public class Song {

    private final String title;
    private final String artist;
    private final String genre;
    private final int lengthSeconds;

    public Song(String title, String artist, String genre, int lengthSeconds) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.lengthSeconds = lengthSeconds;
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getGenre() { return genre; }

    @Override
    public String toString() {
        return title + " - " + artist + " (" + genre + ")";
    }
}

package app.model;

public class Song {

    private String title;
    private String artist;
    private String genre;
    private int lengthSeconds;

    public Song(String title, String artist, String genre, int lengthSeconds) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.lengthSeconds = lengthSeconds;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getLengthSeconds() {
        return lengthSeconds;
    }

    @Override
    public String toString() {
        return title + " | " + artist + " | " + genre + " | " + lengthSeconds + "s";
    }
}

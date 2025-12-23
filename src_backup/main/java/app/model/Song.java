package app.model;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String genre;
    private int lengthSeconds;

    public Song(int id, String title, String artist, String genre, int lengthSeconds) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.lengthSeconds = lengthSeconds;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getGenre() { return genre; }
    public int getLengthSeconds() { return lengthSeconds; }

    @Override
    public String toString() {
        int m = lengthSeconds / 60;
        int s = lengthSeconds % 60;
        return title + " | " + artist + " | " + genre + " | " + m + "m " + s + "s";
    }
}

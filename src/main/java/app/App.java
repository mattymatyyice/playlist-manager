package app;

import app.service.Service;
import app.model.Playlist;
import app.model.Song;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Service service = new Service();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Playlist Manager ===");

        System.out.print("User ID: ");
        int userId = Integer.parseInt(sc.nextLine());

        System.out.print("Password: ");
        String password = sc.nextLine();

        if (!service.login(userId, password)) {
            System.out.println("Login failed.");
            return;
        }

        System.out.println("Login successful.");

        while (true) {
            System.out.println("\n1. Add song");
            System.out.println("2. List songs");
            System.out.println("3. Add playlist");
            System.out.println("4. View playlists");
            System.out.println("5. Add song to playlist");
            System.out.println("6. View playlist contents");
            System.out.println("7. View songs by artist");
            System.out.println("8. View songs by genre");
            System.out.println("9. View personal playlists");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            String choice = sc.nextLine();

            switch (choice) {

                case "1" -> {
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Artist: ");
                    String artist = sc.nextLine();
                    System.out.print("Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Length (seconds): ");
                    int length = Integer.parseInt(sc.nextLine());

                    service.addSong(title, artist, genre, length);
                    System.out.println("Song added.");
                }

                case "2" -> {
                    service.getAllSongs()
                            .forEach(System.out::println);
                }

                case "3" -> {
                    System.out.print("Playlist name: ");
                    String name = sc.nextLine();
                    System.out.print("Personal playlist? (true/false): ");
                    boolean personal = Boolean.parseBoolean(sc.nextLine());

                    service.addPlaylist(name, personal);
                    System.out.println("Playlist added.");
                }

                case "4" -> {
                    service.getAllPlaylists()
                            .forEach(System.out::println);
                }

                case "5" -> {
                    System.out.print("Playlist name: ");
                    String playlistName = sc.nextLine();
                    System.out.print("Song title: ");
                    String songTitle = sc.nextLine();

                    try {
                        service.addSongToPlaylistByName(playlistName, songTitle);
                        System.out.println("Song added to playlist.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case "6" -> {
                    System.out.print("Playlist name: ");
                    String name = sc.nextLine();

                    service.getAllPlaylists()
                            .stream()
                            .filter(p -> p.getName().equalsIgnoreCase(name))
                            .findFirst()
                            .ifPresentOrElse(
                                    p -> p.getSongs().forEach(System.out::println),
                                    () -> System.out.println("Playlist not found.")
                            );
                }

                case "7" -> {
                    System.out.print("Artist: ");
                    String artist = sc.nextLine();

                    service.getSongsByArtist(artist)
                            .forEach(System.out::println);
                }

                case "8" -> {
                    System.out.print("Genre: ");
                    String genre = sc.nextLine();

                    service.getSongsByGenre(genre)
                            .forEach(System.out::println);
                }

                case "9" -> {
                    service.getPersonalPlaylists()
                            .forEach(p -> System.out.println(p.getName()));
                }

                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }

                default -> System.out.println("Invalid choice.");
            }
        }
    }
}

package app;

import app.service.Service;
import app.model.Playlist;
import app.model.Song;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Service service = new Service();

        System.out.println("=== Playlist Manager ===");

        // ---- Login (simple for demo) ----
        System.out.print("User ID: ");
        int userId = Integer.parseInt(sc.nextLine());

        System.out.print("Password: ");
        String password = sc.nextLine();

        if (!service.login(userId, password)) {
            System.out.println("Login failed.");
            return;
        }

        System.out.println("Login successful.");

        // ---- Main menu loop ----
        while (true) {
            System.out.println("\n1. Add song");
            System.out.println("2. List songs");
            System.out.println("3. Add playlist");
            System.out.println("4. View playlists");
            System.out.println("5. Add song to playlist");
            System.out.println("6. View playlist contents");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1 -> {
                    System.out.print("Song title: ");
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

                case 2 -> {
                    for (Song s : service.getAllSongs()) {
                        System.out.println(s);
                    }
                }

                case 3 -> {
                    System.out.print("Playlist name: ");
                    String name = sc.nextLine();

                    System.out.print("Is this a personal playlist? (true/false): ");
                    boolean personal = Boolean.parseBoolean(sc.nextLine());

                    service.addPlaylist(name, personal);
                    System.out.println("Playlist created.");
                }

                case 4 -> {
                    for (Playlist p : service.getAllPlaylists()) {
                        System.out.println(p);
                    }
                }

                case 5 -> {
                    System.out.print("Playlist name: ");
                    String playlistName = sc.nextLine();

                    System.out.print("Song title: ");
                    String songTitle = sc.nextLine();

                    service.addSongToPlaylistByName(playlistName, songTitle);
                    System.out.println("Song added to playlist.");
                }

                case 6 -> {
                    System.out.print("Playlist name: ");
                    String name = sc.nextLine();

                    Playlist playlist = service.getPlaylistByName(name);
                    if (playlist == null) {
                        System.out.println("Playlist not found.");
                    } else {
                        System.out.println(playlist);
                        for (Song s : playlist.getSongs()) {
                            System.out.println(" - " + s);
                        }
                    }
                }

                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }

                default -> System.out.println("Invalid choice.");
            }
        }
    }
}


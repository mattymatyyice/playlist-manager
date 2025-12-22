package app;

import app.service.Service;
import app.model.*;
import java.util.Scanner;

public class App {

    private static int readInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Please enter a number: ");
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Service service = new Service();

        System.out.println("=== Playlist Manager ===");

        System.out.print("User ID: ");
        int uid = readInt(sc);

        System.out.print("Password: ");
        String pw = sc.nextLine();

        if (!service.login(uid, pw)) {
            System.out.println("Login failed");
            return;
        }

        System.out.println("Login successful");

        while (true) {
            System.out.println("\n1. Add song");
            System.out.println("2. List songs");
            System.out.println("3. Add playlist");
            System.out.println("4. View playlists");
            System.out.println("5. Add song to playlist");
            System.out.println("6. View playlist songs");
            System.out.println("7. View personal playlists");
            System.out.println("8. View songs by artist");
            System.out.println("9. View songs by genre");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = readInt(sc);

            switch (choice) {
                case 1 -> {
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Artist: ");
                    String artist = sc.nextLine();
                    System.out.print("Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Length (seconds): ");
                    int len = readInt(sc);
                    service.addSong(title, artist, genre, len);
                    System.out.println("Song added.");
                }

                case 2 -> service.getAllSongs().forEach(System.out::println);

                case 3 -> {
                    System.out.print("Playlist name: ");
                    String name = sc.nextLine();
                    System.out.print("Personal playlist? (y/n): ");
                    boolean personal = sc.nextLine().equalsIgnoreCase("y");
                    service.addPlaylist(name, personal);
                    System.out.println("Playlist added.");
                }

                case 4 -> service.getAllPlaylists().forEach(System.out::println);

                case 5 -> {
                    System.out.print("Playlist name: ");
                    String pname = sc.nextLine();
                    System.out.print("Song title: ");
                    String sname = sc.nextLine();
                    service.addSongToPlaylistByName(pname, sname);
                    System.out.println("Song added to playlist (if names matched).");
                }

                case 6 -> {
                    System.out.print("Playlist name: ");
                    String pname = sc.nextLine();
                    Playlist p = service.getAllPlaylists()
                            .stream()
                            .filter(pl -> pl.getName().equalsIgnoreCase(pname))
                            .findFirst()
                            .orElse(null);

                    if (p == null) {
                        System.out.println("Playlist not found.");
                    } else if (p.getSongs().isEmpty()) {
                        System.out.println("(No songs yet.)");
                    } else {
                        p.getSongs().forEach(System.out::println);
                    }
                }

                case 7 -> service.getPersonalPlaylists().forEach(System.out::println);

                case 8 -> {
                    System.out.print("Artist name: ");
                    service.getSongsByArtist(sc.nextLine())
                            .forEach(System.out::println);
                }

                case 9 -> {
                    System.out.print("Genre name: ");
                    service.getSongsByGenre(sc.nextLine())
                            .forEach(System.out::println);
                }

                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }

                default -> System.out.println("Invalid option");
            }
        }
    }
}

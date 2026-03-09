
package music;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Music {
     Scanner scanner; // Declare the scanner object

       // Map to track play counts by date
    Map<String, Map<LocalDate, Integer>> playCountByDate = new HashMap<>();
    Map<String, List<SongNode>> moodBasedSongs = new HashMap<>();
    private String nextSongName;
    private int repeatCount;
    private final Playlist favorites = new Playlist();

    public Music() {
        scanner = new Scanner(System.in); // Initialize the scanner object
    }
    Playlist home = new Playlist(); // Home playlist
    Playlist[] playlists = new Playlist[10]; // Array of custom playlists
    int playlistCount = 0; // Count of custom playlists
    Map<String, Integer> playCount = new HashMap<>(); // To track the play count of songs

    // Sample map of song lyrics
    Map<String, String> songLyrics = new HashMap<>();

    void displayMenu() {
        System.out.println("******************************");
        System.out.println("       🎵 Music Player 🎵      ");
        System.out.println("*****************************");
        System.out.println("| 1. Add Song to Home       |");
        System.out.println("| 2. Create New Playlist    |");
        System.out.println("| 3. Display Playlists      |");
        System.out.println("| 4. Play Song              |");
        System.out.println("| 5. Sort Songs by Name     |");
        System.out.println("| 6. Sort Songs by Singer   |");
        System.out.println("| 7. Sort Songs by Duration |");
        System.out.println("| 8. Insert Song at Beginning|");
        System.out.println("| 9. Insert Song at End    |");
        System.out.println("| 10. Recommend Songs       |");
        System.out.println("| 11. Display Lyrics        |");
        System.out.println("| 12. Search Song           |");
        System.out.println("| 13. Create Custom Playlist|");
        System.out.println("| 14. Crossroad Play        |");
        System.out.println("| 15. Songs Total Count     |");
        System.out.println("| 16. Today's Biggest Hit     |");
        System.out.println("| 17. Repeat Song     |");
        System.out.println("| 18. Songs By Mood or Activity     |");
        System.out.println("| 19. Favourite Songs     |");
        System.out.println("| 0. Exit                   |");
        System.out.println("-----------------------------");
    }

    public void addSongToHome(String name, String singer, int duration, String lyrics, String mood) {
            home.insertLast(name, singer, duration, lyrics);
    // Add the song to the mood-based songs list
    if (!moodBasedSongs.containsKey(mood)) {
        moodBasedSongs.put(mood, new ArrayList<>());
    }
    moodBasedSongs.get(mood).add(home.tail);
    // Add the song to the favorites playlist
    favorites.insertLast(name, singer, duration, lyrics);
}
    // Method to recommend songs based on play count
    void recommendSongs() {
        System.out.println("-------------------------------");
        System.out.println("|       RECOMMENDED SONGS     |");
        System.out.println("-------------------------------");
        playCount.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> System.out.printf("| %-25s | %4d |\n", entry.getKey(), entry.getValue()));
        System.out.println("-------------------------------");
    }
    private SongNode currentSong; // Track the current song being played
    // Method to set the current song to repeat
    void setRepeat(boolean repeat) {
        if (currentSong != null) {
        currentSong.isRepeated = repeat;
        if (repeat) {
            System.out.println("Song \"" + currentSong.name + "\" is set to repeat.");
        } else {
            System.out.println("Repeat mode for song \"" + currentSong.name + "\" is disabled.");
        }
    } else {
        System.out.println("No song is currently playing.");
    }
    }
    // Method to handle playing the song
    void playSong(SongNode song) {
         System.out.println("-------------------------------");
         System.out.println("| Playing song: " + song.name);
         currentSong = song; // Update current song
         song.playCount++;
         updatePlayCount(song.name); // Update play count
         System.out.println("-------------------------------");
         handleRepeat(); // Check if the song needs to be repeated
    }
    // Method to add a song to a specific mood/activity
    public void addSongToMood(String mood, SongNode song) {
        if (!moodBasedSongs.containsKey(mood)) {
            moodBasedSongs.put(mood, new ArrayList<>());
        }
        moodBasedSongs.get(mood).add(song);
    }

    // Method to filter songs by mood/activity
    public void filterSongsByMood(String mood) {
        if (moodBasedSongs.containsKey(mood)) {
        List<SongNode> filteredSongs = moodBasedSongs.get(mood);
        System.out.println("-------------------------------");
        System.out.println("| Songs for " + mood + ":");
        System.out.println("-------------------------------");
        for (SongNode song : filteredSongs) {
            System.out.println("| " + song.name + " by " + song.singer);
        }
        System.out.println("-------------------------------");
    } else {
        System.out.println("-------------------------------");
        System.out.println("| No songs found for " + mood + ".");
        System.out.println("-------------------------------");
    }    }

     // Method to handle playing the next song in repeat mode
    void handleRepeat() {
        if (currentSong != null && currentSong.repeatCount > 0) {
        currentSong.repeatCount--;
        System.out.println("-------------------------------");
        System.out.println("| Replaying song: " + currentSong.name);
        playSong(currentSong); // Replay the current song
    }
    }

    // Method to display lyrics of the currently playing song
    void displayLyrics(SongNode song) {
        System.out.println("-------------------------------");
        if (song.lyrics != null && !song.lyrics.isEmpty()) {
            System.out.println("| Lyrics for " + song.name + ":");
            System.out.println("-------------------------------");
            System.out.println(song.lyrics);
            System.out.println("-------------------------------");
        } else {
            System.out.println("| Lyrics not available for this song.");
            System.out.println("-------------------------------------");
        }
    }

    // Method to search for a song by name, singer, or part of the lyrics
    void searchSong(String query) {
        System.out.println("-------------------------------");
        SongNode current = home.getHead();
        boolean found = false;
        while (current != null) {
            if (current.name.equalsIgnoreCase(query) || current.singer.equalsIgnoreCase(query) ||
                (current.lyrics != null && current.lyrics.contains(query))) {
                System.out.println("| Found song: " + current.name + " by " + current.singer);
                System.out.println("-------------------------------");
                found = true;
                
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("| Song not found in the playlist.");
            System.out.println("-------------------------------");
        }
    }
    void addSongToFavorites() {
    if (currentSong != null) {
        favorites.insertLast(currentSong.name, currentSong.singer, currentSong.duration, currentSong.lyrics);
    }
}
    void displayFavouriteSongs()
    {
        if (favorites.getTotalCount() == 0) {
        System.out.println("-------------------------------");
        System.out.println("| No favorite songs added yet.");
        System.out.println("-------------------------------");
    } else {
        System.out.println("-------------------------------");
        System.out.println("| Favorite Songs:");
        System.out.println("-------------------------------");
        favorites.displayNamesOnly();
    }
    
    
    }

    // Method to display the total number of songs in the home playlist
    void displayTotalSongsCount() {
        System.out.println("-------------------------------");
        System.out.println("| Total number of songs: " + home.getTotalCount());
        System.out.println("-------------------------------");
    }

    // Method to create a custom playlist with a specific total duration
    void createCustomPlaylistWithName() {
        System.out.println("-------------------------------");
        System.out.println("| Creating custom playlist... |");
        System.out.println("-------------------------------");
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();
        //Playlist customPlaylist = new Playlist();
        Playlist customPlaylist = new Playlist(playlistName);
        System.out.println("-------------------------------");
        System.out.println("| " + playlistName + " playlist created.");
        System.out.println("-------------------------------");
        
        
        int i = 0;
        while (i < 3) {
        System.out.print("Enter song " + (i + 1) + " name: ");
        String songName = scanner.nextLine();
        System.out.print("Enter song " + (i + 1) + " singer: ");
        String singer = scanner.nextLine();
        System.out.print("Enter song " + (i + 1) + " duration (in seconds): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter song " + (i + 1) + " lyrics: ");
        String lyrics = scanner.nextLine();
        customPlaylist.insertLast(songName, singer, duration, lyrics);
        
        i++; // Increment the counter
        
            // View the songs in the playlist
        System.out.println("-------------------------------");
        System.out.println("| Total: " + customPlaylist.getSongCount());
        System.out.println("-------------------------------");
        customPlaylist.displayNamesOnly();

       /* playlists[playlistCount] = customPlaylist;
         playlistCount++; */

}    playlists[playlistCount] = customPlaylist;
        playlistCount++;
    }
    void addSongToPlaylist() {
    System.out.print("Enter playlist number to add song to: ");
    int playlistNumber = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    if (playlistNumber >= 1 && playlistNumber <= playlistCount) {
        System.out.print("Enter song name: ");
        String name = scanner.nextLine();
        System.out.print("Enter singer name: ");
        String singer = scanner.nextLine();
        System.out.print("Enter duration (in seconds): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter lyrics: ");
        String lyrics = scanner.nextLine();
        playlists[playlistNumber - 1].insertLast(name, singer, duration, lyrics);
        System.out.println("-------------------------------");
        System.out.println("| Song added to playlist " + playlistNumber);
        System.out.println("-------------------------------");
    } else {
        System.out.println("-------------------------------");
        System.out.println("| Invalid playlist number.");
        System.out.println("-------------------------------");
    }
}

    // Method to crossfade play between two songs
    void crossfadePlay(SongNode current, SongNode next) {
        while (current.next != null) {
        current = current.next;
        if (current.name.equalsIgnoreCase(nextSongName)) {
            System.out.println("Crossfading from " + current.prev.name + " to " + current.name);
            nextSongName = current.name; // Update nextSongName
            break;
        }
    }
    if (current.next == null) {
        System.out.println("Next song not found.");
    } else {
        next = current.next; // This line is not necessary and can be removed
    }
    }

    // Method to add lyrics to a song
    void addLyricsToSong(SongNode song, String lyrics) {
        song.lyrics = lyrics;
        System.out.println("-------------------------------");
        System.out.println("| Lyrics added to " + song.name);
        System.out.println("-------------------------------");
    }
     // Method to update the play count for a song
    void updatePlayCount(String songName) {
         LocalDate today = LocalDate.now();
         playCountByDate.putIfAbsent(songName, new HashMap<>());
         Map<LocalDate, Integer> dailyCounts = playCountByDate.get(songName);
         dailyCounts.put(today, dailyCounts.getOrDefault(today, 0) + 1);
    
    // Update the playCount map
    playCount.put(songName, playCount.getOrDefault(songName, 0) + 1);
    }

    // Method to retrieve and display today's biggest hit
    void getTodaysBiggestHit() {
        String biggestHit = null;
        int maxPlays = 0;

         for (Map.Entry<String, Integer> entry : playCount.entrySet()) {
             String songName = entry.getKey();
             int totalPlays = entry.getValue();

             if (totalPlays > maxPlays) {
                maxPlays = totalPlays;
                biggestHit = songName;
             }
    }

         System.out.println("-------------------------------");
         if (biggestHit!= null) {
             System.out.println("| Today's Biggest Hit: " + biggestHit);
             System.out.println("| Play Count: " + maxPlays);
    }
    else {
             System.out.println("| No plays recorded today.");
    }
    System.out.println("-------------------------------");
    }
    

    

    // Main method
    public static void main(String[] args) {
        Music player = new Music();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            player.displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1: {
          while (true) 
          {
              System.out.print("Enter song name (or type 'exit' to stop adding songs): ");
              String name = scanner.nextLine();
              if (name.equalsIgnoreCase("exit")) {
                  break;
                }
               System.out.print("Enter singer name: ");
               String singer = scanner.nextLine();
               System.out.print("Enter duration (in seconds): ");
               int duration = scanner.nextInt();
               scanner.nextLine(); // Consume newline
               System.out.print("Enter lyrics: ");
               String lyrics = scanner.nextLine();
               System.out.print("Enter mood (happy, sad, workout, etc.): ");
               String mood = scanner.nextLine();
               System.out.print("Do you want to repeat this song (yes/no): ");
               String repeat = scanner.nextLine();
               boolean isRepeated = repeat.equalsIgnoreCase("yes");

               player.addSongToHome(name, singer, duration, lyrics, mood);
               if (isRepeated) {
                    player.setRepeat(true);
                } 

                player.displayTotalSongsCount();
                }
                break;
                }
                case 2: {
                    player.createCustomPlaylistWithName();
                    break;
                }
                 case 3: {
                    System.out.println("-------------------------------");
                    System.out.println("| Available Playlists:");
                    System.out.println("-------------------------------");
                    for (int i = 0; i < player.playlistCount; i++) {
                         System.out.println("| " + (i + 1) + ". " + player.playlists[i].getName());
                         }
                        System.out.println("-------------------------------");
                        break;
                 }
                case 4: {
                 System.out.print("Enter song name to play: ");
                 String name = scanner.nextLine();
                 SongNode current = player.home.getHead();
                 while (current != null) {
                 if (current.name.equalsIgnoreCase(name)) {
                      player.playSong(current); // Set current song and play it
                      player.handleRepeat(); // Check if the song needs to be repeated
                      break;
                    }
                    current = current.next;
                    }
                    if (current == null) {
                        System.out.println("-------------------------------");
                        System.out.println("| Song not found.");
                        System.out.println("-------------------------------");
                        }
                       break;
            
                }
                case 5: {
                    player.home = player.home.sortByName();
                    System.out.println("-------------------------------");
                    System.out.printf("| %-25s |\n", "Song Name");
                    
                    System.out.println("-------------------------------");
                    player.home.displayNamesOnly();
                     break;
                }
               case 6: {
                     player.home = player.home.sortBySinger();
                     System.out.println("-------------------------------");
                     System.out.printf("| %-25s | %-20s |\n", "Song Name", "Singers");

                     System.out.println("-------------------------------");
                     player.home.displaySingersOnly(); // Display song names and singers
                     System.out.println("-------------------------------");
                     break;
}

                case 7: {
                     player.home = player.home.sortByDuration();
                     System.out.println("-------------------------------");
                     System.out.printf("| %-25s | %-10s |\n", "Song Name", "Durations");
                     System.out.println("-------------------------------");
                     player.home.displayDurationsOnly(); // Display song names and durations
                     System.out.println("-------------------------------");
                     break;
}
                case 8: {
                    System.out.print("Enter song name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter singer name: ");
                    String singer = scanner.nextLine();
                    System.out.print("Enter duration (in seconds): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter lyrics: ");
                    String lyrics = scanner.nextLine();
                    player.home.insertFirst(name, singer, duration, lyrics);
                    System.out.println("------------------------------------------------");
                    System.out.println("Song added to the beginning of home playlist");
                    System.out.println("------------------------------------------------");

                    break;
                }
                case 9: {
                    System.out.print("Enter song name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter singer name: ");
                    String singer = scanner.nextLine();
                    System.out.print("Enter duration (in seconds): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter lyrics: ");
                    String lyrics = scanner.nextLine();
                    player.home.insertLast(name, singer, duration, lyrics);
                    System.out.println("-------------------------------------------");
                    System.out.println("Song added to the end of home playlist");
                    System.out.println("-------------------------------------------");

                    break;
                }
                
                case 10: {
                    player.recommendSongs();
                    break;
                }
                case 11: {
                    System.out.print("Enter song name to display lyrics: ");
                    String name = scanner.nextLine();
                    SongNode current = player.home.getHead();
                    while (current != null) {
                        if (current.name.equalsIgnoreCase(name)) {
                            player.displayLyrics(current);
                            break;
                        }
                        current = current.next;
                    }
                    if (current == null) {
                       System.out.println("-------------------------------");
                       System.out.println("| Song not found.");
                       System.out.println("-------------------------------");

                    }
                    break;
                }
                case 12: {
                    System.out.print("Enter song name, singer, or lyrics to search: ");
                    String query = scanner.nextLine();
                    player.searchSong(query);
                    break;
                }
                case 13: {
                    player.addSongToPlaylist();
                    break;
                }
                case 14: {
                    System.out.print("Enter current song name: ");
                    String currentName = scanner.nextLine();
                    System.out.print("Enter next song name: ");
                    String nextName = scanner.nextLine();
                    SongNode current = player.home.getHead();
                    while (current!= null) {
                    if (current.name.equalsIgnoreCase(currentName)) {
                       player.crossfadePlay(current, current);
                       break;
                    }
                    current = current.next;
                      }
                      if (current == null) {
                      System.out.println("-------------------------------");
                      System.out.println("| Current song not found.");
                      System.out.println("-------------------------------");
                     }
                     break;
                }
                case 15: {
                    player.displayTotalSongsCount();
                    break;
                }
                case 16:
                {
                     player.getTodaysBiggestHit();
                     break;
                }
                case 17 : 
                {
                    System.out.print("Enter 'on' to repeat or 'off' to disable repeat: ");
                    String repeatCommand = scanner.nextLine();
                    boolean repeat = repeatCommand.equalsIgnoreCase("on");
                    player.setRepeat(repeat);
                    break;
                }
                case 18 :
                {
                    System.out.print("Enter mood/activity (e.g., happy, sad, workout,love): ");
                    String mood = scanner.nextLine();
                    player.filterSongsByMood(mood);
                    break;
                }
                case 19 :
                {
                     System.out.println("-------------------------------");
                     System.out.println("| Favorite Songs:");
                     player.displayFavouriteSongs(); 
                     System.out.println("-------------------------------");
                     break;
                }
                        
                case 0: {
                    System.out.println("-------------------------------");
                    System.out.println("| Exiting the Music Player.");
                    System.out.println("-------------------------------");
                    scanner.close();
                    return;
                }
                default: {
                    System.out.println("-------------------------------");
                    System.out.println("| Invalid choice. Please try again.");
                    System.out.println("-------------------------------");
                }
            }
        }
    }

   
}
            
            
            
            

   

    

   

   


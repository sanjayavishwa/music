package music;

public class SongNode {

        String name;
        String singer;
        int duration; // Duration in seconds
        int playCount; // Track play count
        String lyrics; // Store lyrics
        SongNode next;
        SongNode prev;
        boolean isRepeated;
        int repeatCount;


    public SongNode(String name, String singer, int duration, String lyrics) {
        this.name = name;
        this.singer = singer;
        this.duration = duration;
        this.playCount = 0; // Initialize play count to 0
        this.lyrics = lyrics; // Store lyrics
        this.isRepeated = false; // Initialize pause to false
    }

}

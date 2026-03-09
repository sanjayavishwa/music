package music;

public class Playlist {
    private SongNode head;
    SongNode tail;
    int totalCount; // Track total number of songs in the playlist
    int songCount; // Track the number of songs in the playl

    private String name;
    private boolean isRepeated; // Add a repeat variable to keep track of whether the playlist is repeated

    public Playlist(String name) {
        this.name = name;

    }

    public String getName() {
        return this.name;
    }
    public int getSongCount() {
        return this.songCount;
    }


    public Playlist() {
        this.head = null;
        this.tail = null;
        this.totalCount = 0; // Initialize song count to 0
        this.isRepeated = false; // Initialize repeat to false
        this.songCount = 0;

    }

    public void insertFirst(String name, String singer, int duration, String lyrics) {
        SongNode newNode = new SongNode(name, singer, duration, lyrics);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        totalCount++; // increment the total count
        songCount++;
    }

    public void insertLast(String name, String singer, int duration, String lyrics) {
        SongNode newNode = new SongNode(name, singer, duration, lyrics);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        totalCount++; // Increment song count
        songCount++;
    }

    public void displayNamesOnly() {
        System.out.println("-------------------------------");
        SongNode current = head;
        while (current != null) {
            System.out.printf("| %-25s |\n", current.name);
            current = current.next;
        }
        System.out.println("-------------------------------");
    }
    public void displaySingersOnly() {
        SongNode current = head;
        while (current != null) {
            System.out.printf("| %-25s | %-20s |\n", current.name, current.singer);
            current = current.next;
        }
    }
    public void displayDurationsOnly() {

        SongNode current = head;
        while (current != null) {
            System.out.printf("| %-25s | %-10d |\n", current.name, current.duration);
            current = current.next;
        }

    }
    public SongNode getHead() {
        return head;
    }

    public int getTotalCount() {
        return totalCount; // Return the total number of songs
    }

    // Sorting logic for songs by name
    public Playlist sortByName() { //sorting algorithm: bubble sort
        if (head == null) return this;
        SongNode current, index;
        String tempName, tempSinger, tempLyrics;
        int tempDuration, tempPlayCount;

        for (current = head; current.next != null; current = current.next) {
            for (index = current.next; index != null; index = index.next) {
                if (current.name.compareToIgnoreCase(index.name) > 0) {
                    // Swap the node data
                    tempName = current.name;
                    tempSinger = current.singer;
                    tempDuration = current.duration;
                    tempLyrics = current.lyrics;
                    tempPlayCount = current.playCount;

                    current.name = index.name;
                    current.singer = index.singer;
                    current.duration = index.duration;
                    current.lyrics = index.lyrics;
                    current.playCount = index.playCount;

                    index.name = tempName;
                    index.singer = tempSinger;
                    index.duration = tempDuration;
                    index.lyrics = tempLyrics;
                    index.playCount = tempPlayCount;
                }
            }
        }
        return this;
    }
    // Sorting logic for songs by singer
    public Playlist sortBySinger() {
        if (head == null) return this;
        SongNode current, index;
        String tempName, tempSinger, tempLyrics;
        int tempDuration, tempPlayCount;

        for (current = head; current.next != null; current = current.next) {
            for (index = current.next; index != null; index = index.next) {
                if (current.singer.compareToIgnoreCase(index.singer) > 0) {
                    // Swap the node data
                    tempName = current.name;
                    tempSinger = current.singer;
                    tempDuration = current.duration;
                    tempLyrics = current.lyrics;
                    tempPlayCount = current.playCount;

                    current.name = index.name;
                    current.singer = index.singer;
                    current.duration = index.duration;
                    current.lyrics = index.lyrics;
                    current.playCount = index.playCount;

                    index.name = tempName;
                    index.singer = tempSinger;
                    index.duration = tempDuration;
                    index.lyrics = tempLyrics;
                    index.playCount = tempPlayCount;
                }
            }
        }

        return this;
    }
    // Sorting logic for songs by duration
    public Playlist sortByDuration() {
        if (head == null) return this;
        SongNode current, index;
        String tempName, tempSinger, tempLyrics;
        int tempDuration, tempPlayCount;

        for (current = head; current.next != null; current = current.next) {
            for (index = current.next; index != null; index = index.next) {
                if (current.duration > index.duration) {
                    // Swap the node data
                    tempName = current.name;
                    tempSinger = current.singer;
                    tempDuration = current.duration;
                    tempLyrics = current.lyrics;
                    tempPlayCount = current.playCount;

                    current.name = index.name;
                    current.singer = index.singer;
                    current.duration = index.duration;
                    current.lyrics = index.lyrics;
                    current.playCount = index.playCount;

                    index.name = tempName;
                    index.singer = tempSinger;
                    index.duration = tempDuration;
                    index.lyrics = tempLyrics;
                    index.playCount = tempPlayCount;
                }
            }
        }

        return this;

    }

    boolean containsSong(String songName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void insertFirst(String songName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

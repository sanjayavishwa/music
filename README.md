# Music Player Application - PDSA 1 Project

A Java-based console application designed to efficiently manage and play music by leveraging advanced Data Structures and Algorithms.

This project was developed for the Programming Data Structures & Algorithms 1 module to demonstrate the practical application of efficient memory management, data retrieval, and sorting techniques.

## Group Members
* **K. A. Pramuditha** - COHNDSE241F-025
* **R. A. H. Y. Ranaweera** - COHNDSE241F-050
* **M. A. S. V. Anuradha** - COHNDSE241F-116

---

## Data Structures Implemented
To ensure optimal performance, the application utilizes the following data structures:

* **Doubly Linked List:** Core backbone for the `Playlist` implementation. It allows for highly efficient `O(1)` insertions at the tail and supports features like "Play Next" and "Play Previous" song.
* **HashMap:** Fast-search index mapping exact song names to their respective `SongNode`. It is also used to map songs by keys like moods, and tracks daily play counts for analytics.
* **ArrayList:** A dynamic array and connect HashMap to group multiple `SongNode` objects under a single mood category without being restricted by fixed memory limits.
* **Arrays:** A fixed-size array to safely store playlists.

---

## Key Algorithms Used
* **Sorting Algorithm:** An adapted **Bubble Sort** logic is implemented to reorder the Doubly Linked List. It allows users to sort their playlists dynamically by Song Name, Singer, and Track Duration with a time complexity of `O(N^2)`.
* **Searching Algorithms (Hybrid Approach):** * **Hash-Based Search:** Used as the primary lookup for instant `O(1)` exact-match song queries via the system's `fastSearchIndex`.
    * **Linear Search:** Used as a fallback mechanism for sequential deep-search traversal (`O(N)`) through the linked list to search by specific singers or lyrics.

---

## Core Features
* Hybrid Search Engine (O(1) exact match by name and O(N) deep search).
* Add, search, and delete songs from the main library.
* Mood-based playlist management and filtering.
* Comprehensive playback controls (Play Next, Play Previous, Repeat).
* Analytics-driven song recommendations (today’s biggest hit) and play-count tracking.
* Dynamic sorting by multiple attributes (Name, Singer, Duration).

---
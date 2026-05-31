import java.util.*;

// -------------------- SONG --------------------
class Song {
    private String id;
    private String title;
    private String artist;
    private int durationSeconds;

    public Song(String id, String title, String artist, int durationSeconds) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }
}



// -------------------- MUSIC LIBRARY --------------------
class MusicLibrary {
    private List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public List<Song> getAllSongs() {
        return songs;
    }

    public Song getSongById(String id) {
        for (Song song : songs) {
            if (song.getId().equals(id)) {
                return song;
            }
        }
        return null;
    }
}


// -------------------- PAYMENT SYSTEM --------------------
class Payment {
    private int balance = 0;

    public void insertCoin(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean hasSufficientBalance(int cost) {
        return balance >= cost;
    }

    public void deduct(int cost) {
        if (cost <= balance) {
            balance -= cost;
        }
    }

    public int getBalance() {
        return balance;
    }
}


// -------------------- AUDIO PLAYER --------------------
class AudioPlayer {
    public void play(Song song) {
        System.out.println("🎵 Now playing: " + song.getTitle()
                + " by " + song.getArtist());

        // Simulate playback
        try {
            Thread.sleep(Math.min(song.getDurationSeconds(), 3) * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("⏹ Finished playing: " + song.getTitle());
    }
}


// -------------------- JUKEBOX CONTROLLER --------------------
class JukeboxController {

    private MusicLibrary library;
    private Payment payment;
    private AudioPlayer player;

    private Song currentSong;
    private final int SONG_COST = 10;

    public JukeboxController(MusicLibrary library, Payment payment, AudioPlayer player) {
        this.library = library;
        this.payment = payment;
        this.player = player;
    }

    // Select a song
    public void selectSong(String songId) {
        Song song = library.getSongById(songId);

        if (song == null) {
            System.out.println("Song not found");
            return;
        }

        currentSong = song;
        System.out.println("Selected: " + song.getTitle());
    }

    // Insert money
    public void insertCoin(int amount) {
        payment.insertCoin(amount);
        System.out.println("Inserted: " + amount + " | Balance: " + payment.getBalance());
    }

    // Play selected song
    public void play() {

        if (currentSong == null) {
            System.out.println("No song selected");
            return;
        }

        if (!payment.hasSufficientBalance(SONG_COST)) {
            System.out.println("Insufficient balance. Please insert more coins.");
            return;
        }

        payment.deduct(SONG_COST);

        System.out.println("Starting playback...");
        player.play(currentSong);

        // Reset state after playing
        currentSong = null;
    }
}


// -------------------- DEMO MAIN --------------------
public class JukeboxDemo {
    public static void main(String[] args) {

        MusicLibrary library = new MusicLibrary();
        Payment payment = new Payment();
        AudioPlayer player = new AudioPlayer();

        JukeboxController jukebox = new JukeboxController(library, payment, player);

        // Add songs
        library.addSong(new Song("1", "Blinding Lights", "The Weeknd", 200));
        library.addSong(new Song("2", "Shape of You", "Ed Sheeran", 210));

        // User flow
        jukebox.selectSong("1");
        jukebox.insertCoin(5);
        jukebox.insertCoin(10);
        jukebox.play();
    }
}
// A file system is naturally a tree.
// Every file or folder has a parent, and folders can contain children.

// Entry
//  ├── File
//  └── Directory

// ENTRY.JAVA

abstract class Entry {

    protected String name;
    protected Directory parent;

    public Entry(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public String getFullPath() {
        if (parent == null) {
            return "/" + name;
        }

        return parent.getFullPath() + "/" + name;
    }

    public abstract int size();
}


// FILE.JAVA

class File extends Entry {

    private String content;

    public File(String name,
                Directory parent,
                String content) {

        super(name, parent);
        this.content = content;
    }

    @Override
    public int size() {
        return content.length();
    }

    public String read() {
        return content;
    }

    public void write(String content) {
        this.content = content;
    }
}

// DIRECTORY.JAVA

import java.util.*;

class Directory extends Entry {

    private Map<String, Entry> children;

    public Directory(String name, Directory parent) {
        super(name, parent);
        this.children = new HashMap<>();
    }

    @Override
    public int size() {

        int total = 0;

        for (Entry entry : children.values()) {
            total += entry.size();
        }

        return total;
    }

    public void addEntry(Entry entry) {
        children.put(entry.getName(), entry);
    }

    public void removeEntry(String name) {
        children.remove(name);
    }

    public Entry getChild(String name) {
        return children.get(name);
    }

    public Collection<Entry> getChildren() {
        return children.values();
    }
}


// FILESYSTEM.JAVA


class FileSystem {

    private Directory root;

    public FileSystem() {
        root = new Directory("root", null);
    }

    public Directory getRoot() {
        return root;
    }

    public Entry find(String path) {

        if (path == null || path.isEmpty()) {
            return null;
        }

        String[] parts = path.split("/");

        Directory current = root;

        for (int i = 1; i < parts.length; i++) {

            Entry entry = current.getChild(parts[i]);

            if (entry == null) {
                return null;
            }

            if (i == parts.length - 1) {
                return entry;
            }

            if (!(entry instanceof Directory)) {
                return null;
            }

            current = (Directory) entry;
        }

        return root;
    }
}


// MAIN.JAVA

public class Main {

    public static void main(String[] args) {

        FileSystem fs = new FileSystem();

        Directory root = fs.getRoot();

        // Create folders
        Directory documents =
                new Directory("Documents", root);

        Directory music =
                new Directory("Music", root);

        root.addEntry(documents);
        root.addEntry(music);

        // Create files
        File resume =
                new File(
                        "resume.txt",
                        documents,
                        "John Muchiri Resume");

        File song =
                new File(
                        "song.mp3",
                        music,
                        "binary music data");

        documents.addEntry(resume);
        music.addEntry(song);

        // Read file
        System.out.println(resume.read());

        // Path
        System.out.println(resume.getFullPath());

        // Lookup
        Entry found =
                fs.find("/Documents/resume.txt");

        System.out.println(found.getName());

        // Directory size
        System.out.println(
                documents.size()
        );

        // Delete file
        documents.removeEntry("resume.txt");

        Entry deleted =
                fs.find("/Documents/resume.txt");

        System.out.println(deleted);
    }
}


// | Operation        | Complexity           |
// | ---------------- | -------------------- |
// | Create File      | O(1)                 |
// | Create Directory | O(1)                 |
// | Delete File      | O(1)                 |
// | Lookup Child     | O(1)                 |
// | Find by Path     | O(depth)             |
// | Directory Size   | O(total descendants) |

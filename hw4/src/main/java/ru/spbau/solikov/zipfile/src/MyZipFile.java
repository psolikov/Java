package ru.spbau.solikov.zipfile.src;

import org.jetbrains.annotations.*;

import java.io.*;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;


/**
 * Class for extracting all files matching the regex from all zip-archives at directory
 * Takes folder-path and regex as String
 * Has one public method 'unzip' for use
 */
public class MyZipFile {

    private final String directory;
    private final Pattern pattern;

    public MyZipFile(String directory, String regex) {
        this.directory = directory;
        this.pattern = Pattern.compile(regex);
    }

    /**
     * Function to get array of all files from given directory
     *
     * @param root file instance of directory to be unzipped
     * @return array of all files from directory
     * @throws IllegalArgumentException exception that can occur from mismatched parameter
     */
    private static File[] filesInDirectory(@NotNull File root) throws IllegalArgumentException {
        if (!root.isDirectory()) {
            throw new IllegalArgumentException(root + " is not a directory.");
        }
        return root.listFiles();
    }

    /**
     * Function for check if given file already exists in directory
     *
     * @param file file to be checked
     * @return true if exists, false - otherwise
     */
    private static boolean isFileInDirectory(@NotNull File file) {
        return file.exists();
    }

    /**
     * Public method that provides ability to unzip all zip-files from directory
     * Checks every file in directory for being zip-archive and in that case unzips
     * Uses writing from input stream to output stream
     *
     * @throws ZipFileException appears if zip-archive contains more than one file with the same name
     */
    public void unzip() throws ZipFileException {
        File[] files = filesInDirectory(new File(directory));
        for (File file : files) {
            if (file.exists() && file.canRead() && !file.isDirectory()) {
                try (ZipFile zipFile = new ZipFile(file.getAbsolutePath())) {
                    if (zipFile.size() != 0) {
                        Enumeration entries = zipFile.entries();
                        while (entries.hasMoreElements()) {
                            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                            Matcher matcher = pattern.matcher(zipEntry.getName());
                            if (matcher.matches()) {
                                if (zipEntry.isDirectory()) {
                                    File directory = new File(file.getParent(), zipEntry.getName());
                                    directory.mkdirs();
                                } else {
                                    if (isFileInDirectory(new File(file.getParent(), zipEntry.getName()))) {
                                        throw new ZipFileException("There is more than 1 file with name \"" + zipEntry.getName() + "\" in the archive");
                                    } else {
                                        writeToStream(zipFile.getInputStream(zipEntry), new File(file.getParent(), zipEntry.getName()));
                                    }
                                }
                            }
                        }
                    }
                } catch (ZipException ignored) {
                    /*
                     * No code here because we want to ignore non-zipped files
                     * Exception that could be caught in that catch statement means that
                     * the file that is tried to open by ZipFile is not actually zip file.
                     */
                } catch (IOException e) {
                    System.err.println("Input/Output exception: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Help function for writing into file. Generates buffer, reads into it and writes it.
     *
     * @param inputStream stream form which it will read bytes
     * @param file        file to be written
     * @throws IOException could occur while working with streams
     */
    private void writeToStream(@NotNull InputStream inputStream, @NotNull File file) throws IOException {
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(buffer);
        fos.flush();
        fos.close();
    }
}

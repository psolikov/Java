package ru.spbau.solikov.zipfile.test;

import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Test;
import ru.spbau.solikov.zipfile.src.MyZipFile;
import ru.spbau.solikov.zipfile.src.ZipFileException;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tests for MyZipFile class. Manages zip-archives from Example folder
 */
public class MyZipFileTest {
    private String path = System.getProperty("user.dir");
    private File folder = new File(path + "/Example");
    private File nonZipFolder = new File(path + "/Example/NotZipped");
    private Pattern pattern = Pattern.compile(".*.example");

    @Test
    public void testConstructor() {
        MyZipFile myZipFile = new MyZipFile("Dir", "Regex");
    }

    @Test
    public void testUnzipEmptyDirectory() throws ZipFileException {
        MyZipFile myZipFile = new MyZipFile(path + "/Example/NotZipped", "/.*");
        myZipFile.unzip();
        assertEquals(2, nonZipFolder.listFiles().length);
    }

    @Test(expected = ZipFileException.class)
    public void testUnzipThrowsExceptionIf2SameFiles() throws ZipFileException {
        MyZipFile myZipFile = new MyZipFile(path + "/Example/TwoOneFiles", "1.example");
        myZipFile.unzip();
    }

    @Test
    public void testUnzipOneFileExtractsOneFile() throws ZipFileException {
        MyZipFile myZipFile = new MyZipFile(path + "/Example/OneFile", ".*");
        myZipFile.unzip();
        assertEquals(2, new File(folder.getAbsolutePath() + "/OneFile").listFiles().length);
    }

    @Test
    public void testUnzipTwoFilesExtractsTwoFiles() throws ZipFileException {
        MyZipFile myZipFile = new MyZipFile(path + "/Example/TwoFiles", ".*");
        myZipFile.unzip();
        assertEquals(3, new File(folder.getAbsolutePath() + "/TwoFiles").listFiles().length);
    }

    @After
    public void deleteAllNonZipFiles() {
        File[] files1 = new File(folder.getAbsolutePath() + "/OneFile").listFiles();
        File[] files2 = new File(folder.getAbsolutePath() + "/TwoFiles").listFiles();
        File[] files3 = new File(folder.getAbsolutePath() + "/TwoOneFiles").listFiles();

        if (files1 != null) {
            for (File f : files1) {
                Matcher matcher = pattern.matcher(f.getName());
                if (!f.isDirectory() && matcher.matches()) {
                    f.delete();
                }
            }
        }

        if (files2 != null) {
            for (File f : files2) {
                Matcher matcher = pattern.matcher(f.getName());
                if (!f.isDirectory() && matcher.matches()) {
                    f.delete();
                }
            }
        }

        if (files3 != null) {
            for (File f : files3) {
                Matcher matcher = pattern.matcher(f.getName());
                if (!f.isDirectory() && matcher.matches()) {
                    f.delete();
                }
            }
        }
    }
}

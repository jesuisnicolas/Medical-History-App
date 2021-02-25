package com.example.medicalhistory;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class TestImportExport {

    @Test
    public void testsShouldWork(){
        //just a sanity test that assertEquals will check the contents of a string from Page
        String testText = "Test text";

        Page firstPage = new Page();
        firstPage.setBodyText(testText);

        Page secondPage = new Page();
        secondPage.setBodyText(testText);

        assertEquals(firstPage.getBodyText(), secondPage.getBodyText());
    }

    @Test
    public void makeFileTest(){
        //test to make sure the FileExport class will make a new file if one doesnt already exsist
        String testText = "Test text";
        Page testPage = new Page();
        testPage.setBodyText(testText);

        FileExport testExport = new FileExport();
        testExport.setPage(testPage);
        testExport.setFileReffrance("newFile");
        testExport.exportPage();

        File file = new File("newFile");
        assertEquals(true, file.exists());
    }

    @Test
    public void importExportTest(){
        //test to make sure the FileExport and FileImport classes accuretly import and export text so it is the same at both ends
        String testText = "Test Text";
        Page testPage = new Page();
        testPage.setBodyText(testText);

        FileExport testExport = new FileExport();
        testExport.setPage(testPage);
        testExport.setFileReffrance("testFile");
        testExport.exportPage();

        FileImport testImport = new FileImport();
        testImport.setFileReffrance("testFile");
        testImport.importFile();

        Page importedPage = testImport.getPage();
        assertEquals(testPage.getBodyText(), importedPage.getBodyText());
    }


    @Test
    public void overrideFile(){
        //exports a new file
        //exports to the same file to see that the text actualy gets overriden
        String startingText = "this is the starting text";
        Page startingPage = new Page();
        startingPage.setBodyText(startingText);

        FileExport startingExport = new FileExport();
        startingExport.setFileReffrance("OverrideFile");
        startingExport.setPage(startingPage);
        startingExport.exportPage();

        String overrideText = "New text where no words match";
        Page overridePage = new Page();
        overridePage.setBodyText(overrideText);

        FileExport overrideExport = new FileExport();
        overrideExport.setPage(overridePage);
        overrideExport.setFileReffrance("OverrideFile");
        overrideExport.exportPage();

        FileImport importFile = new FileImport();
        importFile.setFileReffrance("OverrideFile");
        importFile.importFile();

        Page importedPage = importFile.getPage();

        assertEquals("New text where no words match", importedPage.getBodyText());
    }


}

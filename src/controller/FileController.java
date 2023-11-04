package controller;

import common.Library;
import model.ModelFile;

import view.Menu;

public class FileController extends Menu<String> {

    Library lib = new Library();
    static String[] mChoice = new String[]{"Compression", "Extraction", "Exit"};
    ModelFile f= new ModelFile();

    public FileController() {
        super("========= Zipper program =========", mChoice);
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                //"D:\FA23\DEMO"
                performCompression();
                break;
            case 2:
                performExtraction();
                break;
            case 3:
                System.out.println("Goodbye!");
                System.exit(0);
        }
    }

    private void performCompression() {
        String sourceFolder = lib.getInputString("Enter Source Folder: ");
        String destinationFolder = lib.getInputString("Enter Destination Folder: ");
        String name = lib.getInputString("Enter Name: ");
        System.out.println("");
        System.out.println("------------ Result -----------");
        if (f.compressTo(sourceFolder, name+".zip", destinationFolder)) System.out.println("Zipped Successfully");; }
        

  

    private void performExtraction() {
        String sourceFile = lib.getInputString("Enter Source file: ");
        String destinationFolder = lib.getInputString("Enter Destination Folder: ");
        System.out.println("");
        System.out.println("------------ Result -----------");
        f.unzip(sourceFile, destinationFolder);
    }

 
}

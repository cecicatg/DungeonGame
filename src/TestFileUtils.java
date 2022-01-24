
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nfavela
 */
public class TestFileUtils {
    
    public static void main(String[] args) {
        File selectedFile = FileUtils.selectFile();
        //FileUtils.printFileContent( selectedFile );
        String content = FileUtils.readFileContentInString( selectedFile );
        
        System.out.println("Contenido del archivo " + selectedFile );
        System.out.println(content);
    }
    
}

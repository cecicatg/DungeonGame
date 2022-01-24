
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author nfavela
 */
public class FileUtils {
    
    public static File selectFile( ) {
        File selectedFile = null;
        JFileChooser jfc = new JFileChooser();
        
        int resp = jfc.showOpenDialog( null );
        
        if ( resp == JFileChooser.APPROVE_OPTION ) {
            selectedFile = jfc.getSelectedFile();
        }
        
        return selectedFile;
    }
    
    public static void printFileContent( File f ) {
        
        String content = readFileContentInString( f );
        System.out.println(content);
    }
    
    public static String readFileContentInString( File f ) {
        String content = null;
        
        if (f == null || !f.exists() || !f.isFile() ) {
            System.out.println( "ERROR: " + f + " no es un archivo valido...");
            return content;
        }
        
        try( FileReader reader = new FileReader( f ) ) {
            BufferedReader br = new BufferedReader( reader );
            String line = "";
            StringBuilder sb = new StringBuilder();
            while ( (line = br.readLine() ) != null ) {
                sb.append(line);
                sb.append("\n");
            } 
            content = sb.toString();
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: no se encontró el archivo " + f );
        } catch (IOException ex) {
            System.out.println("ERROR: no se pudo abrir el archivo " + f );
        }
        
        return content;
    }
    
}

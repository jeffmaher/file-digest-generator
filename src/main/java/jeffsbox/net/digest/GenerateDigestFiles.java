package jeffsbox.net.digest;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import jeffsbox.net.digest.sha1.Sha1FileDigestGenerator;

import org.apache.commons.lang3.StringUtils;

public class GenerateDigestFiles
{
  public static void main(String[] args)
  {
    if(args.length != 2)
    {
      exit("Usage: java -jar jeffsbox.net.digest.GenerateDigestFiles <digest algorithm> <directory>");
    }
    
    String digestAlgorithm = args[0]; // right now, only sha1
    String directoryPath = args[1];
    
    File dir = new File(directoryPath);
    if(!dir.exists() || !dir.isDirectory())
    {
      exit("The directory path needs to be an existing directory (it either doesn't exist or is not a directory)");
    }
    
    FileDigestGenerator digestGenerator = null;
    if(StringUtils.equalsIgnoreCase(digestAlgorithm, "sha1"))
    {
      digestGenerator = new Sha1FileDigestGenerator();
    }
    
    try
    {
      DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir.toPath());
      for(Path path : dirStream)
      {
        File file = path.toFile();
        if(file.isFile() && !file.isHidden())
        {
          String newFilePath = path.toString() + "." + digestAlgorithm.toLowerCase();
          String digest = digestGenerator.generateDigest(file);
          Files.write(Paths.get(newFilePath), digest.getBytes(), StandardOpenOption.CREATE);
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
      exit("There was a problem working with the files.");
    }
     
  }
  
  
  private static void exit(String errorMsg)
  {
    System.err.println(errorMsg);
    System.exit(-1);
  }
}

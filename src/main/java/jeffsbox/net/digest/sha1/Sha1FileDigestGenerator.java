package jeffsbox.net.digest.sha1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import jeffsbox.net.digest.FileDigestGenerator;

public class Sha1FileDigestGenerator implements FileDigestGenerator
{

  public String generateDigest(String filePath)
  {
    File file = new File(filePath);
    return generateDigest(file);
  }

  public String generateDigest(File file)
  {
    String sha1 = "";
    try
    {
      sha1 = sha1(file);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e.getMessage());
    }

    return sha1;
  }

  // Implementation from
  // http://www.javacreed.com/how-to-generate-sha1-hash-value-of-file/
  private static String sha1(final File file)
      throws NoSuchAlgorithmException, IOException
  {
    final MessageDigest messageDigest = MessageDigest.getInstance("SHA1");

    try (InputStream is = new BufferedInputStream(new FileInputStream(file)))
    {
      final byte[] buffer = new byte[1024];
      for (int read = 0; (read = is.read(buffer)) != -1;)
      {
        messageDigest.update(buffer, 0, read);
      }
    }

    // Convert the byte to hex format
    try (Formatter formatter = new Formatter())
    {
      for (final byte b : messageDigest.digest())
      {
        formatter.format("%02x", b);
      }
      return formatter.toString();
    }
  }
}

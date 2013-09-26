package jeffsbox.net.digest;

import java.io.File;

public interface FileDigestGenerator 
{
	public String generateDigest(String filePath);
	public String generateDigest(File file);
}

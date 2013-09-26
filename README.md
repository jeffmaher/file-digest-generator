# file-digest-generator

Creates checksum digest files for files within a given directory and stores them in *.(digest extension) files.

Only sha1 is currently implemented.

## Build Instructions

Requires Maven 3.x and Java 7.

`mvn package`

JAR is now in the `target` directory.


## Run Instructions

`java -jar jeffsbox.net.digest.GenerateDigestFiles <digest algorithm> <directory containing files>`

## Example

`java -jar jeffsbox.net.digest.GenerateDigestFiles sha1 /some/dir`

### Contents of `/some/dir`

Before: `file1.txt`, `file2.txt`, `file3.jpg`

After: `file1.txt`, `file1.txt.sha1`, `file2.txt`, `file2.txt.sha1`, `file3.jpg`, `file3.jpg.sha1`

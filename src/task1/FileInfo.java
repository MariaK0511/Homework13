package task1;

import java.util.Objects;

public class FileInfo {
    private String fileName;
    private String path;

    public FileInfo(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileInfo)) return false;
        FileInfo fileInfo = (FileInfo) o;
        return getFileName().equals(fileInfo.getFileName()) && getPath().equals(fileInfo.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileName(), getPath());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileName='" + fileName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}


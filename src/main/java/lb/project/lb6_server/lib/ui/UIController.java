package lb.project.lb6_server.lib.ui;

public interface UIController {

    void show(String message);

    String readString(String message);

    double readDouble(String message);

    int readInteger(String message);

    long readLong(String message);

    float readFloat(String message);

    void setFileToRead(String filePath);

    boolean isFileMode();

    void show(String message, boolean showInFileMode);
}

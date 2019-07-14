package deploy.pojo;

import java.io.File;
import java.io.Serializable;

public class DeployFile implements Serializable {
    private File file;

    private String operation;

    private String relativepath;


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRelativepath() {
        return relativepath;
    }

    public void setRelativepath(String relativepath) {
        this.relativepath = relativepath;
    }
}

package deploy.pojo;

public class DeployInfo {
    private String relativePath;
    private String operation;

    public DeployInfo(String operation, String relativePath){
        this.operation = operation;
        this.relativePath = relativePath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }


}

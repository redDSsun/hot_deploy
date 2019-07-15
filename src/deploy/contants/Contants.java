package deploy.contants;

import java.util.HashMap;

public class Contants {
    public final static String COMMAND = "git status";
    public final static String DELETE_OPERATION = "delete";
    public final static String DELETE_STATUS = "deleted";

    public final static String NEWFILE_OPERATION = "new";
    public final static String NEWFILE_STATUS = "new file";

    public final static String MODIFY_OPERATION = "modify";
    public final static String MODIFY_STATUS = "modified";

    public final static HashMap<String, String> STATUS_OPERATION_MAP= new HashMap();
    static {
        STATUS_OPERATION_MAP.put(DELETE_STATUS, DELETE_OPERATION);
        STATUS_OPERATION_MAP.put(NEWFILE_STATUS, NEWFILE_OPERATION );
        STATUS_OPERATION_MAP.put(MODIFY_STATUS, MODIFY_OPERATION );
    }


}

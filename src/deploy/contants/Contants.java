package deploy.contants;

import java.util.HashMap;

public class Contants {
    public final static String DELETE_OPERATION = "delete";
    public final static String DELETE_STATUS = "deleted";

    public final static String NEWFILE_OPERATION = "new";
    public final static String NEWFILE_STATUS = "new file";

    public final static String MODIFY_OPERATION = "modify";
    public final static String MODIFY_STATUS = "modified";

    public final static HashMap<String, String> OPERATION_STATUS_MAP= new HashMap();
    static {
        OPERATION_STATUS_MAP.put(DELETE_OPERATION, DELETE_OPERATION);
        OPERATION_STATUS_MAP.put(NEWFILE_OPERATION, NEWFILE_STATUS );
        OPERATION_STATUS_MAP.put(MODIFY_OPERATION, MODIFY_STATUS );
    }


}

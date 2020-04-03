package com.equieter.webcore.register;

import com.equieter.webcore.web.model.AbstractAction;
import com.equieter.weblog.helper.LogHelper;
import com.equieter.weblog.logger.Logger;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class RegisterHelper {

    public static Logger LOG = LogHelper.genLogger();

    private static Map<String, AbstractAction> actionMap = new HashMap<String, AbstractAction>();

    public static void register(AbstractAction action) {
        if (action == null || StringUtils.isEmpty(action.getActionName())) {
            return;
        }

        if (actionMap.containsKey(action.getActionName())) {
            LOG.errorFmt("Error Action Name Duplicate Detected !!!");
        }

        actionMap.put(action.getActionName(), action);

        LOG.debugFmt("[%s] Register Action : %s(%s) Completed!"
                , RegisterHelper.class.getSimpleName(), action.getClass().getSimpleName(), action.getActionName());
    }

    public static AbstractAction getAction(String key) {
        return actionMap.get(key);
    }


}

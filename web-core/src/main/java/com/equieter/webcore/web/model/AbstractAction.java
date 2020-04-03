package com.equieter.webcore.web.model;

import com.equieter.webcore.register.RegisterHelper;
import com.equieter.weblog.annotation.Ignore;
import com.equieter.weblog.helper.LogHelper;
import com.equieter.weblog.logger.Logger;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

@Ignore
public abstract class AbstractAction<T extends AbstractForm> {

    public static Logger LOG = LogHelper.genLogger();

    public String getActionName() {
        return this.getClass().getName().replace(".", "/");
    }

    public abstract T createNewForm();

    {
        RegisterHelper.register(this);
    }

}

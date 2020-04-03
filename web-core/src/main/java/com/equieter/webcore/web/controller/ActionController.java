package com.equieter.webcore.web.controller;

import com.equieter.webcore.register.RegisterHelper;
import com.equieter.webcore.web.model.AbstractAction;
import com.equieter.webcore.web.model.AbstractForm;
import com.equieter.weblog.annotation.Ignore;
import com.equieter.weblog.helper.LogHelper;
import com.equieter.weblog.logger.Logger;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Controller
public abstract class ActionController {

    private final static Logger LOG = LogHelper.genLogger();

    private String viewPage;
    public void setViewPage(String viewPage) {
        this.viewPage = viewPage;
    }

    private final static String ACTION_NAME = "__ACTION_NAME";
    private final static String METHOD_NAME = "__METHOD_NAME";

    @RequestMapping()
    public final ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String actionName = (String) request.getAttribute(ACTION_NAME);
        String methodName = (String) request.getAttribute(METHOD_NAME);
        LOG.debugFmt("Request ActionName: %s, MethodName: %s", actionName, methodName);
        AbstractAction absAction = RegisterHelper.getAction(actionName);
        try {
            Method method = absAction.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class, AbstractForm.class);
            AbstractForm form = absAction.createNewForm();
            BeanUtils.populate(form, request.getParameterMap());
            return (ModelAndView) method.invoke(RegisterHelper.getAction(actionName), request, response, form);
        } catch (NoSuchMethodException e) {
            LOG.errorFmt(e,"Method Not Found! Error Stack: %s", e);
        } catch (IllegalAccessException e) {
            LOG.errorFmt(e,"Forward to Method Failed! Error Stack:");
        } catch (InvocationTargetException e) {
            LOG.errorFmt(e,"Forward to Method Failed! Error Stack:");
        } catch (NullPointerException e) {
            LOG.errorFmt(e,"Forward to Method Failed! Error Stack:");
        }
        return new ModelAndView(viewPage, ACTION_NAME, actionName);
    }

}

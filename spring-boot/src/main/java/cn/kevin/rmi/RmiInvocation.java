package cn.kevin.rmi;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.remoting.support.RemoteInvocation;

import java.lang.reflect.InvocationTargetException;

public class RmiInvocation extends RemoteInvocation {

    private static final long serialVersionUID = 1L;
    private static final String dubboAttachmentsAttrName = "dubbo.attachments";

    /**
     * executed on consumer side
     */
    public RmiInvocation(MethodInvocation methodInvocation) {
        super(methodInvocation);
    }

    /**
     * Need to restore context on provider side (Though context will be overridden by Invocation's attachment
     * when ContextFilter gets executed, we will restore the attachment when Invocation is constructed, check more
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object invoke(Object targetObject) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        return super.invoke(targetObject);
    }
}

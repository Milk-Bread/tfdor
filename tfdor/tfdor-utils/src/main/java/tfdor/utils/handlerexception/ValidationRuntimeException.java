package tfdor.utils.handlerexception;

/**
 * 异常类
 *@author chepeiqing
 *@Mail chepeiqin@icloud.com
 *@Date 2016/11/20
 *@Time 下午12:10
 *@version V1.0.0
 */
public class ValidationRuntimeException extends RuntimeException {

    private Object[] obj;
    public ValidationRuntimeException(String message,Object[] obj){
        super(message);
        this.obj = obj;
    }

    public ValidationRuntimeException(String message){
        super(message);
    }

    public ValidationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationRuntimeException(Throwable cause){
        super(cause);
    }
    public Object[] getObj() {
        return obj;
    }
}

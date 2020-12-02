package com.ivan.pinellia.tool.exception;

import com.ivan.pinellia.tool.api.IResultCode;
import com.ivan.pinellia.tool.api.R;
import lombok.Getter;

/**
 * <p></p>
 *
 * @author ivan
 * @className BizException
 * @since 2020/12/2 13:58
 */

@Getter
public class BizException extends RuntimeException {

    public R result;

    public BizException(R result) {
        super(result.getMsg());
        this.result = result;
    }

    public BizException(String message){
        super(message);
    }

    public BizException(String message, Throwable cause){
        super(message, cause);
    }

    public BizException(Throwable cause){
        super(cause);
    }

}

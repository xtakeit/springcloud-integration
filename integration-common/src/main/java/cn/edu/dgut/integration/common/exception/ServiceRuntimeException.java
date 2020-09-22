package cn.edu.dgut.integration.common.exception;

/**
 *  用于抛出业务处理异常
 *
 */
public class ServiceRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -620218678949183946L;

	public ServiceRuntimeException(String message) {
		super(message);
	}

}

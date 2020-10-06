/**
 * Utility class  to support to close resources
 * @package com.plusplm.util
 * @file ResourceCloseHelper.java
 * @author 권민관
 * @date 2019. 1. 30. 오전 10:42:20
 * @version 1.0 
 * @see
 *
 * <pre>
 
 *   수정일          수정자         수정내용 
 *  -------       --------     --------------------------- 
 *  2019. 1. 30.   권민관         최초 생성 
 * 
 * Copyright (C) 2019 by (주)마린소프트 All right reserved.
 *
 * </pre>
 */
package com.plusplm.exception;

import java.io.Closeable;

public class ResourceCloseHelper {

	public static void close(Closeable  ... resources) {
		for (Closeable resource : resources) {
			if (resource != null) {
				try {
					resource.close();
				} catch (Exception ignore) {
					ignore.getMessage();
				}
			}
		}
	}
}

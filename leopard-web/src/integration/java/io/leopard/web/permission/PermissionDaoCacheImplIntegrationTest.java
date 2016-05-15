/**
 * This class has been generated by Fast Code Eclipse Plugin 
 * For more information please go to http://fast-code.sourceforge.net/
 * @author : Administrator
 * Created : 04/11/2013
 */

package io.leopard.web.permission;

import io.leopard.burrow.lang.Json;
import io.leopard.web4j.permission.Permission;
import io.leopard.web4j.permission.PermissionDaoCacheImpl;
import io.leopard.web4j.permission.PermissionKey;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class PermissionDaoCacheImplIntegrationTest extends PermissionIntegrationTest {

	@Autowired
	private PermissionDaoCacheImpl permissionDaoCacheImpl;

	@Test
	public void get() {
		Permission permission = permissionDaoCacheImpl.get(new PermissionKey("uri", "127.0.0.1"));
		Json.print(permission, "permission");

		for (int i = 0; i < 10000; i++) {
			permissionDaoCacheImpl.get(new PermissionKey("uri", "127.0.0.1"));
		}
	}
}
package io.leopard.im.huanxin;

import io.leopard.im.huanxin.model.UserResponseObject;

/**
 * 环信
 * 
 * @author 谭海潮
 *
 */
public interface HuanxinClient {

	String getToken();

	UserResponseObject addUser(String username, String password, String nickname);

	UserResponseObject getUser(String username);

}

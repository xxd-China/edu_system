package com.edu.realm;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import com.edu.pojo.Role;
import com.edu.pojo.Userlogin;
import com.edu.service.RoleService;
import com.edu.service.UserloginService;

@Component
public class LoginRealm extends AuthorizingRealm {

	@Resource(name = "userloginServiceImpl")
	private UserloginService userloginService;

	@Resource(name = "roleServiceImpl")
	private RoleService roleService;

	/**
	 * 授权 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息 当调用权限验证时，就会调用此方法
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		String username = (String) getAvailablePrincipal(principalCollection);

		Role role = null;

		try {
			Userlogin userlogin = userloginService.findByName(username);
			// 获取角色对象
			role = roleService.findByid(userlogin.getRole());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 通过用户名从数据库获取权限/角色信息
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> r = new HashSet<String>();
		if (role != null) {
			r.add(role.getRolename());
			info.setRoles(r);
		}

		return info;
	}

	/**
	 * 在这个方法中，进行身份验证 login时调用
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// System.out.println("登录校验...");
		// 用户名
		UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
		String username = passwordToken.getUsername();
		// 密码
		String password = new String(passwordToken.getPassword());
		
		Userlogin userlogin = null;
		try {
			// 根据用户名查询数据库中的密码
			userlogin = userloginService.findByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (userlogin == null) {
			// 没有该用户名
			throw new UnknownAccountException();
		} else if (!password.equals(userlogin.getPassword())) {
			// 密码错误
			// System.out.println(password);
			// System.out.println(userlogin.getPassword());
			throw new IncorrectCredentialsException();
		}

		// 身份验证通过,返回一个身份信息
		AuthenticationInfo info = new SimpleAuthenticationInfo(username, password, this.getName());

		return info;
	}

}

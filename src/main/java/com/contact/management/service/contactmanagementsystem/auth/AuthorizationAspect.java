package com.contact.management.service.contactmanagementsystem.auth;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.contact.management.service.contactmanagementsystem.common.Constant;
import com.contact.management.service.contactmanagementsystem.common.Constant.ExceptionMessage;
import com.contact.management.service.contactmanagementsystem.exceptions.UnauthorizedException;
import com.contact.management.service.contactmanagementsystem.services.AuthorizationService;

@Aspect
@Component
public class AuthorizationAspect {

  @Autowired
  private AuthorizationService authorizationService;

  @Before("@annotation(AuthorizeUser) && args(userId, ..)")
  public void authorize(JoinPoint joinPoint, Long userId) throws UnauthorizedException {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    String accessToken = request.getHeader(Constant.ACCESS_TOKEN);

    boolean authorized = authorizationService.authorizeUser(accessToken, userId);

    if (!authorized) {
      throw new UnauthorizedException(ExceptionMessage.UNAUTHORIZED_USER);
    }
  }
}

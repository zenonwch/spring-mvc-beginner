package my.spring.project.springmvc.interceptor;

import lombok.Setter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Setter
public class PromoCodeInterceptor extends HandlerInterceptorAdapter {
    private String promoCode;
    private String errorRedirect;
    private String offerRedirect;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws IOException {
        final String givenPromoCode = request.getParameter("promo");

        if (Objects.equals(promoCode, givenPromoCode)) {
            final String redirectUrl = request.getContextPath() + '/' + offerRedirect;
            response.sendRedirect(redirectUrl);
        } else {
            response.sendRedirect(errorRedirect);
        }

        return false;
    }
}
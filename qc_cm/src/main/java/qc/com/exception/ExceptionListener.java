package qc.com.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionListener implements HandlerExceptionResolver {
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		ModelAndView mv = new ModelAndView("error");
		Map<String, String> map = new HashMap<String, String>();
		if (ex.getClass().toString().indexOf("com.huipu.common.exception.EsbException") != -1) {
			EsbException e = (EsbException) ex;
			map.put("code", e.getCode());
			map.put("desc", e.getDesc().replaceAll("\n", "<br/>"));
		}else if(ex instanceof AccessDeniedException){
			try {
				response.sendError(998);
				return mv;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			map.put("code", "000000");
			if (ex.getMessage() != null) {
				map.put("desc", ex.getMessage().replaceAll("\n", "<br/>"));
			}
		}
		mv.addObject("exception", map);
		ex.printStackTrace();
		
		return mv;
	}
}

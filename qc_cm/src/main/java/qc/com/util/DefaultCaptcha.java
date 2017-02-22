package qc.com.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import qc.com.log4j.HpLogger;

/**
 *
 */
public class DefaultCaptcha {

    private int width = 57, height = 24;
    private int codeLen = 4;
    private int mailPhoneCodeLen = 6;
    private int fontSize = 16;

    private String randomCodeKey;

    public DefaultCaptcha(String randomCodeKey) {
        if (StringUtils.isBlank(randomCodeKey))
            throw new IllegalArgumentException("randomCodeKey can not be blank");
        this.randomCodeKey = randomCodeKey;
    }

    public void render(HttpServletResponse response) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        String vCode = drawGraphic(image);
        vCode = encrypt(vCode);
        Cookie cookie = new Cookie(randomCodeKey, vCode);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            ImageIO.write(image, "jpeg", sos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                sos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String renderMailOrPhone(HttpServletResponse response, String addr, String ret) {
        String rCode = new String(RandomUniqueIdGenerator.getNewNumString(mailPhoneCodeLen));

        String vCode = encrypt(addr + ":" + rCode + ":" + ret);
        Cookie cookie = new Cookie(randomCodeKey, vCode);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
        return rCode;
    }
    public String renderMailOrPhone(HttpServletResponse response, String addr, String ret,int maxAge) {
        String rCode = new String(RandomUniqueIdGenerator.getNewNumString(mailPhoneCodeLen));

        String vCode = encrypt(addr + ":" + rCode + ":" + ret);
        HpLogger.debug("后台生成效验码==============>>:cookie键 "+randomCodeKey+" cookie值："+ vCode);
        Cookie cookie = new Cookie(randomCodeKey, vCode);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
        return rCode;
    }
    private String drawGraphic(BufferedImage image) {
        Graphics g = image.createGraphics();
        Random random = new Random();
        g.setColor(new Color(200, 200, 200));
        g.drawRect(0, 0, width - 1, height - 1);
        g.setColor(new Color(255, 255, 255));
        g.fillRect(1, 1, width - 2, height - 2);
        g.setFont(new Font("Times New Roman", Font.BOLD, fontSize));

        String sRand = new String(RandomUniqueIdGenerator.getNewString(codeLen));

        // 需要动态计算X和Y的位置
        int codeY = (height + getFontHeight(g)) / 2;
        int codeXStep = (width - getFontWidth(g) * 4 - 10) / 4 + getFontWidth(g);

        for (int i = 0; i < codeLen; i++) {
            g.setColor(new Color(random.nextInt(110), random.nextInt(110), random.nextInt(110)));
            // g.drawString(String.valueOf(sRand.charAt(i)), 20 * i + 7, 24);
            g.drawString(String.valueOf(sRand.charAt(i)), 5 + i * codeXStep, codeY);

        }

        g.dispose();
        return sRand;
    }

    private static final String encrypt(String srcStr) {
        try {
            String result = "";
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(srcStr.toLowerCase().getBytes("utf-8"));
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xFF).toUpperCase();
                result += ((hex.length() == 1) ? "0" : "") + hex;
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validate(HttpServletRequest request, String inputRandomCode, String randomCodeKey) {
        if (StringUtils.isBlank(inputRandomCode))
            return false;
        try {
        	 
            inputRandomCode = encrypt(inputRandomCode.trim());
            HpLogger.debug("客户端生成效验码 ==============>>："+inputRandomCode);
             String s=getCookieValue(request, randomCodeKey);
             HpLogger.debug("服务端保存的效验码 ==============>>："+s);
             
             if(inputRandomCode.equals(s)){
            	 return true;
             }else{
            	 return false;
             }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }     
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
    	HpLogger.debug("客户端验证cookie键："+name);
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if (cookies != null){
            for (Cookie c : cookies)
            {
            	HpLogger.debug("读取客户端cookie 键:"+c.getName()+"客户端cookie 值:"+c.getValue());
                if (c.getName().equals(name)) {
                    cookie = c;
                    break;
                }
            }
        }
        return cookie != null ? cookie.getValue() : null;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCodeLen() {
        return codeLen;
    }

    public void setCodeLen(int codeLen) {
        this.codeLen = codeLen;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * 获取字体宽度
     * 
     * @param g
     * @return
     */
    private int getFontWidth(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // 获得字体宽度方法一
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D rc = fm.getStringBounds("M", g2);

        return (int) rc.getWidth();
    }

    /**
     * 获取字体高度
     * 
     * @param g
     * @return
     */
    private int getFontHeight(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // 获得字体宽度方法一
        FontMetrics fm = g2.getFontMetrics();

        return fm.getAscent();
    }
}

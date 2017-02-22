/**
 * 
 */
package qc.com.conf;

import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;

/**
 * @author liuqing
 * 
 */
public class XMLConfig{
	private static final String encoding = "UTF8";
	private Document document = null ;
	public XMLConfig(InputStream is) {
		SAXReader reader = new SAXReader();
		reader.setEncoding(encoding);
		try {
			document = reader.read(is);
		} catch (Exception e) {
			e.printStackTrace();
			document = DocumentHelper.createDocument();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public Document getDocument(){
		return document;
	}
	
	
}

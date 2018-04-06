package teste.download;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
	
	protected static final String CONTENT_TYPE_CSV = "text/csv";
    protected static final String CONTENT_TYPE_HEADER = "Content-Disposition";
    protected static final String CONTENT_TYPE_ATTACHMENT = "attachment; filename=\"export_cart.csv\"";
    protected static final String UNICODE_TYPE = "UTF-8";

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void exportCsvAnalytics(
            final Model model, final HttpServletResponse response, final HttpServletRequest request) {

        try (ServletOutputStream outputStream = response.getOutputStream(); BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream, UNICODE_TYPE))) {
        	response.setContentType(CONTENT_TYPE_CSV);
        	response.setHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_ATTACHMENT);
            
        	for (int i = 0; i < 60; i++) {
        		List<String> lines = generate(1000);
        		out.write(lines.toString());
        		
        		out.flush();
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private List<String> generate(int qtdLines) {
    	try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	List<String> retorno = new ArrayList<>();
    	for (int i = 0; i < qtdLines; i++) {
    		retorno.add("[Thu Nov 16 17:40:42 BRST 2017;ABERTO;1;1750000011;345.75;0;KARINA DELFINO DANTAS;33244310850;null;0.0;0.0;1;87174136;null;15.0;1;TOP1;null;21.99;0.0;;null;null;null;329.85;Caixa;Thu Nov 16 17:40:51 BRST 2017;1;1;8823174496299;8822113763372]");
    	}    		
    	
    	return retorno;
    }

}
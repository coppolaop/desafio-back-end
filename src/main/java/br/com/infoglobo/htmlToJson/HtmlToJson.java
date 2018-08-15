package br.com.infoglobo.htmlToJson;

public class HtmlToJson {

	public String convert(String html) {
		String [] splitedArray = html.split("<", -1);
		boolean hasUl = false;
		String json = "";
		
		json += "[" + System.getProperty("line.separator");
		for(String content : splitedArray) {
			if(content.startsWith("img")) {
				String[] x = content.split("src",-1);
				String[] y = x[1].split("\"", -1);
				y[1] = y[1].substring(0, y[1].length()-1);//retirada da \
				
				json += "\t{" + System.getProperty("line.separator");
				json += "\t\t\"type\":\"image\"," + System.getProperty("line.separator");
				json += "\t\t\"content\":\""+y[1]+"\"" + System.getProperty("line.separator");
				json += "\t}," + System.getProperty("line.separator");
			}else if(content.startsWith("ul")) {
				hasUl = true;
				json += "\t{" + System.getProperty("line.separator");
				json += "\t\t\"type\":\"links\"," + System.getProperty("line.separator");
				json += "\t\t\"content\":[" + System.getProperty("line.separator");
			}else if(hasUl ) {
				if(content.startsWith("a ")) {
					String[] y = content.split("\"", -1);
					y[1] = y[1].substring(0, y[1].length()-1);//retirada da \
					json += "\t\t\t\"" + y[1] + "\"," + System.getProperty("line.separator");
				}else if(content.startsWith("\\/ul")) {
					hasUl = false;
					json += "\t\t]" + System.getProperty("line.separator");
					json += "\t}," + System.getProperty("line.separator");
				}
			}else if(content.startsWith("p>")) {
				content = content.substring(2, content.length()-1);//retirada do p> e da \
				json += "\t{" + System.getProperty("line.separator");
				json += "\t\t\"type\":\"text\"," + System.getProperty("line.separator");
				json += "\t\t\"content\":\""+content+"\"" + System.getProperty("line.separator");
				json += "\t}," + System.getProperty("line.separator");
			}
		}
		json += "]";
		System.out.println(json);
		return json;
	}
	
	public static void main(String[] args) {
		String html = "<div class=\\\"foto componente_materia midia-largura-620\\\">\\r\\n\\t<img alt=\\\"Ford Territory (Foto: divulgação)\\\" height=\\\"413\\\" id=\\\"288893\\\" src=\\\"https://s2.glbimg.com/kEthIsW9o39DotqySVwOdiThM2Y=/620x413/e.glbimg.com/og/ed/f/original/2018/08/10/2019-ford-territory-china.jpg\\\" title=\\\"Ford Territory (Foto: divulgação)\\\" width=\\\"620\\\" /><label class=\\\"foto-legenda\\\">Ford Territory (Foto: divulga&ccedil;&atilde;o)<\\/label><\\/div>\\r\\n<p>\\r\\n\\t&nbsp;<\\/p>\\r\\n<p>\\r\\n\\tA <a href=\\\"https://revistaautoesporte.globo.com/carros/ford/\\\">Ford<\\/a> apresentou o novo SUV<strong> Territory<\\/strong> na China. Desenvolvido em conjunto com a Jianling Motors Corporation (JMC), o utilit&aacute;rio esportivo tem previs&atilde;o de chegar ao mercado j&aacute; em meados de <strong>2019<\\/strong>. Mas em contato com a assessoria da <strong>Ford<\\/strong>, a primeira informa&ccedil;&atilde;o &eacute; a de que realmente o Territory ser&aacute; voltado apenas para o mercado chin&ecirc;s.<\\/p>\\r\\n<div class=\\\"saibamais componente_materia expandido\\\">\\r\\n\\t<strong>saiba mais<\\/strong>\\r\\n\\t<ul>\\r\\n\\t\\t<li>\\r\\n\\t\\t\\t<a href=\\\"https://revistaautoesporte.globo.com/Noticias/noticia/2018/08/toyota-registra-patente-da-nova-geracao-do-rav-4-no-brasil.html\\\">Toyota registra nova gera&ccedil;&atilde;o do RAV4 no Brasil<\\/a><\\/li>\\r\\n\\t\\t<li>\\r\\n\\t\\t\\t<a href=\\\"https://revistaautoesporte.globo.com/Noticias/noticia/2018/07/jeep-registra-suv-de-sete-lugares-no-brasil.html\\\">Jeep registra SUV de sete lugares no Brasil<\\/a><\\/li>\\r\\n\\t\\t<li>\\r\\n\\t\\t\\t<a href=\\\"https://revistaautoesporte.globo.com/Noticias/noticia/2018/08/ford-comemora-marca-de-10-milhoes-de-mustangs-produzidos.html\\\">Ford comemora a marca de 10 milh&otilde;es de Mustangs produzidos<\\/a><\\/li>\\r\\n\\t<\\/ul>\\r\\n<\\/div>\\r\\n<p>\\r\\n\\t&nbsp;<\\/p>\\r\\n<p>\\r\\n\\tA montadora ainda n&atilde;o divulgou qual gama de motoriza&ccedil;&otilde;es ir&aacute; disponibilizar para o ve&iacute;culo. Parte da m&iacute;dia especializada aposta que o utilit&aacute;rio abrigue apenas propulsores <a href=\\\"https://revistaautoesporte.globo.com/Videos/noticia/2018/06/video-toyota-prius-esta-na-hora-de-ter-um-carro-hibrido-no-brasil.html\\\">h&iacute;bridos<\\/a>, <strong>h&iacute;bridos leves e h&iacute;bridos plug-ins<\\/strong>. Outros afirmam que o SUV dever&aacute; trazer propulsores a combust&atilde;o movidos a gasolina.<\\/p>\\r\\n<p>\\r\\n\\tCerto mesmo, segundo a Ford, &eacute; que o Territory ser&aacute; um jipe mais acess&iacute;vel, para um p&uacute;blico que deseja entrar no mundo dos SUV. Ainda assim, o modelo ainda contar&aacute; com servi&ccedil;os avan&ccedil;ados de <strong>carros conectados<\\/strong> e com tecnologias de <strong>assist&ecirc;ncia ao condutor<\\/strong>.<br />\\r\\n\\t<br />\\r\\n\\tA estrat&eacute;gia, no entanto, n&atilde;o proibir&aacute; a montadora de oferecer um pre&ccedil;o atrativo (ainda n&atilde;o definido) para o consumidor.&nbsp;De s&eacute;rie, o Territory ainda oferecer&aacute; <strong>far&oacute;is e lanternas de led<\\/strong>, fun&ccedil;&otilde;es de assistencia de pr&eacute;-colis&atilde;o e <strong>controle de cruzeiro adaptativo<\\/strong>.<\\/p>\\r\\n<div class=\\\"foto componente_materia midia-largura-620\\\">\\r\\n\\t<img alt=\\\"Ford Territory (Foto: divulgação)\\\" height=\\\"300\\\" id=\\\"288894\\\" src=\\\"https://s2.glbimg.com/S3YM02Cgp_a2YQAYw6eZk6yEDi0=/620x300/e.glbimg.com/og/ed/f/original/2018/08/10/dims.jpg\\\" title=\\\"Ford Territory (Foto: divulgação)\\\" width=\\\"620\\\" /><label class=\\\"foto-legenda\\\">Ford Territory (Foto: divulga&ccedil;&atilde;o)<\\/label><\\/div>\\r\\n<p>\\r\\n\\t&nbsp;<\\/p>\\r\\n";
		HtmlToJson htj = new HtmlToJson();
		htj.convert(html);
	}
}

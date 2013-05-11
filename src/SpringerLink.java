import java.net.MalformedURLException;

public class SpringerLink {

	public static void main(String[] args) throws MalformedURLException {
		String url = "http://link.springer.com/book/10.1007/978-3-8348-2002-0";
						
		System.out.println("Parse page...");

		Book book = new Book();
		Parser page = new Parser(url, book);
		page.run();
	}

}

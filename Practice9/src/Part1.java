
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Part1")
public class Part1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int x = Integer.parseInt(request.getParameter("x")), y = Integer.parseInt(request.getParameter("y")),
				result = 0;
		String operation = request.getParameter("op");
		switch (operation) {
		case "minus":
			result = x - y;
			;
			break;
		case "plus":
			result = x + y;
			break;
		}
		StringBuilder content = new StringBuilder();
		content.append("<html><body>").append("<h3>Result: ").append(result).append("</h3>")
				.append("</br><a href='calc.html'>Home</a>").append("</body></html>");
		response.getWriter().println(content);
	}

}


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Part2")
public class Part2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("vote");
		String name = request.getParameter("name");
		switch (operation) {
		case "Football":
			VoteData.footbal++;
			VoteData.footbalName=VoteData.footbalName + name+" ";
			;
			break;
		case "Biathlon":
			VoteData.biathlon++;
			VoteData.biathlonName=VoteData.biathlonName + name+" ";
			break;
		case "Basketball":
			VoteData.basketball++;
			VoteData.basketballName=VoteData.basketballName + name+" ";
			break;
		}
		StringBuilder content = new StringBuilder();
		content.append("<html><body>").append("<p>Football ").append(VoteData.footbal).append(" ").append(VoteData.footbalName).append("</p>")
				.append("<p>Biathlon ").append(VoteData.biathlon).append(" ").append(VoteData.biathlonName).append("</p>").append("<p>Basketball ")
				.append(VoteData.basketball).append(" ").append(VoteData.basketballName).append("</p>").append("</body></html>");
		response.getWriter().println(content);
	}

}

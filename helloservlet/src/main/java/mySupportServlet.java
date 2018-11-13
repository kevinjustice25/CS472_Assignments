import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/support")
public class mySupportServlet extends HttpServlet {
    private int ticketNumber = 0;

    private int getNextTicketNumber(){
        return ticketNumber++;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Get Help Form</title></head><body>");
        out.print("<form method='post'>");
        out.print("<label>Enter Name:<input id='name' name='name'/></label>");
        out.print("<label>Enter Email:<input id='email' name='email'/></label>");
        out.print("<label>Enter Problem Type:<input id='problemsummary' name='problemsummary'/></label>");
        out.print("<textarea id='textarea' rows='5' cols='50' name='description'></textarea>");
        out.print("<p>Please click the button</p>");
        out.print("<input type='submit' value='Click me'/>");
        out.print("</form>");
        out.print("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String problemsummary = req.getParameter("problemsummary");
        String description = req.getParameter("description");
        PrintWriter out = resp.getWriter();
        ServletContext sc = this.getServletContext();
        out.print("<html><head><title>Help is on the way!</title></head><body>");
        out.print("<p>Thank you! " + name + " for contacting us. We should receive reply from us with in 24 hrs in your " +
                        "email address " + email + ". Let us know in our support email " +
                         sc.getInitParameter("SUPPORT_EMAIL") + " if you don’t receive reply within 24 hrs. " +
                "Please be sure to attach your reference ticket number <strong>" +
                getNextTicketNumber() + "</strong> in your email.");
        out.print("</p></body></html>");
    }
}

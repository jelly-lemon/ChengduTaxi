package interfaces;

import javax.servlet.http.HttpServletRequest;

public interface IImportDataServlet {
    void showError();
    void showSuccessful();
    HttpServletRequest getRequest();
}

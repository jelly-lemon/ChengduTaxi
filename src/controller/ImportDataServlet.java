package controller;

import interfaces.IImportDataServlet;
import model.ImportDataModel;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ImportDataServlet", urlPatterns = "/ImportDataServlet")
public class ImportDataServlet extends javax.servlet.http.HttpServlet implements IImportDataServlet {
    private ImportDataModel mImportDataModel = new ImportDataModel(this);
    HttpServletRequest mRequest;
    HttpServletResponse mResponse;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        mRequest = request;
        mResponse = response;

        mRequest.setCharacterEncoding("utf8");
        mResponse.setCharacterEncoding("utf8");

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }


        System.out.println("start to resolve the file...");
        // 保存文件
        mImportDataModel.saveFile(getServletContext());
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    public void showError() {
        try {
            getServletContext().getRequestDispatcher("/Error.jsp").forward(mRequest, mResponse);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showSuccessful() {
        // 跳转到 Successful.jsp
        try {
            getServletContext().getRequestDispatcher("/Successful.jsp").forward(mRequest, mResponse);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HttpServletRequest getRequest() {
        return mRequest;
    }


}

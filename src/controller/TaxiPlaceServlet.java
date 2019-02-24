package controller;

import interfaces.ITaxiPlaceServlet;
import model.TaxiPlaceModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "TaxiPlaceServlet", urlPatterns = "/TaxiPlaceServlet")
public class TaxiPlaceServlet extends HttpServlet implements ITaxiPlaceServlet {
    TaxiPlaceModel taxiPlaceModel = new TaxiPlaceModel(this);
    HttpServletRequest mRequest;
    HttpServletResponse mResponse;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String TID = mRequest.getParameter("TID");
        String start_time = mRequest.getParameter("start_time");
        String end_time = mRequest.getParameter("end_time");


        if (start_time.equals("")) {
            start_time = "00:00";
        }
        if (end_time.equals("")) {
            end_time = "23:59";
        }

        System.out.println("TID:" + TID + "  start_time:" + start_time + "   end_time:" + end_time);


        taxiPlaceModel.getSelectedPoint(TID, start_time, end_time);
        saveSelection(TID, start_time, end_time);

        goJSP();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mRequest = request;
        mResponse = response;

        mRequest.setCharacterEncoding("utf8");
        mResponse.setCharacterEncoding("utf8");



        // 初始化可选择的 TID
        taxiPlaceModel.getAllTID();
        // 获取所有坐标点
        taxiPlaceModel.getAllPoint(null);
        saveSelection("all", "00:00", "23:59");


        goJSP();
    }

    @Override
    public void showResult(String str) {
        HttpSession session = mRequest.getSession();
        session.setAttribute("point", str);
    }

    @Override
    public void showAllTID(ArrayList<Integer> list) {
        HttpSession session = mRequest.getSession();
        session.setAttribute("TIDList", list);

    }

    public void goJSP() {
        // 跳转到 TaxiPlace.jsp
        try {
            getServletContext().getRequestDispatcher("/TaxiPlace.jsp").forward(mRequest, mResponse);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSelection(String TID, String start_time, String end_time) {
        HttpSession session = mRequest.getSession();
        session.setAttribute("TID", TID);
        session.setAttribute("start_time", start_time);
        session.setAttribute("end_time", end_time);
    }
}

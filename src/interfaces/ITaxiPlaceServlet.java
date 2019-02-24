package interfaces;

import org.json.JSONArray;

import java.util.ArrayList;

public interface ITaxiPlaceServlet {
    void showResult(String str);
    void showAllTID(ArrayList<Integer> list);
}

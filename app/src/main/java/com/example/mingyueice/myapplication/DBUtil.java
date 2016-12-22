package com.example.mingyueice.myapplication;

/**
 * Created by mingyueice on 2016/12/15.
 */
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private HttpConnSoap Soap = new HttpConnSoap();

    public static Connection getConnection() {
        Connection con = null;
        try {
            //Class.forName("org.gjt.mm.mysql.Driver");
            //con=DriverManager.getConnection("jdbc:mysql://192.168.0.106:3306/test?useUnicode=true&characterEncoding=UTF-8","root","initial");
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return con;
    }

    /**
     * 获取所有货物的信息
     *
     * @return
     */
    public List<HashMap<String, String>> getAllInfo() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        arrayList.clear();
        brrayList.clear();
        crrayList.clear();

        crrayList = Soap.GetWebServre("selectAllCargoInfor", arrayList, brrayList);

        HashMap<String, String> tempHash = new HashMap<String, String>();
        tempHash.put("name", "name");
        tempHash.put("conid", "conid");
        list.add(tempHash);

        for (int j = 0; j < crrayList.size(); j += 3) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("name", crrayList.get(j));
            hashMap.put("conid", crrayList.get(j + 1));
            list.add(hashMap);
        }

        return list;
    }

    /**
     * 增加一条货物信息
     *
     * @return
     */
    public void insertCargoInfo(String name, String conid) {

        arrayList.clear();
        brrayList.clear();

        arrayList.add("name");
        arrayList.add("conid");
        brrayList.add(name);
        brrayList.add(conid);

        Soap.GetWebServre("insertCargoInfo", arrayList, brrayList);
    }

    /**
     * 删除一条货物信息
     *
     * @return
     */
    public void deleteCargoInfo(String conid) {

        arrayList.clear();
        brrayList.clear();

        arrayList.add("conid");
        brrayList.add(conid);

        Soap.GetWebServre("deleteCargoInfo", arrayList, brrayList);
    }
}
package net.rafaelaznar.operaciones;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.rafaelaznar.dao.ProductoDao_Mysql;
import net.rafaelaznar.helper.Conexion;
import net.rafaelaznar.helper.FilterBean;

/**
 *
 * @author Pedro Benito
 */
public class ProductoGetregisters implements GenericOperation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String data;
        try {
            ArrayList<FilterBean> alFilter = new ArrayList<>();
            if (request.getParameter("filter") != null) {
                if (request.getParameter("filteroperator") != null) {
                    if (request.getParameter("filtervalue") != null) {
                        FilterBean oFilterBean = new FilterBean();
                        oFilterBean.setFilter(request.getParameter("filter"));
                        oFilterBean.setFilterOperator(request.getParameter("filteroperator"));
                        oFilterBean.setFilterValue(request.getParameter("filtervalue"));
                        oFilterBean.setFilterOrigin("user");
                        alFilter.add(oFilterBean);
                    } else {
                        alFilter = null;
                    }
                } else {
                    alFilter = null;
                }
            } else {
                alFilter = null;
            }
            if (request.getParameter("systemfilter") != null) {
                if (request.getParameter("systemfilteroperator") != null) {
                    if (request.getParameter("systemfiltervalue") != null) {
                        FilterBean oFilterBean = new FilterBean();
                        oFilterBean.setFilter(request.getParameter("systemfilter"));
                        oFilterBean.setFilterOperator(request.getParameter("systemfilteroperator"));
                        oFilterBean.setFilterValue(request.getParameter("systemfiltervalue"));
                        oFilterBean.setFilterOrigin("system");
                        alFilter.add(oFilterBean);
                    }
                }
            }
            ProductoDao_Mysql oProductoDAO = new ProductoDao_Mysql(Conexion.getConection());
            int pages = oProductoDAO.getCount(alFilter);
            data = "{\"data\":\"" + Integer.toString(pages) + "\"}";
            return data;
        } catch (Exception e) {
            throw new ServletException("ProductoGetregistersJson: View Error: " + e.getMessage());
        }
    }
}
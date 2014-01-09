package net.rafaelaznar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.rafaelaznar.bean.ProductoBean;
import net.rafaelaznar.data.MysqlData;
import net.rafaelaznar.helper.Conexion;
import net.rafaelaznar.helper.FilterBean;

/**
 *
 * @author Pedro Benito
 */
public class ProductoDao_Mysql implements ProductoDao {

    private final MysqlData oMysql;
    private final Conexion.Tipo_conexion enumTipoConexion;

    public ProductoDao_Mysql(Conexion.Tipo_conexion tipoConexion) throws Exception {
        oMysql = new MysqlData();
        enumTipoConexion = tipoConexion;
    }

    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("producto", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ProductoDao.getPages: Error: " + e.getMessage());
        }
    }

    @Override
    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("producto", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ProductoDao.getCount: Error: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<ProductoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<ProductoBean> arrProducto = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("producto", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                ProductoBean oProductoBean = new ProductoBean(iterador.next());
                arrProducto.add(this.get(oProductoBean));
            }
            oMysql.desconexion();
            return arrProducto;
        } catch (Exception e) {
            throw new Exception("ProductoDao.getPage: Error: " + e.getMessage());
        }
    }

    @Override
    public ProductoBean get(ProductoBean oProductoBean) throws Exception {
        if (oProductoBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("producto", oProductoBean.getId())) {
                    oProductoBean.setId(0);
                } else {
                    oProductoBean.setCodigo(oMysql.getOne("producto", "codigo", oProductoBean.getId()));
                    oProductoBean.setDescripcion(oMysql.getOne("producto", "descripcion", oProductoBean.getId()));
                    oProductoBean.setPrecio(oMysql.getOne("producto", "precio", oProductoBean.getId()));
                    oProductoBean.setId_tipoproducto(Integer.parseInt(oMysql.getOne("producto", "id_tipoproducto", oProductoBean.getId())));
                }
            } catch (Exception e) {
                throw new Exception("ProductoDao.getProducto: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oProductoBean.setId(0);
        }
        return oProductoBean;
    }

    @Override
    public ProductoBean set(ProductoBean oProductoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oProductoBean.getId() == 0) {
                oProductoBean.setId(oMysql.insertOne("producto"));
            }
            oMysql.updateOne(oProductoBean.getId(), "producto", "codigo", oProductoBean.getCodigo());
            oMysql.updateOne(oProductoBean.getId(), "producto", "descripcion", oProductoBean.getDescripcion());
            oMysql.updateOne(oProductoBean.getId(), "producto", "precio", oProductoBean.getPrecio());
            oMysql.updateOne(oProductoBean.getId(), "producto", "id_tipoproducto", Integer.toString(oProductoBean.getId_tipoproducto()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("ProductoDao.setProducto: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oProductoBean;
    }

    @Override
    public void remove(ProductoBean oProductoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oProductoBean.getId(), "producto");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ProductoDao.removeProducto: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    @Override
    public ArrayList<String> getColumnsNames() throws Exception {
        ArrayList<String> alColumns = null;
        try {
            oMysql.conexion(enumTipoConexion);
            alColumns = oMysql.getColumnsName("producto", Conexion.getDatabaseName());
            oMysql.desconexion();

        } catch (Exception e) {
            throw new Exception("ProductoDao.removeProducto: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return alColumns;
    }
}

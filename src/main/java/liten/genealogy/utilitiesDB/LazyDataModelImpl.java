/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.utilitiesDB;

/**
 *
 * @author bayomock.a
 */
import jakarta.faces.context.FacesContext;
import java.util.List;
import java.util.Map;
import jakarta.faces.model.ListDataModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author FACULTY
 */
public class LazyDataModelImpl<T> extends LazyDataModel<T> {

    private List<Object> datasource;
    Query queryList = null, queryCountRows = null;
    String queryListString = "";
    int curFirst = 0;
    int totalRowCount = 0;

    public <T> LazyDataModelImpl() {
        super();
    }

    public <T> LazyDataModelImpl(List<Object> datasource) {
        super();
        this.datasource = datasource;
    }

    public void determineTotalRowsCount() {
        totalRowCount = ((Long) queryCountRows.getSingleResult()).intValue();
        setTotalRowCount(totalRowCount);
    }

   @Override
    public int count(Map<String, FilterMeta> filterBy){
        return totalRowCount;
    }

    @Override
    public List<T> load(int first, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
        //paginate
        try {
            curFirst = first;
            if (totalRowCount > pageSize) {
                queryList.setFirstResult(first);
                queryList.setMaxResults(pageSize);
                this.datasource = queryList.getResultList();
            } else {
                this.datasource = queryList.getResultList();
            }
        } catch (IndexOutOfBoundsException e) {
            this.datasource = queryList.getResultList().subList(first, first + (totalRowCount % pageSize));
        }
        return (List<T>) this.datasource;
    }

    public void getDatasource(List<Object> datasource) {
        this.datasource = datasource;
    }

    public List<Object> getDatasource() {
        return this.datasource;
    }

    public void setCurFirst(int curFurst) {
        this.curFirst = curFirst;
    }

    public int getCurFirst() {
        return this.curFirst;
    }

    public void setQueryList(Query queryList) {
        this.queryList = queryList;
    }

    public Query getQueryList() {
        return this.queryList;
    }

    public void setQueryCountRows(Query queryCountRows) {
        this.queryCountRows = queryCountRows;
    }

    public Query getQueryCountRows() {
        return this.queryCountRows;
    }

    public void setTotalRowCount(int totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    public int getTotalRowCount() {
        return this.totalRowCount;
    }
}

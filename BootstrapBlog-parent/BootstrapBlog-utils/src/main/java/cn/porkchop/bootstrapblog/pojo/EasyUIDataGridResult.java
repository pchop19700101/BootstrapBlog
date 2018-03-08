package cn.porkchop.bootstrapblog.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EasyUIDataGridResult<T> implements Serializable {
    private Long total;
    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public EasyUIDataGridResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}


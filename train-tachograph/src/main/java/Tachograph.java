import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Date;

public class Tachograph {
    Table<Long, Integer, Integer> table;

    public Tachograph (){
       table = HashBasedTable.create();
    }

    public void update(long n, int a, int b){
        table.put(n, a, b);
    }
}

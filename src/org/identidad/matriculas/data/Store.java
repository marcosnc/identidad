package org.identidad.matriculas.data;

import java.util.Date;
import java.util.List;

public interface Store {

    void add(String matricula, Date fecha);

    List<String> query(String filter);

}

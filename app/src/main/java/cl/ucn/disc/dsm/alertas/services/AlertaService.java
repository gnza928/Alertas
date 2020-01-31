/*
 * Copyright (c) @author Gonzalo Nieto Berríos
 */

package cl.ucn.disc.dsm.alertas.services;

import cl.ucn.disc.dsm.alertas.model.Alerta;
import java.util.List;

public interface AlertaService {

  List<Alerta> getAlertas(final int pageSize);


}

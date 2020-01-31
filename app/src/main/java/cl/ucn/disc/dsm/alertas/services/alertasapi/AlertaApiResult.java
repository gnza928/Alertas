/*
 * Copyright (c) @author Gonzalo Nieto Berríos
 */

package cl.ucn.disc.dsm.alertas.services.alertasapi;

import cl.ucn.disc.dsm.alertas.model.Alerta;
import java.util.ArrayList;
import java.util.List;

public class AlertaApiResult {
  public String status;
  public long totalResults;
  public List<Alerta> ultimos_sismos = new ArrayList<Alerta>();

}

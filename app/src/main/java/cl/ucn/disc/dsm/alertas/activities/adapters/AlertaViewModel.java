/*
 * Copyright (c) @author Gonzalo Nieto Berríos
 */

package cl.ucn.disc.dsm.alertas.activities.adapters;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cl.ucn.disc.dsm.alertas.model.Alerta;
import cl.ucn.disc.dsm.alertas.services.alertasapi.AlertaApiService;
import cl.ucn.disc.dsm.alertas.services.AlertaService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertaViewModel extends ViewModel {

  private static final Logger log = LoggerFactory.getLogger(AlertaViewModel.class);

  private final MutableLiveData<List<Alerta>> theAlertas = new MutableLiveData<>();

  private final MutableLiveData<Exception> theException = new MutableLiveData<>();

  private AlertaService alertaService = new AlertaApiService();

  public LiveData<List<Alerta>> getAlertas() {
    return this.theAlertas;
  }

  public LiveData<Exception> getException() {
    return this.theException;
  }

  public int refresh() {

    try {

      // 1. Get the list of noticia from NewsApi
      final List<Alerta> alertas = this.alertaService.getAlertas(PAGE_SIZE);

      // 2. Set the values (NEED to be in background)
      this.theAlertas.postValue(alertas);

      // 3. All ok!
      return alertas.size();

    } catch (final Exception ex) {

      log.error("Error", ex);

      // 2. Set the exception
      this.theException.postValue(ex);

      // 3. All error!
      return -1;

    }

  }
}

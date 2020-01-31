/*
 * Copyright (c) @author Gonzalo Nieto Berríos
 */

package cl.ucn.disc.dsm.alertas.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import cl.ucn.disc.dsm.alertas.R;
import android.os.AsyncTask;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import cl.ucn.disc.dsm.alertas.services.AlertaService;
import es.dmoral.toasty.Toasty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cl.ucn.disc.dsm.alertas.databinding.ActivityMainBinding;
import cl.ucn.disc.dsm.alertas.activities.adapters.AlertaAdapter;
import cl.ucn.disc.dsm.alertas.activities.adapters.AlertaViewModel;

public class MainActivity extends AppCompatActivity {

  private static final Logger log = LoggerFactory.getLogger(MainActivity.class);
  private AlertaAdapter alertaAdapter;
  private AlertaService alertaService;
  private AlertaViewModel alertaViewModel;
  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Inflate the layout
    this.binding = ActivityMainBinding.inflate(getLayoutInflater());

    // Assign to the main view.
    setContentView(this.binding.getRoot());

    // Set the toolbar
    {
      this.setSupportActionBar(this.binding.toolbar);
    }

    // The refresh
    {
      binding.swlRefresh.setOnRefreshListener(() -> {
        log.debug("Refreshing ..");
      });
    }

    // The Adapter + RecyclerView
    {
      // The Adapter
      this.alertaAdapter = new AlertaAdapter();

      // The Adapter
      this.binding.rvNoticias.setAdapter(this.alertaAdapter);

      // The layout (ListView)
      this.binding.rvNoticias.setLayoutManager(new LinearLayoutManager(this));

      // The separator (line)
      this.binding.rvNoticias.addItemDecoration(
          new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
      );

    }

    // The NoticiaService
    //this.noticiaService = (NoticiaService) new NewsApiNoticiaService();

    // The refresh
    {
      this.binding.swlRefresh.setOnRefreshListener(() -> {
        log.debug("Refreshing ..");

        // Run in background
        AsyncTask.execute(() -> {

          // All ok
          final int size = this.alertaViewModel.refresh();
          if (size != -1) {

            // In the UI
            runOnUiThread(() -> {

              // Hide the loading
              this.binding.swlRefresh.setRefreshing(false);

              // Show a message.
              Toasty.success(this, "Noticias fetched: " + size, Toast.LENGTH_SHORT, true).show();

            });
          }

        });

      });
    }

    // The ViewModel
    {
      this.alertaViewModel = new ViewModelProvider(this).get(AlertaViewModel.class);

      // Observe the list of noticia
      this.alertaViewModel.getNoticias().observe(this,
          noticias -> this.alertaAdapter.setAlertas(noticias));

      // Observe the exception
      this.alertaViewModel.getException().observe(this, this::showException);

    }
  }

  /**
   * Show the exception.
   *
   * @param exception to use.
   */
  private void showException(final Exception exception) {

    // Hide the loading
    this.binding.swlRefresh.setRefreshing(false);

    // Build the message
    final StringBuilder sb = new StringBuilder("Error: ");
    sb.append(exception.getMessage());
    if (exception.getCause() != null) {
      sb.append(", ");
      sb.append(exception.getCause().getMessage());
    }

    Toasty.error(this, sb.toString(), Toast.LENGTH_LONG, true).show();

  }
}


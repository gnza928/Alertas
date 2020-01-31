/*
 * Copyright (c) @author Gonzalo Nieto Berríos
 */

package cl.ucn.disc.dsm.alertas.activities.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.alertas.R;
import cl.ucn.disc.dsm.alertas.databinding.PopupImageBinding;
import cl.ucn.disc.dsm.alertas.databinding.RowAlertaBinding;
import cl.ucn.disc.dsm.alertas.model.Alerta;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertaAdapter extends RecyclerView.Adapter<AlertaViewHolder>{

  private List<Alerta> theAlertas;

  private static final Logger log = LoggerFactory.getLogger(AlertaAdapter.class);

  public AlertaAdapter() {

    this.theAlertas = new ArrayList<>();

    this.setHasStableIds(true);
  }

  public void setAlertas(final List<Alerta> alertas) {

    this.theAlertas = alertas;

    this.notifyDataSetChanged();
  }

  public AlertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

    final RowAlertaBinding rowAlertaBinding = RowAlertaBinding.inflate(
        layoutInflater,
        parent,
        false
    );

    final AlertaViewHolder alertaViewHolder = new AlertaViewHolder(rowAlertaBinding);


    // Click in the row
    rowAlertaBinding.getRoot().setOnClickListener(view -> {

      // The position
      final int position = alertaViewHolder.getAdapterPosition();

      // The id
      final long id = alertaViewHolder.getItemId();
      log.debug("Click! position: {}, id: {}.", position, Long.toHexString(id));


      final Alerta alerta = this.theAlertas.get(position);

      log.debug("Link: {}.", alerta.getUrl());
      if (alerta.getUrl() != null) {

        // Open the browser
        parent.getContext().startActivity(
            new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(alerta.getUrl())
            )
        );
      }

    });

    return alertaViewHolder;

  }

  @Override
  public void onBindViewHolder(@NonNull AlertaViewHolder holder, int position) {
    holder.bind(this.theAlertas.get(position));
  }

  /**
   * Returns the total number of items in the data set held by the adapter.
   */
  @Override
  public int getItemCount() {
    return this.theAlertas.size();
  }

  /**
   * Return the stable ID for the item at position.
   */
  @Override
  public long getItemId(int position) {
    return this.theAlertas.get(position).getId();
  }

}

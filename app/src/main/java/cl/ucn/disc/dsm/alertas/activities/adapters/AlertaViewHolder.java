/*
 * Copyright (c) @author Gonzalo Nieto Berríos
 */

package cl.ucn.disc.dsm.alertas.activities.adapters;

import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.alertas.R;
import cl.ucn.disc.dsm.alertas.databinding.RowAlertaBinding;
import cl.ucn.disc.dsm.alertas.model.Alerta;
import java.util.Date;
import org.ocpsoft.prettytime.PrettyTime;
import org.threeten.bp.DateTimeUtils;

public class AlertaViewHolder extends RecyclerView.ViewHolder{
  /**
   * The Bindings
   */
  private final RowAlertaBinding binding;

  /**
   * The Date formatter
   */
  private static final PrettyTime PRETTY_TIME = new PrettyTime();

  public AlertaViewHolder(RowAlertaBinding rowAlertaBinding) {
    super(rowAlertaBinding.getRoot());
    this.binding = rowAlertaBinding;
  }

  public void bind(final Alerta alerta) {

    this.binding.tvMagnitude.setText(String.valueOf(alerta.getMagnitude()));
    this.binding.tvReference.setText(alerta.getReference());
    this.binding.tvLatitude.setText(String.valueOf(alerta.getLatitude()));
    this.binding.tvLongitude.setText(String.valueOf(alerta.getLongitude()));
    this.binding.tvScale.setText(alerta.getScale());
    this.binding.tvChileanTime.setText(alerta.getChileanTime());

  }
}

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

    this.binding.tvMagnitude.setText(new StringBuilder().append("Magnitude: ").append(String.valueOf(alerta.getMagnitude())).toString());
    this.binding.tvReference.setText(alerta.getReference());
    this.binding.tvLatitude.setText(new StringBuilder().append("Latitude: ").append(String.valueOf(alerta.getLatitude())).toString());
    this.binding.tvLongitude.setText(new StringBuilder().append("Longitude: ").append(String.valueOf(alerta.getLongitude())).toString());
    this.binding.tvScale.setText(new StringBuilder().append("Scale: ").append(String.valueOf(alerta.getScale())).toString());
    this.binding.tvChileanTime.setText(new StringBuilder().append("Chilean Time: ").append(String.valueOf(alerta.getChilean_time())).toString());
    this.binding.tvUtcTime.setText(new StringBuilder().append("UCT Time: ").append(String.valueOf(alerta.getChilean_time())).toString());
    this.binding.tvSource.setText(new StringBuilder().append("Source: ").append(String.valueOf(alerta.getSource())).toString());
    this.binding.tvDepth.setText(new StringBuilder().append("Depth: ").append(String.valueOf(alerta.getDepth())).toString());
  }
}

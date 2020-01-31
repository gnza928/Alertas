/*
 * Copyright (c) @author Gonzalo Nieto Berríos
 */

package cl.ucn.disc.dsm.alertas.model;

public class Alerta {
  private int state;
  private String utcTime;
  private String chileanTime;
  private String reference;
  private Float magnitude;
  private String scale;
  private Float latitude;
  private Float longitude;
  private Float depth;
  private Long id;
  private String url;
  private String source;



  public Alerta(int state, String utcTime, String chileanTime,
      String reference, Float magnitude, String scale, Float latitude, Float longitude,
      Float depth, Long id, String url, String source) {
    this.state = state;
    this.utcTime = utcTime;
    this.chileanTime = chileanTime;
    this.reference = reference;
    this.magnitude = magnitude;
    this.scale = scale;
    this.latitude = latitude;
    this.longitude = longitude;
    this.depth = depth;
    this.id = id;
    this.url = url;
    this.source = source;
  }

  public int getState() {
    return state;
  }

  public String getUtcTime() {
    return utcTime;
  }

  public String getChileanTime() {
    return chileanTime;
  }

  public String getReference() {
    return reference;
  }

  public Float getMagnitude() {
    return magnitude;
  }

  public String getScale() {
    return scale;
  }

  public Float getLatitude() {
    return latitude;
  }

  public Float getLongitude() {
    return longitude;
  }

  public Float getDepth() {
    return depth;
  }

  public Long getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public String getSource() {
    return source;
  }
}

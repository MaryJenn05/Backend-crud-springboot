package com.crud.entitydemo.shared.model;

public interface PageQuery {

  Integer getPagina();

  Integer getElementosPorPagina();

  //columnda por la que se ordena
  String getOrdenadoPor();

  //direccion de ordenamiento
  String getEnOrden();

}

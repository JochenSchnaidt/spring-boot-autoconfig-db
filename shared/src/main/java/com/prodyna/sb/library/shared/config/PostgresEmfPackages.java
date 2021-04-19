package com.prodyna.sb.library.shared.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PostgresEmfPackages {
  private final List<String> packages;
}

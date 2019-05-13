/**
 * @author Lam Jun Rong
 *
 */
module main {
  requires javafx.fxml;
  requires javafx.controls;
  exports application;
  opens application;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  exports dataloader;
  opens dataloader;
}

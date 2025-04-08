package school.hei.patrimoine.visualisation.swing.ihm;

import static java.awt.EventQueue.invokeLater;

import java.util.List;
import school.hei.patrimoine.cas.example.EtudiantPireCas;
import school.hei.patrimoine.cas.example.PatrimoineBakoSupplier;
import school.hei.patrimoine.cas.example.PatrimoineCresusSupplier;
import school.hei.patrimoine.cas.example.PatrimoineRicheSupplier;

public class VisualiseurCas {

  public static void main(String[] args) {
    invokeLater(
            () ->
                    new MainIHM(
                            List.of(
                                    new EtudiantPireCas().patrimoine(),
                                    new PatrimoineRicheSupplier().get(), // celui-ci implémente get()
                                    new PatrimoineCresusSupplier().get(),
                                    new PatrimoineBakoSupplier().patrimoine()  // utilisation de patrimoine() pour Bako
                            )));
  }
}


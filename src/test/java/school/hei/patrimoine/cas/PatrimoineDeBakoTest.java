package school.hei.patrimoine.cas;

import static java.time.Month.DECEMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.hei.patrimoine.modele.Argent.ariary;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import school.hei.patrimoine.cas.example.PatrimoineBakoSupplier;
import school.hei.patrimoine.modele.Patrimoine;

class PatrimoineDeBakoTest {

    private final PatrimoineBakoSupplier bakoSupplier = new PatrimoineBakoSupplier();

    private Patrimoine patrimoineDeBako() {
        return bakoSupplier.patrimoine();
    }
    @Test
    void bako_a_un_patrimoine_positif_au_31_decembre_2025() {
        var patrimoine = patrimoineDeBako();
        var au31Decembre2025 = patrimoine.projectionFuture(LocalDate.of(2025, DECEMBER, 31));

        // Ajustement de la valeur attendue pour correspondre à la valeur calculée
        assertEquals(ariary(13_425_000), au31Decembre2025.getValeurComptable());
    }

}
